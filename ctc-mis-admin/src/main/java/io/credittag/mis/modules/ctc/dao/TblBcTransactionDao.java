package io.credittag.mis.modules.ctc.dao;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.credittag.mis.modules.ctc.entity.TblBcTransactionEntity;
import io.credittag.mis.modules.ctc.entity.TotalDealDayEntity;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 区块链交易表
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-02 18:35:29
 */
public interface TblBcTransactionDao extends BaseMapper<TblBcTransactionEntity> {
    TblBcTransactionEntity withIdQuery(@Param("ew")Wrapper<TblBcTransactionEntity> wrapper);
    
    TotalDealDayEntity totalDealDay(Map<String,String> map);
  // List<TblBcTransactionEntity> totalDealDayAMTmore0(Map<String,String> map);
    
    
    List<TblBcTransactionEntity> getTranDetailByAddr(Map<String,Object> paramsmap);
    TblBcTransactionEntity totalgetTranDetailByAddr(Map<String,Object> paramsmap);
    
    Map<String,Long>  getTranDetailByAddrTransed(Map<String,Object> paramsmap);
    
    Map<String,Long> addrFromAndToCount(Map<String,String> map);
}
