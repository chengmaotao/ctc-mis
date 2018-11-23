package io.credittag.mis.modules.ctc.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.service.CustomerVerifyCodeService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;

/**
 * 
 *
 * @author yangzhongchao
 */
@RestController
@RequestMapping("ctc/customerverifycode")
public class CustomerVerifyCodeController {
	@Autowired
	private CustomerVerifyCodeService customerVerifyCodeService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ctc:customerverifycode:list")
	public R list(@RequestParam Map<String, Object> params) {
		if (StringUtils.isBlank((String) params.get("vcode"))) {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", "验证码类型必选");
			return error;
		}
		PageUtils page = null;
		String vcode = (String) params.get("vcode");
		if (vcode.equals("zc")) {
			page = customerVerifyCodeService.queryPageRegister(params);
		} else if (vcode.equals("dl")) {
			page = customerVerifyCodeService.queryPageDl(params);
		} else if (vcode.equals("tx")) {
			page = customerVerifyCodeService.queryPageWithDraw(params);
		} else {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", "验证码类型错误");
			return error;
		}
		return R.ok().put("page", page);
	}
}
