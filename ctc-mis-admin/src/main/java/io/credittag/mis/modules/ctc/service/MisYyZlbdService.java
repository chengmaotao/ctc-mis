package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.MisYyZlbdEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-28 16:36:00
 */
public interface MisYyZlbdService extends IService<MisYyZlbdEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    MisYyZlbdEntity findByIdself(Long id);
    
    boolean deleteByIds(Collection<? extends Serializable> idList);
    
    boolean insertSelf(MisYyZlbdEntity misYyZlbdEntity);
    
    boolean updateSelf(MisYyZlbdEntity misYyZlbdEntity);
}

