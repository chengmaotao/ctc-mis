package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.AddWhiteCustomerEntity;

import java.util.Map;

/**
 * 添加到第三方白名单的用户
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface AddWhiteCustomerService extends IService<AddWhiteCustomerEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

