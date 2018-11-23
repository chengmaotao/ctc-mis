package io.credittag.mis.modules.ctc.dao;

import java.util.List;

import io.credittag.mis.modules.ctc.entity.CtcDetailEntity;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
public interface CtcDetailDao extends BaseMapper<CtcDetailEntity> {

	List<DbDataCountEntity> selectCtcNum(CustomerEntity customerEntity);
	
}
