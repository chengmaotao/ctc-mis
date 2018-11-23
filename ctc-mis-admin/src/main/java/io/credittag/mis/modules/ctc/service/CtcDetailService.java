package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.CtcDetailEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface CtcDetailService extends IService<CtcDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

