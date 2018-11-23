package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.TblBcTransactionEntity;

import java.util.Map;

/**
 * 区块链交易表
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-02 18:35:29
 */
public interface TblBcTransactionService extends IService<TblBcTransactionEntity> {

    PageUtils queryPage(Map<String, Object> params);

    TblBcTransactionEntity query(String trx_id);
}

