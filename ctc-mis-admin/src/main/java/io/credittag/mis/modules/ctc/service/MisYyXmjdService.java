package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.MisYyXmjdEntity;

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
public interface MisYyXmjdService extends IService<MisYyXmjdEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    MisYyXmjdEntity findById(Long id);
    
    boolean deleteBatchIdsByOverride(Collection<? extends Serializable> idList);
    
    boolean insertByOverride(MisYyXmjdEntity misYyXmjd);
    
    boolean updateAllColumnByIdByOverride(MisYyXmjdEntity misYyXmjd);
    
    
}

