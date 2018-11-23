package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.MisYyExcEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-25 12:14:16
 */
public interface MisYyExcService extends IService<MisYyExcEntity> {

    PageUtils queryPage(Map<String, Object> params);

}

