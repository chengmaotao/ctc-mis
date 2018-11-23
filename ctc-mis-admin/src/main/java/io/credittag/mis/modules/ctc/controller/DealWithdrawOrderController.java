package io.credittag.mis.modules.ctc.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.credittag.mis.modules.ctc.entity.TblBcTransactionEntity;
import io.credittag.mis.modules.ctc.mq.Sender;
import io.credittag.mis.modules.ctc.service.DealWithdrawOrderService;
import io.credittag.mis.modules.ctc.service.TrxCtcService;
import io.credittag.mis.modules.ctc.utils.DateUtil;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;

/**
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-02 16:11:37
 */
@RestController
@RequestMapping("ctc/dealwithdraworder")
public class DealWithdrawOrderController {
	@Autowired
	private DealWithdrawOrderService withdrawOrderService;
	@Autowired
	private Sender sender;
	@Autowired
	private TrxCtcService trxCtcService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ctc:dealwithdraworder:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = withdrawOrderService.queryPage(params);

		return R.ok().put("page", page);
	}

	@RequestMapping("/push")
	@RequiresPermissions("ctc:dealwithdraworder:push")
	public R push(@RequestBody String[] ids) throws JsonProcessingException {

		List<TblBcTransactionEntity> list = withdrawOrderService.getTxData(ids);
		for (TblBcTransactionEntity tblBcTransactionEntity : list) {
			if (tblBcTransactionEntity.getToAccount() == null || tblBcTransactionEntity.getToAccount().length() < 33) {
				return R.error(4, "转账接收地址不合法");
			} else {
				trxCtcService.deleteByTxid(tblBcTransactionEntity.getTrxId());
				trxCtcService.deleteByInTxid(tblBcTransactionEntity.getTrxId());
				trxCtcService.deleteByOutTxid(tblBcTransactionEntity.getTrxId());
				JSONObject sendTrx = new JSONObject();
				sendTrx.put("trxId", tblBcTransactionEntity.getTrxId());
				sendTrx.put("blockNum", tblBcTransactionEntity.getBlockNum());
				sendTrx.put("trxTime",
						DateUtil.parseStr(tblBcTransactionEntity.getTrxTime(), DateUtil.C_TIME_PATTON_DEFAULT));
				sendTrx.put("fromAddress", tblBcTransactionEntity.getFromAccount());
				sendTrx.put("toAddress", tblBcTransactionEntity.getToAccount().substring(0,
						tblBcTransactionEntity.getToAccount().length() - 32));
				sendTrx.put("subToAddress", tblBcTransactionEntity.getToAccount());
				sendTrx.put("amount",
						new BigDecimal(tblBcTransactionEntity.getAmount()).divide(new BigDecimal(100000000)));
				sendTrx.put("fee", new BigDecimal(tblBcTransactionEntity.getFee()).divide(new BigDecimal(100000000)));
				sendTrx.put("memo", tblBcTransactionEntity.getMemo());
				sender.send(sendTrx);
			}
		}
		return R.ok();
	}
}
