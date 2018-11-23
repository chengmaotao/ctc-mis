package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;
import io.credittag.mis.modules.ctc.entity.TotalUserdetailDayEntity;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-06 09:24:50
 */
public interface TotalUserdetailDayDao extends BaseMapper<TotalUserdetailDayEntity> {
	void insertTotalUserDetail(DbDataCountEntity dbDataCountEntity);
	List<TotalUserdetailDayEntity> query(Map<String,String> map);
	TotalUserdetailDayEntity total(Map<String,String> map);
	void deleteBysametime(Map<String,String> map);
}
