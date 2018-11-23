package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.MisWxMenuEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-11 15:02:54
 */
public interface MisWxMenuService extends IService<MisWxMenuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

