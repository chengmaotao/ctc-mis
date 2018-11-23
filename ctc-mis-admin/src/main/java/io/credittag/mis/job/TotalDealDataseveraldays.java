package io.credittag.mis.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.DynamicDataSource;
import io.credittag.mis.modules.ctc.dao.CtcCsgjDao;
import io.credittag.mis.modules.ctc.dao.CustomerDao;
import io.credittag.mis.modules.ctc.dao.TblBcTransactionDao;
import io.credittag.mis.modules.ctc.dao.TotalDealDayDao;
import io.credittag.mis.modules.ctc.dao.TotalUserdetailDayDao;
import io.credittag.mis.modules.ctc.dao.WalletAssetDao;
import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;
import io.credittag.mis.modules.ctc.entity.TotalDealDayEntity;
import io.credittag.mis.modules.ctc.entity.WalletAssetEntity;
import io.credittag.mis.modules.ctc.service.impl.DbDataCountServiceImpl;

@Component("totalDealDataseveraldays")
public class TotalDealDataseveraldays {
	@Resource
	private TotalDealDayDao totalDealDayDao;
	@Resource
	private TotalUserdetailDayDao totalUserdetailDayDao;
	@Resource
	private TblBcTransactionDao tblBcTransactionDao;
	@Resource
	private CustomerDao customerDao;
	@Resource
	private DbDataCountServiceImpl dbDataCountServiceImpl;
	@Resource
	private WalletAssetDao walletAssetDao;
	@Resource
	private CtcCsgjDao ctcCsgjDao;

	// 1
	public TotalDealDayEntity totaldealtotal(int days) {
		DynamicDataSource.setDataSource(DataSourceNames.THREE);
		// 格林威治时间
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		// 计算前一天的时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 0 - days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date begindate = calendar.getTime();
		String begindateStr = sdf.format(begindate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date enddate = calendar.getTime();
		String enddateStr = sdf.format(enddate);
		map.put("begin", begindateStr);
		map.put("end", enddateStr);
		TotalDealDayEntity totalDealDayEntity = null;
		
		if (days == 0) {
			map.put("begin", null);
			totalDealDayEntity = tblBcTransactionDao.totalDealDay(map);
		} else {
			totalDealDayEntity = tblBcTransactionDao.totalDealDay(map);
			//totalDealDayEntity = new TotalDealDayEntity();
		}
		

		// 查询余额大于0的钱包地址数
		if (totalDealDayEntity == null) {
			totalDealDayEntity = new TotalDealDayEntity();
			totalDealDayEntity.setCreateTime(begindate);
		}
		
			totalDealDayEntity.setCreateTime(begindate);
			
			DynamicDataSource.setDataSource(DataSourceNames.FOUR);
			Map<String, Long> resultmap = null;
			Map<String,Long> addrAll = new HashMap<String,Long>();
			Map<String,Long> fromOrtoCountmap = new HashMap<String,Long>();
			List<WalletAssetEntity> walletamt0list = null;
			if (days == 0) {
				map.put("begin", null);
				map.put("end", enddateStr);
				resultmap = walletAssetDao.queryMoneyMore0(map);
				 walletamt0list = walletAssetDao.totalDealDayAMT0(map);
				 addrAll =  walletAssetDao.totalDealDayAMTAll(map);
				 DynamicDataSource.setDataSource(DataSourceNames.THREE);
				fromOrtoCountmap =tblBcTransactionDao.addrFromAndToCount(map);
			} else {
				resultmap = walletAssetDao.queryMoneyMore0(map);
				walletamt0list = walletAssetDao.totalDealDayAMT0(map);
				addrAll =  walletAssetDao.totalDealDayAMTAll(map);
				 DynamicDataSource.setDataSource(DataSourceNames.THREE);
				fromOrtoCountmap =tblBcTransactionDao.addrFromAndToCount(map);
			}
			if (resultmap != null) {
				if (resultmap.get("count") != null && 0 != resultmap.get("count")) {
					totalDealDayEntity.setAmountCtcWalletAddr(resultmap.get("count"));
				} else {
					totalDealDayEntity.setAmountCtcWalletAddr(0l);
				}

			}else {
				totalDealDayEntity.setAmountCtcWalletAddr(0l);
			}
			
			
			if( walletamt0list!=null &&  walletamt0list.size()>0) {
				List<String> addrList = new ArrayList<String>();
				for(WalletAssetEntity walletAssetEntity : walletamt0list) {
					addrList.add(walletAssetEntity.getCoinaddr());
				}
				//查找交易记录
				Map<String,Object> AMT0buttranmap = new HashMap<String,Object>();
				Map<String,Long> countmap =null;
				if (days == 0) {
					AMT0buttranmap.put("begin",null);
					AMT0buttranmap.put("end", enddateStr);
					
					AMT0buttranmap.put("list",addrList );
					DynamicDataSource.setDataSource(DataSourceNames.THREE);
					 countmap  = tblBcTransactionDao.getTranDetailByAddrTransed(AMT0buttranmap);
				} else {
					AMT0buttranmap.put("begin", begindateStr);
					AMT0buttranmap.put("end", enddateStr);
					AMT0buttranmap.put("list", addrList);
					DynamicDataSource.setDataSource(DataSourceNames.THREE);
					countmap  = tblBcTransactionDao.getTranDetailByAddrTransed(AMT0buttranmap);
				}
				if(countmap !=null && countmap.get("count")!=null) {
					totalDealDayEntity.setExtra(countmap.get("count")+"");
				}else {
					totalDealDayEntity.setExtra("0");
				}
				
			}else {
				totalDealDayEntity.setExtra("0");
				
			}
			//总的新怎地址
			if(addrAll!=null && addrAll.get("count")!=null && !"".equals(addrAll.get("count")) && !"null".equals("count")) {
				totalDealDayEntity.setAmountCtcWalletAddrAll(addrAll.get("count"));
			}else {
				totalDealDayEntity.setAmountCtcWalletAddrAll(0l);
			}
			//转入转出地址统计
			if(fromOrtoCountmap.get("fromCount")!=null) {
				totalDealDayEntity.setAmountCtcWalletAddrFrom(fromOrtoCountmap.get("fromCount"));
			}else {
				totalDealDayEntity.setAmountCtcWalletAddrFrom(0l);
			}
			if(fromOrtoCountmap.get("toCount")!=null) {
				totalDealDayEntity.setAmountCtcWalletAddrTo(fromOrtoCountmap.get("toCount"));
			}else {
				totalDealDayEntity.setAmountCtcWalletAddrTo(0l);
			}
		
		return totalDealDayEntity;
	}

	// 2
	public DbDataCountEntity totaluser(int days) {
		List<DbDataCountEntity> list = totalUserDetailDay(days);
		if (list != null && list.size() > 0) {
			DbDataCountEntity dbDataCountEntity = list.get(0);
			// 添加 用户详情
			Map<String, String> map = totalUserDetailDaySup(days);
			if (map != null && map.get("1") != null) {
				dbDataCountEntity.setCustomerRegister(map.get("1"));
			} else {
				dbDataCountEntity.setCustomerRegister("0");
			}
			if (map != null && map.get("9") != null) {
				dbDataCountEntity.setCustomerLogin(map.get("9"));
			} else {
				dbDataCountEntity.setCustomerLogin("0");
			}
			

			Date date = new Date();
			// 计算前一天的时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, 0-days);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date vodate = calendar.getTime();
			String vodateStr = sdf.format(vodate);
			dbDataCountEntity.setVoStrDate(vodateStr);
			// 查询任务总金额
			dbDataCountEntity.setCompleteMoney("0");
			Map<String, Double> amountmap = totalUserDetailDayForAmount(days);
			if (amountmap != null && !"".equals(amountmap.get("amount"))) {
				dbDataCountEntity.setCompleteMoney(amountmap.get("amount") + "");
			} else {
				dbDataCountEntity.setCompleteMoney(0 + "");
			}

			return dbDataCountEntity;
		}
		DbDataCountEntity dbDataCountEntity  = new DbDataCountEntity();
		dbDataCountEntity.setCustomerRegister("0");
		dbDataCountEntity.setCustomerLogin("0");
		dbDataCountEntity.setWhiteCustomerCount("0");
		dbDataCountEntity.setCompleteCustomer("0");
		dbDataCountEntity.setCompleteCount("0");
		dbDataCountEntity.setCompleteMoney("0");
		dbDataCountEntity.setWhiteCustomerCount("0");
		dbDataCountEntity.setWithdrawNum("0");
		dbDataCountEntity.setWithdrawCtcNum("0");
		
		return dbDataCountEntity;

	}

	public List<DbDataCountEntity> totalUserDetailDay(int days) {
		DynamicDataSource.setDataSource(DataSourceNames.SECOND);
		// Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		// 计算前一天的时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1 - days);
		calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date begindate = calendar.getTime();
		String begindateStr = sdf.format(begindate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date enddate = calendar.getTime();
		List<DbDataCountEntity> list = null;
		if (days == 0) {
			list = dbDataCountServiceImpl.dbDataCountforUserdetail(null, enddate, null, 1);
		} else {
			list = dbDataCountServiceImpl.dbDataCountforUserdetail(begindate, enddate, null, 1);
		}

		return list;

	}

	public Map<String, String> totalUserDetailDaySup(int days) {
		DynamicDataSource.setDataSource(DataSourceNames.SECOND);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		// 计算前一天的时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1 - days);
		calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date begindate = calendar.getTime();
		String begindateStr = sdf.format(begindate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date enddate = calendar.getTime();
		String endStr = sdf.format(enddate);
		Map<String, String> resultmap = new HashMap<String, String>();
		Map mapparam = new HashMap<String, String>();
		mapparam.put("begin", begindateStr);
		mapparam.put("end", endStr);
		List<Map<Integer, Integer>> list = null;
		if (days == 0) {
			mapparam.put("begin", null);
			list = customerDao.totalUserDetailDaySup(mapparam);
		} else {
			list = customerDao.totalUserDetailDaySup(mapparam);
		}
		if (list != null && list.size() > 0) {
			for (Map<Integer, Integer> map : list) {
				if (map.get("status") != null && 0 == map.get("status")) {
					resultmap.put("0", map.get("count") + "");
				}
				if (map.get("status") != null && 1 == (map.get("status"))) {
					// 已注册
					resultmap.put("1", map.get("count") + "");
				}
				if (map.get("status") != null && 9 == (map.get("status"))) {
					resultmap.put("9", map.get("count") + "");
				}

				if (map.get("status") != null && 2 == (map.get("status"))) {
					resultmap.put("2", map.get("count") + "");
				}

			}
		} else {
			resultmap.put("0", "0");
			resultmap.put("1", "0");
			resultmap.put("2", "0");
			resultmap.put("9", "0");
		}
		return resultmap;

	}

	public Map<String, Double> totalUserDetailDayForAmount(int days) {
		DynamicDataSource.setDataSource(DataSourceNames.SECOND);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		// 计算前一天的时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1 - days);
		calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date begindate = calendar.getTime();
		String beginStr = sdf.format(begindate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date enddate = calendar.getTime();
		String endStr = sdf.format(enddate);
		Map<String, String> parammap = new HashMap<String, String>();
		parammap.put("begin", beginStr);
		parammap.put("end", endStr);
		Map<String, Double> map = null;
		if (days == 0) {
			parammap.put("begin", null);
			map = ctcCsgjDao.totalUserDetailDayForAmount(parammap);
		} else {
			map = ctcCsgjDao.totalUserDetailDayForAmount(parammap);
		}
		return map;
	}

	public void TotalUserDetailDayall(int days) {
		//
		List<DbDataCountEntity> list = totalUserDetailDay(days);
		if (list != null && list.size() > 0) {
			DbDataCountEntity dbDataCountEntity = list.get(0);
			// 添加 用户详情
			Map<String, String> map = totalUserDetailDaySup(days);
			if (map != null && map.get("1") != null) {
				dbDataCountEntity.setCustomerRegister(map.get("1"));
			} else {
				dbDataCountEntity.setCustomerRegister("0");
			}
			if (map != null && map.get("9") != null) {
				dbDataCountEntity.setCustomerLogin(map.get("9"));
			} else {
				dbDataCountEntity.setCustomerLogin("0");
			}
			dbDataCountEntity.setCompleteMoney("0");

			Date date = new Date();
			// 计算前一天的时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date vodate = calendar.getTime();
			String vodateStr = sdf.format(vodate);
			dbDataCountEntity.setVoStrDate(vodateStr);
			// 查询任务总金额
			Map<String, Double> amountmap = totalUserDetailDayForAmount(days);
			if (amountmap != null && !"".equals(amountmap.get("amount"))) {
				dbDataCountEntity.setCompleteMoney(amountmap.get("amount") + "");
			} else {
				dbDataCountEntity.setCompleteMoney(0 + "");
			}
			//DynamicDataSource.setDataSource(DataSourceNames.FIRST);

			// insertTotalUserDetailDay(dbDataCountEntity);
		} else {
			System.out.println("totalUserDetail无数据");
		}

	}
}
