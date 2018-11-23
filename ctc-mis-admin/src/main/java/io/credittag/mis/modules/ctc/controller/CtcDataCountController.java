package io.credittag.mis.modules.ctc.controller;

import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.CtcDataCountEntity;
import io.credittag.mis.modules.ctc.service.impl.CtcDataCountServiceImpl;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author chengmaotao
 * 
 */
@RestController
@RequestMapping("ctc/datacount")
public class CtcDataCountController {

	@Autowired
	private CtcDataCountServiceImpl ctcDataCountService;

	@RequestMapping("/getCtcDataCount")
	@RequiresPermissions("ctc:datacount:read")
	public @ResponseBody R getCtcDataCount() {
		
		CtcDataCountEntity ctcDataCount = new CtcDataCountEntity();
		// 用户总量
		ctcDataCount.setCustomerCount(ctcDataCountService.getCountCustomerNum());
		// 钱包地址数
		ctcDataCount.setWalletAddressCount(ctcDataCountService.getWalletAddressCount());

		// 交易总量 交易总笔数
		CtcDataCountEntity t = ctcDataCountService.getTransactionInfo();
		ctcDataCount.setTransactionCount(t.getTransactionCount());
		ctcDataCount.setTransactionAmountCount(t.getTransactionAmountCount());

		// 交易地址数
		ctcDataCount.setTransactionAddressCount(ctcDataCountService.getTransactionAddressCount());

		return R.ok().put("ctcDataCount", ctcDataCount);
		

	}
}
