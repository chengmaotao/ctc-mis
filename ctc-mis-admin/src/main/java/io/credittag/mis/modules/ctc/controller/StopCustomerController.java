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

import io.credittag.mis.modules.ctc.entity.StopCustomerEntity;
import io.credittag.mis.modules.ctc.service.StopCustomerService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;


/**
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-03 10:42:16
 */
@RestController
@RequestMapping("ctc/stopcustomer")
public class StopCustomerController {
    @Autowired
    private StopCustomerService stopCustomerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:stopcustomer:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = stopCustomerService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:stopcustomer:info")
    public R info(@PathVariable("id") Long id) {
        StopCustomerEntity stopCustomer = stopCustomerService.selectById(id);

        return R.ok().put("stopCustomer", stopCustomer);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:stopcustomer:save")
    public R save(@RequestBody StopCustomerEntity stopCustomer) {
        stopCustomerService.insert(stopCustomer);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:stopcustomer:update")
    public R update(@RequestBody StopCustomerEntity stopCustomer) {
        ValidatorUtils.validateEntity(stopCustomer);
        stopCustomerService.updateAllColumnById(stopCustomer);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:stopcustomer:delete")
    public R delete(@RequestBody Long[] ids) {
        stopCustomerService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 冻结
     */
    @RequestMapping("/freeze")
    @RequiresPermissions("ctc:stopcustomer:update")
    public R freeze(@RequestBody String id) {
        if (!stopCustomerService.updateStatusById(id, 0)) {
            return R.error(1, "操作失败");
        }

        return R.ok();
    }


    /**
     * 解冻
     */
    @RequestMapping("/thaw")
    @RequiresPermissions("ctc:stopcustomer:update")
    public R thaw(@RequestBody String id) {
        if (!stopCustomerService.updateStatusById(id, 1)) {
            return R.error(1, "操作失败");
        }
        return R.ok();
    }

}
