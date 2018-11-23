package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.MisYyXmjdEntity;
import io.credittag.mis.modules.ctc.entity.MisYyZlbdEntity;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-28 16:36:00
 */
public interface MisYyZlbdDao extends BaseMapper<MisYyZlbdEntity> {
	
	List<MisYyZlbdEntity> queryPage(RowBounds rowBounds, @Param("ew") EntityWrapper<MisYyZlbdEntity> wrapper);
	MisYyZlbdEntity findByIdSelf(Long id);
}
