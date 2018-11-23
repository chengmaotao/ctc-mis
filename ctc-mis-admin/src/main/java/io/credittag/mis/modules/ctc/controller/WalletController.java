package io.credittag.mis.modules.ctc.controller;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.common.validator.ValidatorUtils;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.entity.WalletEntity;
import io.credittag.mis.modules.ctc.service.WalletService;

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
 * 钱包用户表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
@RestController
@RequestMapping("ctc/wallet")
public class WalletController {
	@Autowired
	private WalletService walletService;

	@RequestMapping("/walletList")
	@RequiresPermissions("ctc:wallet:list")
	public R walletList(@RequestParam Map<String, Object> params) {
		return walletService.walletList(params);
	}
	
	@RequestMapping("/walletListTotal")
	@RequiresPermissions("ctc:wallet:list")
	public R walletListTotal(@RequestParam Map<String, Object> params) {
		return walletService.walletListTotal(params);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ctc:wallet:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = walletService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ctc:wallet:info")
	public R info(@PathVariable("id") Long id) {
		WalletEntity wallet = walletService.selectById(id);

		return R.ok().put("wallet", wallet);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ctc:wallet:save")
	public R save(@RequestBody WalletEntity wallet) {
		walletService.insert(wallet);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ctc:wallet:update")
	public R update(@RequestBody WalletEntity wallet) {
		ValidatorUtils.validateEntity(wallet);
		walletService.updateAllColumnById(wallet);// 全部更新

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ctc:wallet:delete")
	public R delete(@RequestBody Long[] ids) {
		walletService.deleteBatchIds(Arrays.asList(ids));

		return R.ok();
	}

}
