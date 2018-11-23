package io.credittag.mis.modules.ctc.controller;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.common.validator.ValidatorUtils;
import io.credittag.mis.modules.ctc.entity.WalletTranEntity;
import io.credittag.mis.modules.ctc.service.WalletTranService;

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
 * 钱包交易表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:10
 */
@RestController
@RequestMapping("ctc/wallettran")
public class WalletTranController {
	@Autowired
	private WalletTranService walletTranService;

	@RequestMapping("/wallettranList")
	@RequiresPermissions("ctc:wallettran:list")
	public R wallettranList(@RequestParam Map<String, Object> params) {

		return walletTranService.wallettranList(params);

	}
	
	@RequestMapping("/wallettranListTotal")
	@RequiresPermissions("ctc:wallettran:list")
	public R wallettranListTotal(@RequestParam Map<String, Object> params) {

		return walletTranService.wallettranListTotal(params);

	}

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ctc:wallettran:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = walletTranService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ctc:wallettran:info")
	public R info(@PathVariable("id") Long id) {
		WalletTranEntity walletTran = walletTranService.selectById(id);

		return R.ok().put("walletTran", walletTran);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ctc:wallettran:save")
	public R save(@RequestBody WalletTranEntity walletTran) {
		walletTranService.insert(walletTran);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ctc:wallettran:update")
	public R update(@RequestBody WalletTranEntity walletTran) {
		ValidatorUtils.validateEntity(walletTran);
		walletTranService.updateAllColumnById(walletTran);// 全部更新

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ctc:wallettran:delete")
	public R delete(@RequestBody Long[] ids) {
		walletTranService.deleteBatchIds(Arrays.asList(ids));

		return R.ok();
	}

}
