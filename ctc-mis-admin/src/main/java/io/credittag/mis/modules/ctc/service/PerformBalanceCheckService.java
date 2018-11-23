package io.credittag.mis.modules.ctc.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.ErrorAddressBalanceEntity;
import io.credittag.mis.modules.ctc.entity.PerformBalanceCheckEntity;
import io.credittag.mis.modules.ctc.entity.WalletAssetEntity;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-08 14:00:29
 */
public interface PerformBalanceCheckService extends IService<PerformBalanceCheckEntity> {

	PageUtils perform();

	String getRerformStatus();
	
	 Integer getMaxId();
	 public List<ErrorAddressBalanceEntity> getErrorAddressBalanceList(Integer maxId);

	PerformBalanceCheckEntity checkBalance();

	boolean performCheckBalance(PerformBalanceCheckEntity entity, List<WalletAssetEntity> walletAssetList);

	List<ErrorAddressBalanceEntity> queryAddressErrorBalance();

	boolean resertAddressErrorBalance(String address,List<ErrorAddressBalanceEntity> res);
}
