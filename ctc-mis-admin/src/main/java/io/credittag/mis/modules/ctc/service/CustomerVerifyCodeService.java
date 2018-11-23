package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.CustomerVerifyCodeEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface CustomerVerifyCodeService extends IService<CustomerVerifyCodeEntity> {
	
    PageUtils queryPageRegister(Map<String, Object> params);
    PageUtils queryPageWithDraw(Map<String, Object> params);
    PageUtils queryPageDl(Map<String, Object> params);
}

