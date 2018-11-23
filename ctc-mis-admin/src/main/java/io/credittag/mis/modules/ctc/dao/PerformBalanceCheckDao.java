package io.credittag.mis.modules.ctc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import io.credittag.mis.modules.ctc.entity.PerformBalanceCheckEntity;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-08 14:00:29
 */
public interface PerformBalanceCheckDao extends BaseMapper<PerformBalanceCheckEntity> {

	Integer getMaxId();

}
