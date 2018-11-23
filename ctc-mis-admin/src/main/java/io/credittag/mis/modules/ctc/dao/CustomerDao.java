package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface CustomerDao extends BaseMapper<CustomerEntity> {

	CustomerEntity getCustomerByUsername(Map<String,Object> params);

	List<CustomerEntity> customerInvitationlist(Page<CustomerEntity> page, @Param("ew")EntityWrapper<CustomerEntity> wrapper);
	
	List<CustomerEntity> customerInvitationlistself(Page<CustomerEntity> page,Map<String,String> map);
	
	CustomerEntity customerInvitationlistTotal(@Param("ew")EntityWrapper<CustomerEntity> wrapper);
	
	CustomerEntity customerInvitationlistTotalself(Map<String,String> map);

	List<CustomerEntity> customerbeInvitedlist(Page<CustomerEntity> page, @Param("ew")EntityWrapper<CustomerEntity> wrapper);

	List<CustomerEntity> getCTCCountByDate(CustomerEntity customerEntity);
	
	List<CustomerEntity> getCounts(CustomerEntity customerEntity);

	List<CustomerEntity> customerdetaillist(Page<CustomerEntity> page, @Param("ew")EntityWrapper<CustomerEntity> wrapper);
	
	CustomerEntity customerdetaillistTotal(@Param("ew")EntityWrapper<CustomerEntity> wrapper);

	List<CustomerEntity> whiteCustomerList(Page<CustomerEntity> page, @Param("ew")EntityWrapper<CustomerEntity> wrapper);
	
	CustomerEntity whiteCustomerListTotal(@Param("ew")EntityWrapper<CustomerEntity> wrapper);

	List<CustomerEntity> customerbuylist(Page<CustomerEntity> page, @Param("ew")EntityWrapper<CustomerEntity> wrapper);
	
	CustomerEntity customerbuylistTotal( @Param("ew")EntityWrapper<CustomerEntity> wrapper);
	
	List<DbDataCountEntity> seletCustomerCount(CustomerEntity customerEntity);
	
	
	String selectCountCustomerNum();
	int updateRewardById(CustomerEntity customerEntity); 
	List<CustomerEntity> customerbeInvitedlistUseMap(Page<CustomerEntity> page,Map<String,Object> map);
	
	List<Map<String,String>> totalUserDetail(Map<String,String>map);
	
	List<Map<Integer,Integer>> totalUserDetailDaySup(Map<String,String> map);
	
}
