package io.credittag.mis.modules.ctc.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.credittag.mis.modules.ctc.entity.DealWithdrawOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 描述：
 *
 * @author shawn
 * @date 2018/7/2
 */
public interface DealWithdrawOrderDao extends BaseMapper<DealWithdrawOrderEntity> {

	List<DealWithdrawOrderEntity> withdraworderlist(Page<DealWithdrawOrderEntity> page,
			@Param("ew") Wrapper<DealWithdrawOrderEntity> wrapper);

	DealWithdrawOrderEntity queryEntity(@Param("ew") Wrapper<DealWithdrawOrderEntity> wrapper);
}
