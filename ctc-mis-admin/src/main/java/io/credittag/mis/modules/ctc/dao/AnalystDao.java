package io.credittag.mis.modules.ctc.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import io.credittag.mis.modules.ctc.entity.AccountDetailEntity;
import io.credittag.mis.modules.ctc.entity.AnalystEntity;

public interface AnalystDao extends BaseMapper<AnalystEntity> {
	List<AnalystEntity> getRecord(RowBounds rowBounds,
			@Param("ew") EntityWrapper<AnalystEntity> wrapper);
}
