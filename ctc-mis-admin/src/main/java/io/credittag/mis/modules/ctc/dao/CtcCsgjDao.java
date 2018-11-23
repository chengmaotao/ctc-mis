package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.CtcCsgjEntity;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * CTC  财神管家的响应表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface CtcCsgjDao extends BaseMapper<CtcCsgjEntity> {

	List<CtcCsgjEntity> withdraworderlist(Page<CtcCsgjEntity> page, @Param("ew")EntityWrapper<CtcCsgjEntity> wrapper);
	
	CtcCsgjEntity withdraworderlistTotal(@Param("ew")EntityWrapper<CtcCsgjEntity> wrapper);
	
	//CtcCsgjEntity withdraworderlistTotal(Map<String,String> map);

	List<DbDataCountEntity> seleteCompleteCustomer(CustomerEntity customerEntity);
	
	List<DbDataCountEntity> selectCompleteCount(CustomerEntity customerEntity);
	
	Map<String,Double> totalUserDetailDayForAmount(Map<String,String> map);
}
