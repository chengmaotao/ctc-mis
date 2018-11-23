package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface CustomerService extends IService<CustomerEntity> {
    PageUtils queryPage(Map<String, Object> params);

	R customerInvitationlist(Map<String, Object> params);
	
	R customerInvitationlistTotal(Map<String, Object> params);

	R customerbeInvitedlist(Map<String, Object> params);

	R customerDataCount(Map<String, Object> params);
	
	R customerDataCountTotal(Map<String, Object> params);

	R customerdetaillist(Map<String, Object> params);
	
	R customerdetaillistTotal(Map<String, Object> params);

	R whiteCustomerList(Map<String, Object> params);
	
	R whiteCustomerListTotal(Map<String, Object> params);

	R customerbuylist(Map<String, Object> params);
	
	R customerbuylistTotal(Map<String, Object> params);
	
	R updateRewardById(CustomerEntity entity);
}

