package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.MisCwTkEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-07 10:11:16
 */
public interface MisCwTkService extends IService<MisCwTkEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

