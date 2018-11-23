package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.AccountDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface AccountDetailService extends IService<AccountDetailEntity> {

	R queryPage(Map<String, Object> params);
	R queryPageTotal(Map<String, Object> params);
	


	R queryMxPage(Map<String, Object> params);
	R queryMxPageTotal(Map<String, Object> params);
	
	
}
