package io.credittag.mis.modules.ctc.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import io.credittag.mis.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.modules.ctc.entity.MisCwTkEntity;
import io.credittag.mis.modules.ctc.service.MisCwTkService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;



/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-07 10:11:16
 */
@RestController
@RequestMapping("ctc/miscwtk")
public class MisCwTkController {
    @Autowired
    private MisCwTkService misCwTkService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:miscwtk:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = misCwTkService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 生成uuid
     */
    @RequestMapping("/getuuid")
    @RequiresPermissions("ctc:miscwtk:getuuid")
    public R getuuid(){
    	String uuid = UUID.randomUUID().toString().replace("-", "");
    	MisCwTkEntity misCwTk=new MisCwTkEntity();
    	misCwTk.setSubaddress(uuid);
        return R.ok().put("misCwTk", misCwTk);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:miscwtk:info")
    public R info(@PathVariable("id") Long id){
        MisCwTkEntity misCwTk = misCwTkService.selectById(id);

        return R.ok().put("misCwTk", misCwTk);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:miscwtk:save")
    public R save(@RequestBody MisCwTkEntity misCwTk){
    	misCwTk.setCreateTime(new Date());
    	misCwTk.setUpdateTime(new Date());
        misCwTkService.insert(misCwTk);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:miscwtk:update")
    public R update(@RequestBody MisCwTkEntity misCwTk){
        ValidatorUtils.validateEntity(misCwTk);
        misCwTk.setUpdateTime(new Date());
        misCwTkService.updateAllColumnById(misCwTk);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:miscwtk:delete")
    public R delete(@RequestBody Long[] ids){
        misCwTkService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
