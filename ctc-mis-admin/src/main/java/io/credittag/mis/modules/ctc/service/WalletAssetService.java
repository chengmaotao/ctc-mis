package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.WalletAssetEntity;


import java.util.List;
import java.util.Map;

/**
 * 钱包地址表
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
public interface WalletAssetService extends IService<WalletAssetEntity> {

    PageUtils queryPage(Map<String, Object> params);

	R walletDataCount(Map<String, Object> params);
	
	R walletDataCountTotal(Map<String, Object> params);

	List<WalletAssetEntity> selectList();
	
	R ranklist(Map<String,Object> params);
	
	R rankDetail(Map<String,Object> paramsmap);


	

}

