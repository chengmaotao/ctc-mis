package io.credittag.mis.modules.ctc.controller;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.common.validator.ValidatorUtils;
import io.credittag.mis.modules.ctc.entity.CtcCsgjEntity;
import io.credittag.mis.modules.ctc.service.CtcCsgjService;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * CTC  财神管家的响应表
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@RestController
@RequestMapping("ctc/ctccsgj")
public class CtcCsgjController {
    @Autowired
    private CtcCsgjService ctcCsgjService;

    
    /**
     * 信用报告购买次数明细
     * @Title: ctccsgjlist
     * @Description: TODO
     * @param: @param params
     * @param: @return   
     * @return: R   
     * @throws
     */
    @RequestMapping("/ctccsgjlist")
    @RequiresPermissions("ctc:ctccsgj:list")
    public R ctccsgjlist(@RequestParam Map<String, Object> params){
    	return ctcCsgjService.ctccsgjlist(params);
    }
    
    
    @RequestMapping("/ctccsgjlistTotal")
    @RequiresPermissions("ctc:ctccsgj:list")
    public R ctccsgjlistTotal(@RequestParam Map<String, Object> params){
    	return ctcCsgjService.ctccsgjlistTotal(params);
    }
    
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:ctccsgj:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ctcCsgjService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:ctccsgj:info")
    public R info(@PathVariable("id") Long id){
        CtcCsgjEntity ctcCsgj = ctcCsgjService.selectById(id);

        return R.ok().put("ctcCsgj", ctcCsgj);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:ctccsgj:save")
    public R save(@RequestBody CtcCsgjEntity ctcCsgj){
        ctcCsgjService.insert(ctcCsgj);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:ctccsgj:update")
    public R update(@RequestBody CtcCsgjEntity ctcCsgj){
        ValidatorUtils.validateEntity(ctcCsgj);
        ctcCsgjService.updateAllColumnById(ctcCsgj);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:ctccsgj:delete")
    public R delete(@RequestBody Long[] ids){
        ctcCsgjService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
