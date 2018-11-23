package io.credittag.mis.modules.ctc.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.modules.ctc.service.AccountDetailService;
import io.credittag.mis.common.utils.R;

/**
 * 
 * 信用钻相关业务控制器
 * 
 * @author yangzhongchao
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@RestController
@RequestMapping("ctc/accountdetail")
public class AccountDetailController {
	@Autowired
	private AccountDetailService accountDetailService;

	/**
	 * 列表
	 */
	
	
	@RequestMapping("/list")
	@RequiresPermissions("ctc:accountdetail:list")
	public R list(@RequestParam Map<String, Object> params) {
		return accountDetailService.queryPage(params);

	}
	@RequestMapping("/listTotal")
	@RequiresPermissions("ctc:accountdetail:list")
	public R listtotal(@RequestParam Map<String, Object> params) {
		return accountDetailService.queryPageTotal(params);

	}

	@RequestMapping("/listmx")
	@RequiresPermissions("ctc:accountdetail:listmx")
	public R listmx(@RequestParam Map<String, Object> params) {
		return accountDetailService.queryMxPage(params);

	}
	
	@RequestMapping("/listmxTotal")
	@RequiresPermissions("ctc:accountdetail:listmx")
	public R listmxTotal(@RequestParam Map<String, Object> params) {
		return accountDetailService.queryMxPageTotal(params);

	}


}
