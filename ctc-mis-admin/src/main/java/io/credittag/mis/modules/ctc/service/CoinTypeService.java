package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.CoinTypeEntity;

import java.util.Map;

/**
 * 系统支持的货币列表
 *
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
public interface CoinTypeService extends IService<CoinTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

