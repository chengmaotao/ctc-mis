package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.CustomerVerifyCodeEntity;

import java.util.List;

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
public interface CustomerVerifyCodeDao extends BaseMapper<CustomerVerifyCodeEntity> {
	List<CustomerVerifyCodeEntity> getRecordByMobileWithDraw(RowBounds rowBounds,
			@Param("ew") EntityWrapper<CustomerVerifyCodeEntity> wrapper);

	List<CustomerVerifyCodeEntity> getRecordByMobileRegister(RowBounds rowBounds,
			@Param("ew") EntityWrapper<CustomerVerifyCodeEntity> wrapper);

	List<CustomerVerifyCodeEntity> getRecordByMobileDl(RowBounds rowBounds,
			@Param("ew") EntityWrapper<CustomerVerifyCodeEntity> wrapper);
}
