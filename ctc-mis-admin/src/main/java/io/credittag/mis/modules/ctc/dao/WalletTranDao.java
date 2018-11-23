package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.CtcDataCountEntity;
import io.credittag.mis.modules.ctc.entity.WalletDataCount;
import io.credittag.mis.modules.ctc.entity.WalletTranEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 钱包交易表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:10
 */
public interface WalletTranDao extends BaseMapper<WalletTranEntity> {

	List<WalletTranEntity> wallettranList(Page<WalletTranEntity> page, @Param("ew")EntityWrapper<WalletTranEntity> wrapper);
	
	WalletTranEntity wallettranListTotal(@Param("ew")EntityWrapper<WalletTranEntity> wrapper);
	
	List<WalletDataCount> getTranInfo(WalletTranEntity walletTranEntity);

	List<WalletDataCount> getTranAddressCount(WalletTranEntity walletTranEntity);
	
	WalletTranEntity getTranDate();

	CtcDataCountEntity getTransactionInfo();
	
	String getTransactionAddressCount();
	
	
}
