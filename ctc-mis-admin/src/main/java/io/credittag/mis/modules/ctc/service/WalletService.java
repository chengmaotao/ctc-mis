package io.credittag.mis.modules.ctc.service;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.WalletEntity;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * 钱包用户表
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
public interface WalletService extends IService<WalletEntity> {

    PageUtils queryPage(Map<String, Object> params);

	R walletList(Map<String, Object> params);
	
	R walletListTotal(Map<String, Object> params);
}

