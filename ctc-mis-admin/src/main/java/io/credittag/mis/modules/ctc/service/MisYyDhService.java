package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.MisYyDhEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-08 17:52:27
 */
public interface MisYyDhService extends IService<MisYyDhEntity> {

	PageUtils queryPage(Map<String, Object> params);

	List<MisYyDhEntity> queryByDhCode(String dhCode);

}
