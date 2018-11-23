package io.credittag.mis.modules.ctc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.ErrorAddressBalanceEntity;
import io.credittag.mis.modules.ctc.entity.PerformBalanceCheckEntity;
import io.credittag.mis.modules.ctc.entity.WalletAssetEntity;
import io.credittag.mis.modules.ctc.service.PerformBalanceCheckService;
import io.credittag.mis.modules.ctc.service.WalletAssetService;

/**
 * 
 * 执行查询余额是否和链上一致
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-08 14:00:29
 */
@RestController
@RequestMapping("ctc/performbalancecheck")
public class PerformBalanceCheckController {
	@Autowired
	private PerformBalanceCheckService performBalanceCheckService;

	@Autowired
	private WalletAssetService walletAssetService;
	

	/**
	 * 列表
	 */
	@RequestMapping("/perform")
	public R list() {
		PageUtils page = performBalanceCheckService.perform();

		return R.ok().put("page", page);
	}

	// 查询是否有执行中的
	@RequestMapping("/getRerformStatus")
	public @ResponseBody String getRerformStatus() {

		return performBalanceCheckService.getRerformStatus();

	}

	// 开始执行 查询余额是否正确
	@RequestMapping("/checkBalance")
	public @ResponseBody String checkBalance() {

		int insertid = 0;
		try {

			// 插入一条 执行记录 状态是执行中
			PerformBalanceCheckEntity entity = performBalanceCheckService.checkBalance();
			
			if (entity != null && entity.getId() > 0) {
				insertid = entity.getId();
				// walletAssetService.selectList(new
				// EntityWrapper<WalletAssetEntity>());

				List<WalletAssetEntity> walletAssetList = walletAssetService.selectList();

				// 开启一个线程后台执行
				boolean performCheckBalance = performBalanceCheckService.performCheckBalance(entity, walletAssetList);
				if (performCheckBalance) {
					return "0";
				}
				return "1";
			}

		} catch (Exception ex) {
			performBalanceCheckService.deleteById(insertid);
			
			ex.printStackTrace();
			return "1";
		}

		return "1";

	}

	/**
	 * 不对的余额列表
	 * 
	 * @return
	 */
	@RequestMapping("/queryAddressErrorBalance")
	public List<ErrorAddressBalanceEntity> queryAddressErrorBalance() {
		return performBalanceCheckService.queryAddressErrorBalance();
	}

	@RequestMapping("/resertAddressErrorBalance")
	public @ResponseBody String resertAddressErrorBalance(String address) {
		
		String rerformStatus = performBalanceCheckService.getRerformStatus();
		
		if("0".equals(rerformStatus)){
			return "0";
		}
		
		Integer maxId = performBalanceCheckService.getMaxId();
		
		List<ErrorAddressBalanceEntity> errorAddressBalanceList = performBalanceCheckService.getErrorAddressBalanceList(maxId);
		
		if(errorAddressBalanceList == null || errorAddressBalanceList.isEmpty()){
			return "0";
		}

		if (performBalanceCheckService.resertAddressErrorBalance(address,errorAddressBalanceList)) {
			return "0";
		}
		return "1";
	}

}
