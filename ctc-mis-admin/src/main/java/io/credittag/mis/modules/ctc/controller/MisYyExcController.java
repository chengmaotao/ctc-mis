package io.credittag.mis.modules.ctc.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.*;

import io.credittag.mis.common.validator.ValidatorUtils;
import io.credittag.mis.modules.ctc.entity.MisYyDhEntity;
import io.credittag.mis.modules.ctc.service.MisYyDhService;
import io.credittag.mis.modules.ctc.utils.DateUtil;
import io.credittag.mis.modules.ctc.utils.StringUtilsTool;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.modules.ctc.entity.MisYyExcEntity;
import io.credittag.mis.modules.ctc.service.MisYyExcService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;


/**
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-25 12:14:16
 */
@RestController
@RequestMapping("ctc/misyyexc")
public class MisYyExcController {
    @Autowired
    private MisYyDhService misYyDhService;
    @Autowired
    private MisYyExcService misYyExcService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:misyyexc:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = misYyExcService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:misyyexc:info")
    public R info(@PathVariable("id") Long id) {
        MisYyExcEntity misYyExc = misYyExcService.selectById(id);

        return R.ok().put("misYyExc", misYyExc);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:misyyexc:save")
    public R save(@RequestBody MultipartFile file) {
        if (file == null) {
            return R.error(1, "没有文件");
        }
        String filename = file.getOriginalFilename();
        String prefix = filename.substring(filename.lastIndexOf("."));
        if (!prefix.equals(".xls")) {
            return R.error(2, "文件格式错误");
        }
        try {
            List<MisYyExcEntity> allByExcel = getAllByExcel(file);
            boolean insert = false;
            for (MisYyExcEntity entity : allByExcel) {
                insert = misYyExcService.insert(entity);
            }
            if (!insert) {
                return R.error(3, "导入失败");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return R.error(4, e.getMessage());
        }

//        misYyExcService.setData(getAllByExcel(file));
//        misYyExcService.insert(misYyExc);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:misyyexc:update")
    public R update(@RequestBody MisYyExcEntity misYyExc) {
        ValidatorUtils.validateEntity(misYyExc);
        misYyExcService.updateAllColumnById(misYyExc);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:misyyexc:delete")
    public R delete(@RequestBody Long[] ids) {
        misYyExcService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    public List<MisYyExcEntity> getAllByExcel(MultipartFile file) throws IllegalArgumentException {
        List<MisYyExcEntity> list = new ArrayList<>();
        String pid = UUID.randomUUID().toString().replace("-", "");
        try {
            Workbook rwb = Workbook.getWorkbook(file.getInputStream());
            Sheet rs = rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos = rs.getColumns();//得到所有的列
            int rows = rs.getRows();//得到所有的行

            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    MisYyExcEntity misYyExcEntity = new MisYyExcEntity();

                    String dhCode = StringUtilsTool.getRandomString(7);
                    misYyExcEntity.setDhcode(dhCode);
                    List<MisYyDhEntity> temp_list = misYyDhService.queryByDhCode(dhCode);
                    while (temp_list.size() > 0) {
                        dhCode = StringUtilsTool.getRandomString(7);
                        misYyExcEntity.setDhcode(dhCode);
                        temp_list = misYyDhService.queryByDhCode(dhCode);
                    }

                    String phone = rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    if (StringUtils.isEmpty(phone)) {
                        throw new IllegalArgumentException("手机号不能为空");
                    }
                    misYyExcEntity.setPhone(phone);

                    String dhCount = rs.getCell(j++, i).getContents();
                    if (StringUtils.isEmpty(dhCount)) {
                        throw new IllegalArgumentException("兑换数量不能为空");
                    }
                    misYyExcEntity.setCount(new BigDecimal(dhCount));

                    String type = rs.getCell(j++, i).getContents();
                    if (StringUtils.isEmpty(type)) {
                        throw new IllegalArgumentException("兑换类型不能为空");
                    } else if(!StringUtils.contains(type,"0")&&!StringUtils.contains(type,"1")&&!StringUtils.contains(type,"2")){
                        throw new IllegalArgumentException("兑换类型错误");
                    }
                    misYyExcEntity.setType(Integer.parseInt(type));

                    String expireTimeStr = rs.getCell(j++, i).getContents();
                    if (StringUtils.isEmpty(expireTimeStr)) {
                        throw new IllegalArgumentException("过期时间不能为空");
                    }
                    Date date = new Date(expireTimeStr);
                    Date expriTime = DateUtil.parseDate(date, DateUtil.C_DATE_PATTON_DEFAULT);
                    expriTime.setHours(23);
                    expriTime.setMinutes(59);
                    expriTime.setSeconds(59);
                    misYyExcEntity.setExpireTime(expriTime);

                    String wxNickName = rs.getCell(j++, i).getContents();
                    if (StringUtils.isEmpty(wxNickName)) {
                        throw new IllegalArgumentException("微信昵称不能为空");
                    }
                    misYyExcEntity.setWxNickName(wxNickName);

                    String mark = rs.getCell(j++, i).getContents();
                    if (StringUtils.isEmpty(mark)) {
                        throw new IllegalArgumentException("活动标签不能为空");
                    }
                    misYyExcEntity.setMark(mark);

                    Date createDate = new Date();
                    misYyExcEntity.setCreateTime(createDate);

                    Date updateDate = new Date();
                    misYyExcEntity.setUpdateTime(updateDate);

                    misYyExcEntity.setStatus(0);
                    misYyExcEntity.setPid(pid);


                    list.add(misYyExcEntity);
//                    list1.add(misYyDhEntity);

//                    list.add(new MisYyExcEntity(Integer.parseInt(id), name, sex, Integer.parseInt(num)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();

        } catch (BiffException e) {
            e.printStackTrace();
        }
        return list;
    }


//    public static void main(String[] args) {
//        String s = "2018/06/25";
//        Date date1 = new Date(s);
////        Date date = DateUtil.parseDate(date1, DateUtil.C_DATE_PATTON_DEFAULT);
//        System.out.print(DateUtil.parseStr(date1, DateUtil.C_DATE_PATTON_DEFAULT));
//        getAllByExcel("/Users/shawn/Documents/dh_batch_templates.xls");
//    }

}
