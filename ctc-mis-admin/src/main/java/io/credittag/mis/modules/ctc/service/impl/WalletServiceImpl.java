package io.credittag.mis.modules.ctc.service.impl;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.WalletDao;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.entity.WalletEntity;
import io.credittag.mis.modules.ctc.entity.WalletTranEntity;
import io.credittag.mis.modules.ctc.service.WalletService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service("walletService")
public class WalletServiceImpl extends ServiceImpl<WalletDao, WalletEntity> implements WalletService {

	@Autowired
	private WalletDao walletDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<WalletEntity> page = this.selectPage(new Query<WalletEntity>(params).getPage(), new EntityWrapper<WalletEntity>());

		return new PageUtils(page);
	}

	/**
	 * @Title: walletList
	 * @Description: 新增钱包明细(查询条件为空时，默认查询当天的)
	 * @param: @param params
	 * @param: @return
	 * @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.FOUR)
	public R walletList(Map<String, Object> params) {

		return  this.serverForwalletList(params);
		
	}
	public R serverForwalletList(Map<String, Object> params) {

		Page<WalletEntity> page = new Query<WalletEntity>(params).getPage();

		// 传递过来的参数 开始时间 结束时间 钱包地址
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String walletAddress = (String) params.get("walletAddress");

		Date beginDate = null;
		boolean b1 = false;
		if(StringUtils.isNotBlank(beginDates)){
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
			b1 =  true;
		}

		Date endDate = null;
		boolean b2 = false;
		if(StringUtils.isNotBlank(endDates)){
			b2= true;
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}
		
		boolean b3 = false;
		if (StringUtils.isNotBlank(walletAddress)) {
			walletAddress = walletAddress.trim();
			b3 = true;
		}
		
		// 查询条件都为空时 不返回
		if(!b1 && !b2 && !b3){
			return R.ok();
		}
		
		EntityWrapper<WalletEntity> entityWrapper = null;
		if(b1 && b2){
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates), CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}
			
			 entityWrapper = (EntityWrapper<WalletEntity>) new EntityWrapper<WalletEntity>().ge(beginDate != null, "a.create_time", beginDate)
			        .le(endDate != null, "a.create_Time", endDate).like(StringUtils.isNotBlank(walletAddress), "b.coin_addr", walletAddress,SqlLike.DEFAULT).isWhere(true);
			
		}else if(b3){
			entityWrapper = (EntityWrapper<WalletEntity>) new EntityWrapper<WalletEntity>().like(StringUtils.isNotBlank(walletAddress), "b.coin_addr", walletAddress,SqlLike.DEFAULT).isWhere(true);
		}else{
			return R.ok();
		}


		EntityWrapper<WalletEntity> wrapper = (EntityWrapper<WalletEntity>) SqlHelper.fillWrapper(page, entityWrapper);

		page.setRecords(walletDao.walletList(page, wrapper));


		R ok = R.ok();
		// return ;
		ok.put("page", new PageUtils(page));
/*		ok.put("beginDate", CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(beginDate), ConstantField.dateFormatPattern));
		ok.put("endDate", CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(endDate), ConstantField.dateFormatPattern));
		ok.put("walletAddress", walletAddress);*/

		return ok;
	}
	
	@Override
	@DataSource(name = DataSourceNames.FOUR)
	public R walletListTotal(Map<String, Object> params) {

		Page<WalletEntity> page = new Query<WalletEntity>(params).getPage();

		// 传递过来的参数 开始时间 结束时间 钱包地址
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String walletAddress = (String) params.get("walletAddress");

		Date beginDate = null;
		boolean b1 = false;
		if(StringUtils.isNotBlank(beginDates)){
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
			b1 =  true;
		}

		Date endDate = null;
		boolean b2 = false;
		if(StringUtils.isNotBlank(endDates)){
			b2= true;
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}
		
		boolean b3 = false;
		if (StringUtils.isNotBlank(walletAddress)) {
			walletAddress = walletAddress.trim();
			b3 = true;
		}
		
		// 查询条件都为空时 不返回
		if(!b1 && !b2 && !b3){
			return R.ok();
		}
		
		EntityWrapper<WalletEntity> entityWrapper = null;
		if(b1 && b2){
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates), CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}
			
			 entityWrapper = (EntityWrapper<WalletEntity>) new EntityWrapper<WalletEntity>().ge(beginDate != null, "a.create_time", beginDate)
			        .le(endDate != null, "a.create_Time", endDate).like(StringUtils.isNotBlank(walletAddress), "b.coin_addr", walletAddress,SqlLike.DEFAULT).isWhere(true);
			
		}else if(b3){
			entityWrapper = (EntityWrapper<WalletEntity>) new EntityWrapper<WalletEntity>().like(StringUtils.isNotBlank(walletAddress), "b.coin_addr", walletAddress,SqlLike.DEFAULT).isWhere(true);
		}else{
			return R.ok();
		}


		EntityWrapper<WalletEntity> wrapper = (EntityWrapper<WalletEntity>) SqlHelper.fillWrapper(page, entityWrapper);

		//page.setRecords(walletDao.walletList(page, wrapper));
		WalletEntity walletEntity = walletDao.walletListTotal(wrapper);
		
		PageUtils p = new PageUtils(page);
		if(walletEntity.getWalletid()!=null) {
			p.setTatalUserCountInThisCondition(Integer.parseInt(walletEntity.getWalletid()));
		}
		if(walletEntity.getCoinaddr()!=null) {
			p.setBuyCount(Integer.parseInt(walletEntity.getCoinaddr()));
		}
		R ok = R.ok();
		// return ;
		ok.put("page", p);


		return ok;
		
	}

}
