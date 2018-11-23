package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.MisYyDhEntity;
import io.credittag.mis.modules.ctc.entity.WalletAssetEntity;
import io.credittag.mis.modules.ctc.entity.WalletDataCount;
import io.credittag.mis.modules.ctc.entity.WalletTranEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 钱包地址表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
public interface WalletAssetDao extends BaseMapper<WalletAssetEntity> {

	List<WalletDataCount> getWalletAddressCountByDate(WalletAssetEntity walletAssetEntity);
	
	String selectWalletAddressCount();

	void updateTotalAmtByAddress(WalletAssetEntity assetEntity);

	WalletAssetEntity getWalletIdByAddress(String address);

	List<WalletAssetEntity> selectList();

	Map<String,Long> queryMoneyMore0(Map<String,String> map);
	
	List<WalletAssetEntity> totalDealDayAMT0(Map<String,String> map);
	
	Map<String,Long> totalDealDayAMTAll(Map<String,String> map);;
	
	List<WalletAssetEntity> ranklist(Map<String,Object> paramsmap);
	
	
	
	
}
