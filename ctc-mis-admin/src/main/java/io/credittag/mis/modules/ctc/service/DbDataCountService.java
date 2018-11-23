package io.credittag.mis.modules.ctc.service;

import java.util.Map;

import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;

import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface DbDataCountService {

	R dbDataCount(Map<String, Object> params);
	

}
