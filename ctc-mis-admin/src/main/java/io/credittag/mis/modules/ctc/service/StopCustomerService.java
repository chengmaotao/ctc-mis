package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.StopCustomerEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-03 10:42:16
 */
public interface StopCustomerService extends IService<StopCustomerEntity> {

    PageUtils queryPage(Map<String, Object> params);

    boolean updateStatusById(String id,int type);
}

