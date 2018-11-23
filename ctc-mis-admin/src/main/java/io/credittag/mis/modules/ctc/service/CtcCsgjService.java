package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.CtcCsgjEntity;

import java.util.Map;

/**
 * CTC  财神管家的响应表
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface CtcCsgjService extends IService<CtcCsgjEntity> {

    PageUtils queryPage(Map<String, Object> params);

	R ctccsgjlist(Map<String, Object> params);
	R ctccsgjlistTotal(Map<String, Object> params);
}

