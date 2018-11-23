package io.credittag.mis.modules.ctc.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.credittag.mis.modules.ctc.entity.StopCustomerEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-03 10:42:16
 */
public interface StopCustomerDao extends BaseMapper<StopCustomerEntity> {

    List<StopCustomerEntity> withdraworderlist(Page<StopCustomerEntity> page, @Param("ew")Wrapper<StopCustomerEntity> wrapper);

    int freeze(@Param("ew")Wrapper<StopCustomerEntity> wrapper);

    int thaw(@Param("ew")Wrapper<StopCustomerEntity> wrapper);
}
