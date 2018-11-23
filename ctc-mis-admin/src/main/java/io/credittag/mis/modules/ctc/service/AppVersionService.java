package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.AppVersionEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
public interface AppVersionService extends IService<AppVersionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

