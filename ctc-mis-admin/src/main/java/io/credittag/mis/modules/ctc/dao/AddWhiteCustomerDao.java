package io.credittag.mis.modules.ctc.dao;

import java.util.List;
import java.util.Map;

import io.credittag.mis.modules.ctc.entity.AddWhiteCustomerEntity;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 添加到第三方白名单的用户
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface AddWhiteCustomerDao extends BaseMapper<AddWhiteCustomerEntity> {

	List<DbDataCountEntity> seletWhiteCustomerCount(CustomerEntity customerEntity);
	
	Map<String,Integer> sumWhiteUSer(Map<String,String> map);
	
}
