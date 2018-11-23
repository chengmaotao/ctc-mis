package io.credittag.mis.modules.ctc.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.dao.DealWithdrawOrderDao;
import io.credittag.mis.modules.ctc.entity.DealWithdrawOrderEntity;
import io.credittag.mis.modules.ctc.entity.TblBcTransactionEntity;
import io.credittag.mis.modules.ctc.service.DealWithdrawOrderService;
import io.credittag.mis.modules.ctc.service.TblBcTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;

@Service("dealWithdrawOrderService")
public class DealWithdrawOrderServiceImpl extends ServiceImpl<DealWithdrawOrderDao, DealWithdrawOrderEntity>
		implements DealWithdrawOrderService {

	@Autowired
	private DealWithdrawOrderDao dealWithdrawOrderDao;

	private List<TblBcTransactionEntity> mData;

	@Autowired
	private TblBcTransactionService tblBcTransactionService;

	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Wrapper<DealWithdrawOrderEntity> wrapper = new EntityWrapper<DealWithdrawOrderEntity>().eq("status", "C")
				.like("to_address", "CbNDfboKcjMAvkWRuvk6HcWSASv3b3YjJe").orderBy("create_time", false);
		Page<DealWithdrawOrderEntity> page = this.selectPage(new Query<DealWithdrawOrderEntity>(params).getPage(),
				wrapper);
		mData = new ArrayList<TblBcTransactionEntity>();
		List<DealWithdrawOrderEntity> list = dealWithdrawOrderDao.withdraworderlist(page, wrapper);
		List<DealWithdrawOrderEntity> listData = new ArrayList<DealWithdrawOrderEntity>();
		for (int i = 0; i < list.size(); i++) {
			TblBcTransactionEntity en = tblBcTransactionService.query(list.get(i).getOutsideOrderId());
			if (en != null) {
				listData.add(list.get(i));
				mData.add(en);
			}
		}

		page.setTotal(listData.size());
		page.setRecords(listData);
		return new PageUtils(page);
	}

	@Override
	public List<TblBcTransactionEntity> getTxData(String[] ids) {

		return mData;
	}

}
