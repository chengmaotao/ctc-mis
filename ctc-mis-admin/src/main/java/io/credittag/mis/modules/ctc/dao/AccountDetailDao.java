package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.AccountDetailEntity;

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
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface AccountDetailDao extends BaseMapper<AccountDetailEntity> {
	List<AccountDetailEntity> getRecordByDay(RowBounds rowBounds,
			@Param("ew") EntityWrapper<AccountDetailEntity> wrapper);
	AccountDetailEntity getRecordByDayTotal(
			@Param("ew") EntityWrapper<AccountDetailEntity> wrapper);
	
	List<AccountDetailEntity> getRecordMx(RowBounds rowBounds,
			@Param("ew") EntityWrapper<AccountDetailEntity> wrapper);
	AccountDetailEntity  getRecordMxTotal(
			@Param("ew") EntityWrapper<AccountDetailEntity> wrapper);
}
