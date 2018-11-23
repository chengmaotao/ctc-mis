package io.credittag.mis.modules.ctc.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.WithdrawOrderDao;
import io.credittag.mis.modules.ctc.entity.CtcCsgjEntity;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.entity.WithdrawOrderEntity;
import io.credittag.mis.modules.ctc.service.WithdrawOrderService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

@Service("withdrawOrderService")
public class WithdrawOrderServiceImpl extends ServiceImpl<WithdrawOrderDao, WithdrawOrderEntity>
		implements WithdrawOrderService {

	@Autowired
	private WithdrawOrderDao withdrawOrderDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<WithdrawOrderEntity> page = this.selectPage(new Query<WithdrawOrderEntity>(params).getPage(),
				new EntityWrapper<WithdrawOrderEntity>());

		return new PageUtils(page);
	}

	/**
	 * 
	 * @Title: withdraworderlist @Description: 提现明细 @param: @param
	 * params @param: @return @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R withdraworderlist(Map<String, Object> params) {
		Page<WithdrawOrderEntity> page = new Query<WithdrawOrderEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String customerId = (String) params.get("id");
		String username = (String) params.get("username");
		String status = (String) params.get("status");

		Date beginDate = null;
		boolean b1 = false;
		if (StringUtils.isNotBlank(beginDates)) {
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
			b1 = true;
		}

		Date endDate = null;
		boolean b2 = false;
		if (StringUtils.isNotBlank(endDates)) {
			b2 = true;
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}

		boolean b3 = false;
		if (StringUtils.isNotBlank(customerId)) {
			customerId = customerId.trim();
			b3 = true;
		}

		boolean b4 = false;
		if (StringUtils.isNotBlank(username)) {
			username = username.trim();
			b3 = true;
		}

		// 查询条件都为空时 不返回
		if (!b1 && !b2 && !b3 && !b4) {
			return R.ok();
		}

		EntityWrapper<WithdrawOrderEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
					CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<WithdrawOrderEntity>) new EntityWrapper<WithdrawOrderEntity>()
					.ge(beginDate != null, "a.create_time", beginDate).le(endDate != null, "a.create_time", endDate)
					.eq(StringUtils.isNotBlank(customerId), "a.cid", customerId)
					.eq(StringUtils.isNotBlank(username), "c.username", username).isWhere(true);

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<WithdrawOrderEntity>) new EntityWrapper<WithdrawOrderEntity>()
					.eq(StringUtils.isNotBlank(customerId), "a.cid", customerId)
					.eq(StringUtils.isNotBlank(username), "c.username", username).isWhere(true);
		} else {
			return R.ok();
		}

		EntityWrapper<WithdrawOrderEntity> wrapper = (EntityWrapper<WithdrawOrderEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);
		entityWrapper.eq(StringUtils.isNotBlank(status), "a.status", status);
		page.setRecords(withdrawOrderDao.withdraworderlist(page, wrapper));
		
		
		return R.ok().put("page",new PageUtils(page));
	}
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R withdraworderlistTotal(Map<String, Object> params) {
		Page<WithdrawOrderEntity> page = new Query<WithdrawOrderEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String customerId = (String) params.get("id");
		String username = (String) params.get("username");
		String status = (String) params.get("status");

		Date beginDate = null;
		boolean b1 = false;
		if (StringUtils.isNotBlank(beginDates)) {
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
			b1 = true;
		}

		Date endDate = null;
		boolean b2 = false;
		if (StringUtils.isNotBlank(endDates)) {
			b2 = true;
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}

		boolean b3 = false;
		if (StringUtils.isNotBlank(customerId)) {
			customerId = customerId.trim();
			b3 = true;
		}

		boolean b4 = false;
		if (StringUtils.isNotBlank(username)) {
			username = username.trim();
			b3 = true;
		}

		// 查询条件都为空时 不返回
		if (!b1 && !b2 && !b3 && !b4) {
			return R.ok();
		}

		EntityWrapper<WithdrawOrderEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
					CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<WithdrawOrderEntity>) new EntityWrapper<WithdrawOrderEntity>()
					.ge(beginDate != null, "a.create_time", beginDate).le(endDate != null, "a.create_time", endDate)
					.eq(StringUtils.isNotBlank(customerId), "a.cid", customerId)
					.eq(StringUtils.isNotBlank(username), "c.username", username).isWhere(true);

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<WithdrawOrderEntity>) new EntityWrapper<WithdrawOrderEntity>()
					.eq(StringUtils.isNotBlank(customerId), "a.cid", customerId)
					.eq(StringUtils.isNotBlank(username), "c.username", username).isWhere(true);
		} else {
			return R.ok();
		}

		EntityWrapper<WithdrawOrderEntity> wrapper = (EntityWrapper<WithdrawOrderEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);
		entityWrapper.eq(StringUtils.isNotBlank(status), "a.status", status);
		//page.setRecords(withdrawOrderDao.withdraworderlist(page, wrapper));
		
		WithdrawOrderEntity withdrawOrderEntity = withdrawOrderDao.withdraworderlistTotal(wrapper);
		PageUtils p =  new PageUtils(page);
		if(withdrawOrderEntity.getAmount()!=null) {
			p.setCtcCount(withdrawOrderEntity.getAmount().doubleValue());
		}
		if(withdrawOrderEntity.getCid()!=null) {
			p.setTatalUserCountInThisCondition(withdrawOrderEntity.getCid().intValue());
		}
	
		
		return R.ok().put("page", p);
	}

}
