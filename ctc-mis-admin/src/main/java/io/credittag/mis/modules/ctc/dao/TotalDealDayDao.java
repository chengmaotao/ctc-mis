package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.TotalDealDayEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-06 09:24:50
 */
public interface TotalDealDayDao extends BaseMapper<TotalDealDayEntity> {
	int insertDealTotal(TotalDealDayEntity totalDealDayEntity);
	
	List<TotalDealDayEntity> query(Map<String,String> map);
	TotalDealDayEntity total(Map<String,String> map);
	void deleteBySameTime(Map<String,String> map);
	
}
