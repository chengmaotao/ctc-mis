package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.MisYyQuesEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-28 16:44:08
 */
public interface MisYyQuesService extends IService<MisYyQuesEntity> {

    PageUtils queryPage(Map<String, Object> params);


}

