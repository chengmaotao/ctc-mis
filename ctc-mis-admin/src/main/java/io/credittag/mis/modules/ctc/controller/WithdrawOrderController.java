package io.credittag.mis.modules.ctc.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import io.credittag.mis.common.validator.ValidatorUtils;
import io.credittag.mis.job.TotalDealData;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.modules.ctc.dao.TotalDealDayDao;
import io.credittag.mis.modules.ctc.entity.TotalDealDayEntity;
import io.credittag.mis.modules.ctc.entity.WithdrawOrderEntity;
import io.credittag.mis.modules.ctc.service.WithdrawOrderService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;



/**
 * 
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:13
 */
@RestController
@RequestMapping("ctc/withdraworder")
public class WithdrawOrderController {
    @Autowired
    private WithdrawOrderService withdrawOrderService;
    
    @Resource
    private TotalDealData totalDealData;
    @Resource
	private TotalDealDayDao totalDealDayDao;
    
    
    /**
     * 
     * @Title: withdraworderlist
     * @Description: 提现明细
     * @param: @param params
     * @param: @return   
     * @return: R   
     * @throws
     */
//    @RequestMapping("/test")
//	@RequiresPermissions("ctc:withdraworder:list")
//	public List test() {
//    	Map map = new HashMap();
//		return  totalDealDayDao.query(map);
//
//	}
	@RequestMapping("/withdraworderlist")
	@RequiresPermissions("ctc:withdraworder:list")
	public R withdraworderlist(@RequestParam Map<String, Object> params) {

		return withdrawOrderService.withdraworderlist(params);

	}
	
	@RequestMapping("/withdraworderlistTotal")
	@RequiresPermissions("ctc:withdraworder:list")
	public R withdraworderlistTotal(@RequestParam Map<String, Object> params) {

		return withdrawOrderService.withdraworderlistTotal(params);

	}
    
    
    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("ctc:withdraworder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = withdrawOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("ctc:withdraworder:info")
    public R info(@PathVariable("id") Long id){
        WithdrawOrderEntity withdrawOrder = withdrawOrderService.selectById(id);

        return R.ok().put("withdrawOrder", withdrawOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("ctc:withdraworder:save")
    public R save(@RequestBody WithdrawOrderEntity withdrawOrder){
        withdrawOrderService.insert(withdrawOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("ctc:withdraworder:update")
    public R update(@RequestBody WithdrawOrderEntity withdrawOrder){
        ValidatorUtils.validateEntity(withdrawOrder);
        withdrawOrderService.updateAllColumnById(withdrawOrder);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("ctc:withdraworder:delete")
    public R delete(@RequestBody Long[] ids){
        withdrawOrderService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
