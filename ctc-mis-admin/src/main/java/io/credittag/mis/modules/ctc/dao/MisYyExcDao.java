package io.credittag.mis.modules.ctc.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.credittag.mis.modules.ctc.entity.MisYyExcEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-25 12:14:16
 */
public interface MisYyExcDao extends BaseMapper<MisYyExcEntity> {
    List<MisYyExcEntity> withdraworderlist(Page<MisYyExcEntity> page, @Param("ew")Wrapper<MisYyExcEntity> wrapper);

    List<MisYyExcEntity> queryRepeat(MisYyExcEntity entity);
}
