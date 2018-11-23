package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.MisYySumEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-08 17:52:27
 */
public interface MisYyDhSumService extends IService<MisYySumEntity> {

	R queryPageSum(Map<String, Object> params);
}
