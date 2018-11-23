package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.WithdrawOrderEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:13
 */
public interface WithdrawOrderService extends IService<WithdrawOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

	R withdraworderlist(Map<String, Object> params);
	R withdraworderlistTotal(Map<String, Object> params);
}

