package io.credittag.mis.modules.ctc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;
import io.credittag.mis.modules.ctc.entity.WithdrawOrderEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:13
 */
public interface WithdrawOrderDao extends BaseMapper<WithdrawOrderEntity> {

	List<WithdrawOrderEntity> withdraworderlist(Page<WithdrawOrderEntity> page, @Param("ew")EntityWrapper<WithdrawOrderEntity> wrapper);
	
	WithdrawOrderEntity withdraworderlistTotal(@Param("ew")EntityWrapper<WithdrawOrderEntity> wrapper);
	
	List<DbDataCountEntity> selectWithdrawCustomer(CustomerEntity customerEntity);

	List<DbDataCountEntity> selectWithdrawCountAndCtcNum(CustomerEntity customerEntity);
	
	Map<String,Integer> sumCTC(Map<String,String> map);
}
