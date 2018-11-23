package io.credittag.mis.modules.ctc.service;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.WalletTranEntity;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * 钱包交易表
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:10
 */
public interface WalletTranService extends IService<WalletTranEntity> {

    PageUtils queryPage(Map<String, Object> params);

	R wallettranList(Map<String, Object> params);
	
	R wallettranListTotal(Map<String, Object> params);
}

