package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.ErrorAddressBalanceEntity;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-08 14:00:29
 */
public interface ErrorAddressBalanceDao extends BaseMapper<ErrorAddressBalanceEntity> {

	List<ErrorAddressBalanceEntity> getListByPerformId(Integer maxId);
	
}
