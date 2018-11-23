package io.credittag.mis.modules.ctc.service.impl;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.credittag.mis.common.utils.DateUtils;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.AccountDetailDao;
import io.credittag.mis.modules.ctc.entity.AccountDetailEntity;
import io.credittag.mis.modules.ctc.service.AccountDetailService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

@Service("accountDetailService")
public class AccountDetailServiceImpl extends ServiceImpl<AccountDetailDao, AccountDetailEntity>
		implements AccountDetailService {

	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public R queryPage(Map<String, Object> params) {
		Page<AccountDetailEntity> page = new Query<AccountDetailEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		Date beginDate = null;
		if (!StringUtils.isEmpty(beginDates)) {
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
		}

		Date endDate = null;
		if (!StringUtils.isEmpty(endDates)) {
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}

		EntityWrapper<AccountDetailEntity> entityWrapper = (EntityWrapper<AccountDetailEntity>) new EntityWrapper<AccountDetailEntity>()
				.having("dayTime>='" + DateUtils.format(beginDate, ConstantField.dateFormatPattern) + "' and dayTime<'"
						+ DateUtils.format(endDate, ConstantField.dateFormatPattern) + "'")
				.orderBy("dayTime", false);

		EntityWrapper<AccountDetailEntity> wrapper = (EntityWrapper<AccountDetailEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);
		int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
				CtcMisUtils.StringToDate(endDates));
		if (getdiffBytwoDate >= ConstantField.max_date_long) {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
			return error;
		}
		page.setRecords(baseMapper.getRecordByDay(page, wrapper));
		
		return R.ok().put("page", new PageUtils(page));
		
	}
	
	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public R queryPageTotal(Map<String, Object> params) {
		Page<AccountDetailEntity> page = new Query<AccountDetailEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		Date beginDate = null;
		if (!StringUtils.isEmpty(beginDates)) {
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
		}

		Date endDate = null;
		if (!StringUtils.isEmpty(endDates)) {
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}

		EntityWrapper<AccountDetailEntity> entityWrapper = (EntityWrapper<AccountDetailEntity>) new EntityWrapper<AccountDetailEntity>()
				.having("dayTime>='" + DateUtils.format(beginDate, ConstantField.dateFormatPattern) + "' and dayTime<'"
						+ DateUtils.format(endDate, ConstantField.dateFormatPattern) + "'")
				.orderBy("dayTime", false);

		EntityWrapper<AccountDetailEntity> wrapper = (EntityWrapper<AccountDetailEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);
		int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
				CtcMisUtils.StringToDate(endDates));
		if (getdiffBytwoDate >= ConstantField.max_date_long) {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
			return error;
		}
		//page.setRecords(baseMapper.getRecordByDay(page, wrapper));
		
		
		
		AccountDetailEntity accountDetailEntity = baseMapper.getRecordByDayTotal(wrapper);
		PageUtils p = new PageUtils(page);
		p.setTotalDetailsInThisCondition(accountDetailEntity.getAmountBalance().doubleValue());
		p.setTatalUserCountInThisCondition(accountDetailEntity.getPersonCount());
		
		return R.ok().put("page", p);
		
	}
	
	
	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public R queryMxPage(Map<String, Object> params) {
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String userId = (String) params.get("userId");
		String phoneNo = (String) params.get("phoneNo");
		Date beginDate = null;
		if (!StringUtils.isEmpty(beginDates)) {
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
		}

		Date endDate = null;
		if (!StringUtils.isEmpty(endDates)) {
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}
		Page<AccountDetailEntity> page = new Query<AccountDetailEntity>(params).getPage();

		EntityWrapper<AccountDetailEntity> entityWrapper = (EntityWrapper<AccountDetailEntity>) new EntityWrapper<AccountDetailEntity>()
				.and().gt("a.create_time", DateUtils.format(beginDate, ConstantField.dateFormatPattern))
				.lt("a.create_time", DateUtils.format(endDate, ConstantField.dateFormatPattern));
		if (!StringUtils.isEmpty(userId)) {
			entityWrapper.and().eq("b.id", userId);
		}
		if (!StringUtils.isEmpty(phoneNo)) {
			entityWrapper.and().eq("b.userName", phoneNo);
		}
		entityWrapper.orderBy("a.create_time", false);
		EntityWrapper<AccountDetailEntity> wrapper = (EntityWrapper<AccountDetailEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);
		int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
				CtcMisUtils.StringToDate(endDates));
		if (getdiffBytwoDate >= ConstantField.max_date_long) {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
			return error;
		}
		page.setRecords(baseMapper.getRecordMx(page, wrapper));
		
		return R.ok().put("page", new PageUtils(page));
	}
	
	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public R queryMxPageTotal(Map<String, Object> params) {
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String userId = (String) params.get("userId");
		String phoneNo = (String) params.get("phoneNo");
		Date beginDate = null;
		if (!StringUtils.isEmpty(beginDates)) {
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
		}

		Date endDate = null;
		if (!StringUtils.isEmpty(endDates)) {
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}
		Page<AccountDetailEntity> page = new Query<AccountDetailEntity>(params).getPage();

		EntityWrapper<AccountDetailEntity> entityWrapper = (EntityWrapper<AccountDetailEntity>) new EntityWrapper<AccountDetailEntity>()
				.and().gt("a.create_time", DateUtils.format(beginDate, ConstantField.dateFormatPattern))
				.lt("a.create_time", DateUtils.format(endDate, ConstantField.dateFormatPattern));
		if (!StringUtils.isEmpty(userId)) {
			entityWrapper.and().eq("b.id", userId);
		}
		if (!StringUtils.isEmpty(phoneNo)) {
			entityWrapper.and().eq("b.userName", phoneNo);
		}
		entityWrapper.orderBy("a.create_time", false);
		EntityWrapper<AccountDetailEntity> wrapper = (EntityWrapper<AccountDetailEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);
		int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
				CtcMisUtils.StringToDate(endDates));
		if (getdiffBytwoDate >= ConstantField.max_date_long) {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
			return error;
		}
		//page.setRecords(baseMapper.getRecordMx(page, wrapper));
		AccountDetailEntity accountDetailEntity = baseMapper.getRecordMxTotal(wrapper);
		
		
		PageUtils p = new PageUtils(page);
		if(accountDetailEntity.getAmountBalance()!=null) {
			p.setTotalDetailsInThisCondition(accountDetailEntity.getAmountBalance().doubleValue());
		}
		if(accountDetailEntity.getCid()!=null) {
			p.setTatalUserCountInThisCondition(accountDetailEntity.getCid().intValue());
		}
		
		
		return R.ok().put("page", p);
		
	}
	
	
	

}
