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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import io.credittag.mis.modules.ctc.entity.MisYyZlbdEntity;
import io.credittag.mis.modules.ctc.service.MisYyZlbdService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;



/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-28 16:36:00
 */
@RestController
@RequestMapping("ctc/misyyzlbd")
public class MisYyZlbdController {
    @Autowired
    private MisYyZlbdService misYyZlbdService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:misyyzlbd:list")
    public R list(@RequestParam Map<String, Object> params){
    	 PageUtils page = misYyZlbdService.queryPage(params);
    	
    	return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:misyyzlbd:info")
    public R info(@PathVariable("id") Long id){
        MisYyZlbdEntity misYyZlbd = misYyZlbdService.findByIdself(id);

        return R.ok().put("misYyZlbd", misYyZlbd);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:misyyzlbd:save")
    public R save(@RequestBody MisYyZlbdEntity misYyZlbd){
    	
    	if(misYyZlbd.getLanguage()==null) {
    		misYyZlbd.setLanguage("zh");
    	}
    	misYyZlbd.setCreateTime(new Date());
    	misYyZlbd.setUpdateTime(new Date());
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	misYyZlbd.setTime(formatter.format(new Date()));
        misYyZlbdService.insertSelf(misYyZlbd);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:misyyzlbd:update")
    public R update(@RequestBody MisYyZlbdEntity misYyZlbd){
        ValidatorUtils.validateEntity(misYyZlbd);
        misYyZlbd.setUpdateTime(new Date());
        misYyZlbdService.updateSelf(misYyZlbd);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:misyyzlbd:delete")
    public R delete(@RequestBody Long[] ids){
    	 misYyZlbdService.deleteByIds(Arrays.asList(ids));
        return R.ok();
    }

}
