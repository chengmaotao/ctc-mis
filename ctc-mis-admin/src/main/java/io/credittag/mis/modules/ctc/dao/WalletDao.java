package io.credittag.mis.modules.ctc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.credittag.mis.modules.ctc.entity.WalletEntity;
import io.credittag.mis.modules.ctc.entity.WalletTranEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 钱包用户表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
public interface WalletDao extends BaseMapper<WalletEntity> {

	List<WalletEntity> walletList(Page<WalletEntity> page, @Param("ew")EntityWrapper<WalletEntity> wrapper);
	WalletEntity walletListTotal( @Param("ew")EntityWrapper<WalletEntity> wrapper);
	
}
