package io.credittag.mis.modules.ctc.controller;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.common.validator.ValidatorUtils;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.service.CustomerService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@RestController
@RequestMapping("ctc/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	/**
	 * 
	 * @Title: customerDataCount @Description: 邀请统计数据 @param: @param
	 *         params @param: @return @return: R @throws
	 */
	@RequestMapping("/customerDataCount")
	@RequiresPermissions("ctc:customer:dataCount")
	public R customerDataCount(@RequestParam Map<String, Object> params) {

		return customerService.customerDataCount(params);

	}
	
	@RequestMapping("/customerDataCountTotal")
	@RequiresPermissions("ctc:customer:dataCount")
	public R customerDataCountTotal(@RequestParam Map<String, Object> params) {

		return customerService.customerDataCountTotal(params);

	}

	/**
	 * 
	 * @Title: customerInvitationlist @Description: 成功发起邀请人明细 @param: @param
	 *         params @param: @return @return: R @throws
	 */
	@RequestMapping("/customerInvitationlist")
	@RequiresPermissions("ctc:customerInvitation:list")
	public R customerInvitationlist(@RequestParam Map<String, Object> params) {
		return customerService.customerInvitationlist(params);
	}

	@RequestMapping("/customerInvitationlistTotal")
	@RequiresPermissions("ctc:customerInvitation:list")
	public R customerInvitationlistTotal(@RequestParam Map<String, Object> params) {
		return customerService.customerInvitationlistTotal(params);
	}
	/**
	 * 被邀请人明细
	 * 
	 * @Title: customerbeInvitedlist @Description: TODO @param: @param
	 *         params @param: @return @return: R @throws
	 */
	@RequestMapping("/customerbeInvitedlist")
	@RequiresPermissions("ctc:customerbeInvited:list")
	public R customerbeInvitedlist(@RequestParam Map<String, Object> params) {
		return customerService.customerbeInvitedlist(params);
	}

	/**
	 * 矿场人数明细 @Title: customerdetaillist @Description: TODO @param: @param
	 * params @param: @return @return: R @throws
	 */
	@RequestMapping("/customerdetaillist")
	@RequiresPermissions("ctc:customerdetail:list")
	public R customerdetaillist(@RequestParam Map<String, Object> params) {
		return customerService.customerdetaillist(params);
	}
	
	@RequestMapping("/customerdetaillistTotal")
	@RequiresPermissions("ctc:customerdetail:list")
	public R customerdetaillistTotal(@RequestParam Map<String, Object> params) {
		return customerService.customerdetaillistTotal(params);
	}

	/**
	 * 
	 * @Title: whiteCustomerList @Description: CTC手机号白名单明细 @param: @param
	 *         params @param: @return @return: R @throws
	 */
	@RequestMapping("/whiteCustomerList")
	@RequiresPermissions("ctc:whitecustomer:list")
	public R whiteCustomerList(@RequestParam Map<String, Object> params) {
		return customerService.whiteCustomerList(params);
	}
	@RequestMapping("/whiteCustomerListTotal")
	@RequiresPermissions("ctc:whitecustomer:list")
	public R whiteCustomerListTotal(@RequestParam Map<String, Object> params) {
		return customerService.whiteCustomerListTotal(params);
	}

	/**
	 * 
	 * @Title: customerbuylist @Description: 信用报告购买人数明细 @param: @param
	 *         params @param: @return @return: R @throws
	 */
	@RequestMapping("/customerbuylist")
	@RequiresPermissions("ctc:customerbuy:list")
	public R customerbuylist(@RequestParam Map<String, Object> params) {
		return customerService.customerbuylist(params);
	}
	
	@RequestMapping("/customerbuylistTotal")
	@RequiresPermissions("ctc:customerbuy:list")
	public R customerbuylistTotal(@RequestParam Map<String, Object> params) {
		return customerService.customerbuylistTotal(params);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ctc:customer:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = customerService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 信息
	 */

	@RequestMapping("/info/{id}")
	@RequiresPermissions("ctc:customer:info")
	public R info(@PathVariable("id") Long id) {
		CustomerEntity customer = customerService.selectById(id);

		return R.ok().put("customer", customer);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ctc:customer:save")
	public R save(@RequestBody CustomerEntity customer) {
		customerService.insert(customer);

		return R.ok();
	}

	/**
	 * 修改
	 */

	@RequestMapping("/update")
	@RequiresPermissions("ctc:customer:update")
	public R update(@RequestBody CustomerEntity customer) {
		ValidatorUtils.validateEntity(customer);
		return customerService.updateRewardById(customer);// 全部更新
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ctc:customer:delete")
	public R delete(@RequestBody Long[] ids) {
		customerService.deleteBatchIds(Arrays.asList(ids));

		return R.ok();
	}

}
