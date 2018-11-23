package io.credittag.mis.modules.ctc.service.impl;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.TblBcTransactionDao;
import io.credittag.mis.modules.ctc.dao.WalletAssetDao;
import io.credittag.mis.modules.ctc.dao.WalletTranDao;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.entity.TblBcTransactionEntity;
import io.credittag.mis.modules.ctc.entity.WalletAssetEntity;
import io.credittag.mis.modules.ctc.entity.WalletDataCount;
import io.credittag.mis.modules.ctc.entity.WalletTranEntity;
import io.credittag.mis.modules.ctc.service.WalletAssetService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service("walletAssetService")
public class WalletAssetServiceImpl extends ServiceImpl<WalletAssetDao, WalletAssetEntity> implements WalletAssetService {

	@Autowired
	private WalletAssetDao walletAssetDao;

	@Autowired
	private WalletTranDao walletTranDao;
	
	@Resource
	private TblBcTransactionDao tblBcTransactionDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<WalletAssetEntity> page = this.selectPage(new Query<WalletAssetEntity>(params).getPage(), new EntityWrapper<WalletAssetEntity>());

		return new PageUtils(page);
	}

	/**
	 * 
	 * @Title: walletDataCount
	 * @Description: 钱包统计
	 * @param: @param params
	 * @param: @return
	 * @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.FOUR)
	public R walletDataCount(Map<String, Object> params) {
		return this.serverForwalletDataCount(params);
	}
	
	public R serverForwalletDataCount(Map<String, Object> params) {
		// 原始参数 北京时间
		if(params.get("total")!=null&&params.get("total").equals("true")) {
			params.put("page", null);
			params.put("limit", null);
		}
		Page<WalletDataCount> page = new Query<WalletDataCount>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		// 日期为空时 不返回
		if (StringUtils.isBlank(beginDates) || StringUtils.isBlank(endDates)) {
			return R.ok();
		}
		// 参数 UTC时间
		Date beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
		Date endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates), CtcMisUtils.StringToDate(endDates));
		if (getdiffBytwoDate >= ConstantField.max_date_long) {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
			return error;
		}
		List<WalletDataCount> walletDataCount = walletDataCount(beginDate, endDate, page, getdiffBytwoDate);
		 //根绝page 和 limit返回数据
		if(params.get("total")!=null&&params.get("total").equals("true")) {
			
			page.setRecords(walletDataCount);
			page.setTotal(walletDataCount.size());
			R ok = R.ok();
			ok.put("page", new PageUtils(page));
	/*		ok.put("beginDate",beginDates);
			ok.put("endDate", endDates);*/
			return ok;
		}else {
			Integer index =  Integer.parseInt(((String) params.get("page")).trim());
			Integer limit =  Integer.parseInt(((String) params.get("limit")).trim());
			
			List<WalletDataCount> walletDataCountNeed =null;
			if(walletDataCount.size()>0) {
				if(walletDataCount.size()<index*limit) {
					walletDataCountNeed = walletDataCount.subList((index-1)*limit, walletDataCount.size());
				}else {
					walletDataCountNeed = walletDataCount.subList((index-1)*limit, index*limit);
				}
			}
			
			page.setRecords(walletDataCountNeed);
			page.setTotal(walletDataCount.size());
			R ok = R.ok();
			ok.put("page", new PageUtils(page));
	/*		ok.put("beginDate",beginDates);
			ok.put("endDate", endDates);*/
			return ok;
		}
	}
	
	@Override
	@DataSource(name = DataSourceNames.FOUR)
	public R walletDataCountTotal(Map<String, Object> params) {
		
		params.put("total","true");
		
		
		R r = this.serverForwalletDataCount(params);
		if((int) r.get("code")!=0) {
			
			return R.ok().put("page", new PageUtils(new Page<WalletDataCount>()));
		}
		PageUtils ptotal = (PageUtils) r.get("page");
		
		//新加累计领取数量，累计用户
				int invitusercount = 0;
				double invitedusercountAndInit = 0;
				int invitedUserCountAndlogin = 0;
				int tranAddressCount = 0;
				
				List<WalletDataCount> list = (List<WalletDataCount>) ptotal.getList() ;
				if(list!=null&&list.size()>0) {
					for(WalletDataCount walletDataCount : list) {
						invitusercount += Integer.parseInt(walletDataCount.getWalletAddressCount());
					    invitedusercountAndInit += Double.parseDouble(walletDataCount.getTranAmtCount());
						invitedUserCountAndlogin += Integer.parseInt(walletDataCount.getTranCount());
						tranAddressCount += Integer.parseInt(walletDataCount.getTranAddressCount());
					}
				}
				ptotal.setBuyCount(invitusercount);
				ptotal.setMoneyCount(invitedusercountAndInit);
				ptotal.setTatalUserCountInThisCondition(invitedUserCountAndlogin);
				ptotal.setExchangeNum(tranAddressCount);
				
				return R.ok().put("page", ptotal);
	}
	

	/**
	 * 
	 * @Title: walletDataCount
	 * @Description: 钱包统计
	 * @param: @param beginDate 开始日期
	 * @param: @param endDate 结束日期
	 * @param: @param page
	 * @param: @param getdiffBytwoDate 两个日期间隔
	 * @return: void
	 * @throws
	 */
	private List<WalletDataCount> walletDataCount(Date beginDate, Date endDate, Page<WalletDataCount> page, int getdiffBytwoDate) {

		WalletAssetEntity walletAssetEntity = new WalletAssetEntity();
		walletAssetEntity.setParamMinDate(beginDate);
		walletAssetEntity.setParamMaxDate(endDate);

		// 新增钱包地址数
		List<WalletDataCount> walletAddressCountByDate = walletAssetDao.getWalletAddressCountByDate(walletAssetEntity);

		// 钱包交易数 和 交易金额 交易地址数
		WalletTranEntity walletTranEntity = new WalletTranEntity();
		walletTranEntity.setParamMinDate(beginDate);
		walletTranEntity.setParamMaxDate(endDate);
		List<WalletDataCount> tranInfo = walletTranDao.getTranInfo(walletTranEntity);

		// 交易地址数
		List<WalletDataCount> tranAddressCount = walletTranDao.getTranAddressCount(walletTranEntity);

		List<WalletDataCount> res = new ArrayList<WalletDataCount>();
		WalletDataCount temp = null;
		Date tempDate = null;
		String formatDate = null;
		for (int i = getdiffBytwoDate; i >= 0; i--) {
			temp = new WalletDataCount();
			tempDate = DateUtils.addDays(CtcMisUtils.getUTCDateToDate(beginDate), i);
			formatDate = CtcMisUtils.formatDate(tempDate, ConstantField.vodateFormatPattern);

			temp.setTranAddressCount("0"); // 交易地址
			temp.setTranAmtCount("0"); // 交易金额
			temp.setTranCount("0"); // 交易数
			temp.setWalletAddressCount("0"); // 新增钱包地址数

			// 新增钱包地址数
			for (WalletDataCount t1 : walletAddressCountByDate) {
				if (StringUtils.equals(t1.getVoStrDate(), formatDate)) {
					temp.setWalletAddressCount(t1.getWalletAddressCount());
					break;
				}
			}

			// 交易金额 和 交易笔数
			for (WalletDataCount t2 : tranInfo) {
				if (StringUtils.equals(t2.getVoStrDate(), formatDate)) {
					temp.setTranAmtCount(t2.getTranAmtCount());
					temp.setTranCount(t2.getTranCount());
					break;
				}
			}

			// 交易地址数
			for (WalletDataCount t3 : tranAddressCount) {
				if (StringUtils.equals(t3.getVoStrDate(), formatDate)) {
					temp.setTranAddressCount(t3.getTranAddressCount());
					break;
				}
			}

			temp.setVoStrDate(formatDate);
			res.add(temp);
		}
		return res;

	}
	
	/**
	 * 获得资产列表
	 */
	@Override
	@DataSource(name = DataSourceNames.FOUR)
	public List<WalletAssetEntity> selectList() {
		return walletAssetDao.selectList();
	}

	@Override
	@DataSource(name = DataSourceNames.FOUR)
	public  R ranklist(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String orderby = (String) params.get("orderby");
		String begintime = (String) params.get("begintime");
		String endtime = (String) params.get("endtime");
		String beginnumStr = (String) params.get("beginnum");
		String endnumStr = (String) params.get("endnum");
		String walletaddr = (String) params.get("walletaddr");
		int beginnum = 1;
		int endnum = 0;
		try {
			if(beginnumStr != null&& !"".equals(beginnumStr.trim())) {
				beginnum = Integer.parseInt(beginnumStr.trim());
			}
			if(endnumStr !=null && !"".equals(endnumStr.trim())) {
				endnum = Integer.parseInt(endnumStr.trim());
			}
			if(beginnum>endnum||beginnum<1||endnum<1) {
				return R.error("请输入正确的排名");
			}
		}catch(Exception e) {
			return R.error("请输入正确的排名");
		}
		
		
		
		Map<String,Object> paramsmap = new HashMap<String,Object>();
		paramsmap.put("beginnum", beginnum-1);
		//paramsmap.put("endnum",endnum );
		paramsmap.put("total",endnum-beginnum+1);
		paramsmap.put("orderby", orderby);
		paramsmap.put("begintime", begintime);
		paramsmap.put("endtime", endtime);
		paramsmap.put("walletaddr", walletaddr);
		List<WalletAssetEntity> list = walletAssetDao.ranklist( paramsmap);
		//Page<WalletAssetEntity> page = new Query<WalletAssetEntity>(params).getPage();
		Page<WalletAssetEntity> page = new Page<WalletAssetEntity>();
		page.setSize(Integer.MAX_VALUE);
		page.setRecords(list);
		PageUtils p = new PageUtils(page);
		p.setTotalPage(1);
		return R.ok().put("page", p );
		
	}

	@Override
	@DataSource(name = DataSourceNames.THREE)
	public R rankDetail(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String addr = (String) params.get("addr");
		String fromOrIn = (String) params.get("fromOrIn");
		if(addr==null || addr.equals("")) {
			return R.error("钱包=地址无效");
		}
		Map<String ,Object> paramsmap = new HashMap<String,Object>();
		paramsmap.put("addr", addr);
		paramsmap.put("from", false);
		paramsmap.put("to", false);
		if(fromOrIn!=null && fromOrIn.equals("from")) {
			paramsmap.put("from", true);
		}
		if(fromOrIn!=null && fromOrIn.equals("to")) {
			paramsmap.put("to", true);
		}
		if(fromOrIn!=null && fromOrIn.equals("all")) {
			paramsmap.put("from", true);
			paramsmap.put("to", true);
		}
		
		List<TblBcTransactionEntity> list = tblBcTransactionDao.getTranDetailByAddr(paramsmap);
		Page<TblBcTransactionEntity> page = new Page<TblBcTransactionEntity>();
		page.setRecords(list);
		TblBcTransactionEntity tblBcTransactionEntity = tblBcTransactionDao.totalgetTranDetailByAddr(paramsmap);
		PageUtils p = new PageUtils(page);
		p.setMoneyCount(tblBcTransactionEntity.getAmount());
		p.setTotalDetailsInThisCondition(tblBcTransactionEntity.getFee());
		return R.ok().put("page", p );
	}

}
