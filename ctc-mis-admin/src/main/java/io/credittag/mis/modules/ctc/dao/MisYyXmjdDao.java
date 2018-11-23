package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.MisYySumEntity;
import io.credittag.mis.modules.ctc.entity.MisYyXmjdEntity;

import java.util.List;
import java.util.Map;

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
public interface MisYyXmjdDao extends BaseMapper<MisYyXmjdEntity> {
	List<MisYyXmjdEntity> queryPage(RowBounds rowBounds, @Param("ew") EntityWrapper<MisYyXmjdEntity> wrapper);
	
	MisYyXmjdEntity findById(long id);
	void updateByIdOverride(MisYyXmjdEntity entity);
}
