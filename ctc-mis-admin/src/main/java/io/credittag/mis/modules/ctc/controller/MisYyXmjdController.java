package io.credittag.mis.modules.ctc.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.credittag.mis.common.validator.ValidatorUtils;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.modules.ctc.entity.MisYyXmjdEntity;
import io.credittag.mis.modules.ctc.service.MisYyXmjdService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;



/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-28 16:36:00
 */
@RestController
@RequestMapping("ctc/misyyxmjd")
public class MisYyXmjdController {
    @Autowired
    private MisYyXmjdService misYyXmjdService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:misyyxmjd:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = misYyXmjdService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:misyyxmjd:info")
    public R info(@PathVariable("id") Long id){
        MisYyXmjdEntity misYyXmjd = misYyXmjdService.findById(id);

        return R.ok().put("misYyXmjd", misYyXmjd);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:misyyxmjd:save")
    public R save(@RequestBody MisYyXmjdEntity misYyXmjd){
    	
    	if(misYyXmjd.getLanguage()==null) {
    		misYyXmjd.setLangue("zh");
    	}
    	misYyXmjd.setCreateTime(new Date());
    	misYyXmjd.setUpdateTime(new Date());
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	misYyXmjd.setTime(formatter.format(new Date()));
        misYyXmjdService.insertByOverride(misYyXmjd);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:misyyxmjd:update")
    public R update(@RequestBody MisYyXmjdEntity misYyXmjd){
    	
        //ValidatorUtils.validateEntity(misYyXmjd);
        misYyXmjdService.updateAllColumnByIdByOverride(misYyXmjd);//
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:misyyxmjd:delete")
    public R delete(@RequestBody Long[] ids){
        misYyXmjdService.deleteBatchIdsByOverride(Arrays.asList(ids));
        return R.ok();
    }

}
