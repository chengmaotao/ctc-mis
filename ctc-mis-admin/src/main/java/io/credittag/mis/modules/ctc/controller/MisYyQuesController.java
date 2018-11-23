package io.credittag.mis.modules.ctc.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import io.credittag.mis.common.validator.ValidatorUtils;
import io.credittag.mis.modules.ctc.entity.MisYyDhEntity;
import io.credittag.mis.modules.ctc.entity.MisYyExcEntity;
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

import io.credittag.mis.modules.ctc.entity.MisYyQuesEntity;
import io.credittag.mis.modules.ctc.service.MisYyQuesService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-28 16:44:08
 */
@RestController
@RequestMapping("ctc/misyyques")
public class MisYyQuesController {
    @Autowired
    private MisYyQuesService misYyQuesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:misyyques:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = misYyQuesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:misyyques:info")
    public R info(@PathVariable("id") Long id) {
        MisYyQuesEntity misYyQues = misYyQuesService.selectById(id);

        return R.ok().put("misYyQues", misYyQues);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:misyyques:save")
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
            List<MisYyQuesEntity> allByExcel = getAllByExcel(file);
            boolean insert = false;
            for (MisYyQuesEntity entity : allByExcel) {
                insert = misYyQuesService.insert(entity);
            }
            if (!insert) {
                return R.error(3, "导入失败");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return R.error(4, e.getMessage());
        } catch (BiffException e) {
            e.printStackTrace();
            return R.error(2, "文件格式错误");
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(2, "文件格式错误");
        }

//        misYyExcService.setData(getAllByExcel(file));
//        misYyExcService.insert(misYyExc);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:misyyques:update")
    public R update(@RequestBody MisYyQuesEntity misYyQues) {
        ValidatorUtils.validateEntity(misYyQues);
        misYyQues.setUpdateTime(new Date());
        misYyQuesService.updateAllColumnById(misYyQues);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:misyyques:delete")
    public R delete(@RequestBody Long[] ids) {
        misYyQuesService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    public List<MisYyQuesEntity> getAllByExcel(MultipartFile file) throws IllegalArgumentException, BiffException, IOException {
        List<MisYyQuesEntity> list = new ArrayList<>();
        Workbook rwb = Workbook.getWorkbook(file.getInputStream());
        Sheet rs = rwb.getSheet(0);//或者rwb.getSheet(0)
        int clos = rs.getColumns();//得到所有的列
        int rows = rs.getRows();//得到所有的行

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < clos; j++) {
                MisYyQuesEntity misYyQuesEntity = new MisYyQuesEntity();

                String question = rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                if (StringUtils.isEmpty(question)) {
                    throw new IllegalArgumentException("问题不能为空");
                }
                misYyQuesEntity.setQuestion(question);

                String answerA = rs.getCell(j++, i).getContents();
                if (StringUtils.isEmpty(answerA)) {
                    throw new IllegalArgumentException("选项A不能为空");
                }
                misYyQuesEntity.setAnswerA(answerA);

                String answerB = rs.getCell(j++, i).getContents();
                if (StringUtils.isEmpty(answerB)) {
                    throw new IllegalArgumentException("选项B不能为空");
                }
                misYyQuesEntity.setAnswerB(answerB);

                String answerC = rs.getCell(j++, i).getContents();
                misYyQuesEntity.setAnswerC(answerC);

                String answerD = rs.getCell(j++, i).getContents();
                misYyQuesEntity.setAnswerD(answerD);

                String answer = rs.getCell(j++, i).getContents();
                if (StringUtils.isEmpty(answer)) {
                    throw new IllegalArgumentException("正确答案不能为空");
                }
                misYyQuesEntity.setAnswerRight(answer);

                String keyword = rs.getCell(j++, i).getContents();
                misYyQuesEntity.setKeyword(keyword);

                String type = rs.getCell(j++, i).getContents();
                if (StringUtils.isEmpty(type)) {
                    throw new IllegalArgumentException("兑换类型不能为空");
                } else if (!StringUtils.contains(type, "0") && !StringUtils.contains(type, "1") && !StringUtils.contains(type, "2")) {
                    throw new IllegalArgumentException("兑换类型错误");
                }
                misYyQuesEntity.setType(Integer.parseInt(type));

                String dhCount = rs.getCell(j++, i).getContents();
                if (StringUtils.isEmpty(dhCount)) {
                    throw new IllegalArgumentException("兑换数量不能为空");
                }
                misYyQuesEntity.setCount(new BigDecimal(dhCount));

                String useTime = rs.getCell(j++, i).getContents();
                if (StringUtils.isEmpty(useTime)) {
                    throw new IllegalArgumentException("使用时间不能为空");
                }
                misYyQuesEntity.setUseTime(useTime);

                String language = rs.getCell(j++, i).getContents();
                if (StringUtils.isEmpty(language)) {
                    throw new IllegalArgumentException("语言类型不能为空");
                } else if (!StringUtils.contains(language, "zh") && !StringUtils.contains(language, "en")) {
                    throw new IllegalArgumentException("语言类型错误");
                }
                misYyQuesEntity.setLanguage(language);

                misYyQuesEntity.setCreateTime(new Date());

                misYyQuesEntity.setUpdateTime(new Date());

                misYyQuesEntity.setStatus(0);

                list.add(misYyQuesEntity);

            }
        }
        return list;
    }

}
