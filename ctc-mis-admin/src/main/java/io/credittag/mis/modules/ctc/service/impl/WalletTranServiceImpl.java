package io.credittag.mis.modules.ctc.service.impl;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.WalletTranDao;
import io.credittag.mis.modules.ctc.entity.WalletTranEntity;
import io.credittag.mis.modules.ctc.service.WalletTranService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service("walletTranService")
public class WalletTranServiceImpl extends ServiceImpl<WalletTranDao, WalletTranEntity> implements WalletTranService {

	@Autowired
	private WalletTranDao walletTranDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<WalletTranEntity> page = this.selectPage(new Query<WalletTranEntity>(params).getPage(), new EntityWrapper<WalletTranEntity>());

		return new PageUtils(page);
	}

	/**
	 * @Title: wallettranList
	 * @Description: 交易明细
	 * @param: @param params
	 * @param: @return
	 * @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.FOUR)
	public R wallettranList(Map<String, Object> params) {

		Page<WalletTranEntity> page = new Query<WalletTranEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");

		// 日期都为空时 不返回
		if (StringUtils.isBlank(beginDates) && StringUtils.isBlank(endDates)) {
			return R.ok();
		}
		
		
		Date beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
		Date endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		
		int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates), CtcMisUtils.StringToDate(endDates));
		if (getdiffBytwoDate >= ConstantField.max_date_long) {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
			return error;
		}

		EntityWrapper<WalletTranEntity> entityWrapper = (EntityWrapper<WalletTranEntity>) new EntityWrapper<WalletTranEntity>().ge(beginDate != null, "a.create_time", beginDate)
		        .le(endDate != null, "a.create_time", endDate).isWhere(true);

		EntityWrapper<WalletTranEntity> wrapper = (EntityWrapper<WalletTranEntity>) SqlHelper.fillWrapper(page, entityWrapper);

		page.setRecords(walletTranDao.wallettranList(page, wrapper));

		R ok = R.ok();
		ok.put("page", new PageUtils(page));
/*		ok.put("beginDate", CtcMisUtils.formatDate(beginDate, ConstantField.dateFormatPattern));
		ok.put("endDate", CtcMisUtils.formatDate(endDate, ConstantField.dateFormatPattern));*/
		//((PageUtils)ok.get("page")).setPageSize(11);
		return ok;
	}
	
	 
		@Override
		@DataSource(name = DataSourceNames.FOUR)
		public R wallettranListTotal(Map<String, Object> params) {

			Page<WalletTranEntity> page = new Query<WalletTranEntity>(params).getPage();
			String beginDates = (String) params.get("beginDate");
			String endDates = (String) params.get("endDate");

			// 日期都为空时 不返回
			if (StringUtils.isBlank(beginDates) && StringUtils.isBlank(endDates)) {
				return R.ok();
			}
			
			
			Date beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
			Date endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
			
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates), CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			EntityWrapper<WalletTranEntity> entityWrapper = (EntityWrapper<WalletTranEntity>) new EntityWrapper<WalletTranEntity>().ge(beginDate != null, "a.create_time", beginDate)
			        .le(endDate != null, "a.create_time", endDate).isWhere(true);

			EntityWrapper<WalletTranEntity> wrapper = (EntityWrapper<WalletTranEntity>) SqlHelper.fillWrapper(page, entityWrapper);

	
			WalletTranEntity walletTranEntity = walletTranDao.wallettranListTotal(wrapper);
			PageUtils p = new PageUtils(page);
			if(walletTranEntity.getTranfee()!=null) {
				//暂时用来传递手续费总计
				p.setCtcCount(walletTranEntity.getTranfee().doubleValue());
			}
			
			if(walletTranEntity.getTranamt()!=null) {
				//暂时传递总交易金额
				p.setMoneyCount(walletTranEntity.getTranamt().doubleValue());
			}
			R ok = R.ok();
			ok.put("page", p);
			
			
			return ok;
		}

}
