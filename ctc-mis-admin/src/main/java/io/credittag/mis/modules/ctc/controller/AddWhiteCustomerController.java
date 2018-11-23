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

import io.credittag.mis.modules.ctc.entity.AddWhiteCustomerEntity;
import io.credittag.mis.modules.ctc.service.AddWhiteCustomerService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;



/**
 * 添加到第三方白名单的用户
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@RestController
@RequestMapping("ctc/addwhitecustomer")
public class AddWhiteCustomerController {
    @Autowired
    private AddWhiteCustomerService addWhiteCustomerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:addwhitecustomer:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = addWhiteCustomerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:addwhitecustomer:info")
    public R info(@PathVariable("id") Long id){
        AddWhiteCustomerEntity addWhiteCustomer = addWhiteCustomerService.selectById(id);

        return R.ok().put("addWhiteCustomer", addWhiteCustomer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:addwhitecustomer:save")
    public R save(@RequestBody AddWhiteCustomerEntity addWhiteCustomer){
        addWhiteCustomerService.insert(addWhiteCustomer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:addwhitecustomer:update")
    public R update(@RequestBody AddWhiteCustomerEntity addWhiteCustomer){
        ValidatorUtils.validateEntity(addWhiteCustomer);
        addWhiteCustomerService.updateAllColumnById(addWhiteCustomer);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:addwhitecustomer:delete")
    public R delete(@RequestBody Long[] ids){
        addWhiteCustomerService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
