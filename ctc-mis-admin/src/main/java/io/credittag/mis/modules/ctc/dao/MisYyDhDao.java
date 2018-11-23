package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.MisYyDhEntity;
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
 * @date 2018-06-08 17:52:27
 */
public interface MisYyDhDao extends BaseMapper<MisYyDhEntity> {
	List<MisYyDhEntity> queryPage(RowBounds rowBounds, @Param("ew") EntityWrapper<MisYyDhEntity> wrapper);

	List<MisYyDhEntity> queryByDhCode(String dhCode);
}
