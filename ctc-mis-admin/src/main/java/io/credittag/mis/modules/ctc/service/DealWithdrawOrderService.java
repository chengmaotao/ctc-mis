package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.DealWithdrawOrderEntity;
import io.credittag.mis.modules.ctc.entity.TblBcTransactionEntity;

import java.util.List;
import java.util.Map;
/**
 * 描述：
 *
 * @author shawn
 * @date 2018/7/2
 */
public interface DealWithdrawOrderService  extends IService<DealWithdrawOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TblBcTransactionEntity> getTxData(String[] ids);
}
