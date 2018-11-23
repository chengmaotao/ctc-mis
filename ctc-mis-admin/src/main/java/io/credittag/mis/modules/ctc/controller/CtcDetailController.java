package io.credittag.mis.modules.ctc.controller;

import java.util.Arrays;
import java.util.Map;

import io.credittag.mis.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.modules.ctc.entity.CtcDetailEntity;
import io.credittag.mis.modules.ctc.service.CtcDetailService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;



/**
 * 
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@RestController
@RequestMapping("ctc/ctcdetail")
public class CtcDetailController {
    @Autowired
    private CtcDetailService ctcDetailService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:ctcdetail:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = ctcDetailService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:ctcdetail:info")
    public R info(@PathVariable("id") Long id){
        CtcDetailEntity ctcDetail = ctcDetailService.selectById(id);

        return R.ok().put("ctcDetail", ctcDetail);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:ctcdetail:save")
    public R save(@RequestBody CtcDetailEntity ctcDetail){
        ctcDetailService.insert(ctcDetail);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:ctcdetail:update")
    public R update(@RequestBody CtcDetailEntity ctcDetail){
        ValidatorUtils.validateEntity(ctcDetail);
        ctcDetailService.updateAllColumnById(ctcDetail);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:ctcdetail:delete")
    public R delete(@RequestBody Long[] ids){
        ctcDetailService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
