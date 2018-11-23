package io.credittag.mis.modules.ctc.service.impl;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.DynamicDataSource;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.AddWhiteCustomerDao;
import io.credittag.mis.modules.ctc.dao.CtcCsgjDao;
import io.credittag.mis.modules.ctc.dao.CtcDetailDao;
import io.credittag.mis.modules.ctc.dao.CustomerDao;
import io.credittag.mis.modules.ctc.dao.WithdrawOrderDao;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;
import io.credittag.mis.modules.ctc.entity.WalletDataCount;
import io.credittag.mis.modules.ctc.entity.WithdrawOrderEntity;
import io.credittag.mis.modules.ctc.service.DbDataCountService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;

@Service("dbDataCountServiceImpl")
public class DbDataCountServiceImpl implements DbDataCountService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private AddWhiteCustomerDao addWhiteCustomerDao;
	@Autowired
	private CtcCsgjDao ctcCsgjDao;
	@Autowired
	private CtcDetailDao ctcDetailDao;
	@Autowired
	private WithdrawOrderDao withdrawOrderDao;

	/**
	 * 
	 * @Title: dbDataCount @Description: 担保业务流程统计数据 @param: @param
	 *         params @param: @return @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R dbDataCount(Map<String, Object> params) {

		PageUtils pageUtils = (PageUtils) this.serverFordbDataCount(params).get("page");

		params.put("total", "true");
		params.put("page", null);
		params.put("limit", null);
		// 新加累计领取数量，累计用户
		int usercount = 0;
		int whiteUserCount = 0;
		int completeUserCount = 0;
		int completeCount = 0;
		double ctcCount = 0;
		int withdrawCustomer = 0;
		int withdrawNum = 0;
		double withdrawCtcNum = 0;

		R r = this.serverFordbDataCount(params);
		if ((int) r.get("code") != 0) {
			return R.ok().put("page", pageUtils);
		}
		PageUtils ptotal = (PageUtils) r.get("page");

		if (ptotal == null) {
			DbDataCountEntity total = new DbDataCountEntity();
			total.setVoStrDate("总计");
			total.setCustomerCount("0");
			total.setWhiteCustomerCount("0");
			total.setCompleteCustomer("0");
			total.setCompleteCount("0");
			total.setCtcNum("0");
			total.setWithdrawCustomer("0");
			total.setWithdrawNum("0");
			total.setWithdrawCtcNum("0");
			List<DbDataCountEntity> list1 = new ArrayList<DbDataCountEntity>();// (List<DbDataCountEntity>)
																				// pageUtils.getList();
			list1.add(total);
			return R.ok().put("page", pageUtils);

		}
		List<DbDataCountEntity> list = (List<DbDataCountEntity>) ptotal.getList();

		if (list != null && list.size() > 0) {
			for (DbDataCountEntity dbDataCountEntity : list) {
				usercount += Integer.parseInt(dbDataCountEntity.getCustomerCount());
				whiteUserCount += Integer.parseInt(dbDataCountEntity.getWhiteCustomerCount());
				completeUserCount += Integer.parseInt(dbDataCountEntity.getCompleteCustomer());
				completeCount += Integer.parseInt(dbDataCountEntity.getCompleteCount());
				ctcCount += Double.parseDouble(dbDataCountEntity.getCtcNum());
				withdrawCustomer += Integer.parseInt(dbDataCountEntity.getWithdrawCustomer());
				withdrawNum += Integer.parseInt(dbDataCountEntity.getWithdrawNum());
				withdrawCtcNum += Double.parseDouble(dbDataCountEntity.getWithdrawCtcNum());
			}
		}

		DbDataCountEntity total = new DbDataCountEntity();
		total.setVoStrDate("总计");
		total.setCustomerCount(usercount + "");
		total.setWhiteCustomerCount(whiteUserCount + "");
		total.setCompleteCustomer(completeUserCount + "");
		total.setCompleteCount(completeCount + "");
		total.setCtcNum(ctcCount + "");
		total.setWithdrawCustomer(withdrawCustomer + "");
		total.setWithdrawNum(withdrawNum + "");
		total.setWithdrawCtcNum(withdrawCtcNum + "");
		List<DbDataCountEntity> list1 = (List<DbDataCountEntity>) pageUtils.getList();
		list1.add(total);
		return R.ok().put("page", pageUtils);
	}

	public R serverFordbDataCount(Map<String, Object> params) {
		// 原始参数 北京时间
		Page<DbDataCountEntity> page = new Query<DbDataCountEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");

		// 日期为空时 不返回
		if (StringUtils.isBlank(beginDates) || StringUtils.isBlank(endDates)) {
			return R.ok();
		}

		// 参数 UTC时间
		Date beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
		Date endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));

		int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
				CtcMisUtils.StringToDate(endDates));
		if (getdiffBytwoDate >= ConstantField.max_date_long) {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
			return error;
		}

		List<DbDataCountEntity> walletDataCount = dbDataCount(beginDate, endDate, page, getdiffBytwoDate);
		if (params.get("total") != null && params.get("total").equals("true")) {
			// 进行汇总
			page.setRecords(walletDataCount);
			page.setTotal(walletDataCount.size());
			return R.ok().put("page", new PageUtils(page));
		} else {
			// 查询需要的数据，显示
			Integer index = Integer.parseInt(((String) params.get("page")).trim());
			Integer limit = Integer.parseInt(((String) params.get("limit")).trim());
			List<DbDataCountEntity> walletDataCountNeed = null;
			if (walletDataCount.size() > 0) {
				if (walletDataCount.size() < index * limit) {
					walletDataCountNeed = walletDataCount.subList((index - 1) * limit, walletDataCount.size());
				} else {
					walletDataCountNeed = walletDataCount.subList((index - 1) * limit, index * limit);
				}
			}
			page.setRecords(walletDataCountNeed);
			page.setTotal(walletDataCount.size());
			R ok = R.ok();
			ok.put("page", new PageUtils(page));
			return ok;

		}
	}

	/**
	 * 
	 * @Title: dbDataCount @Description: TODO @param: @param
	 *         beginDate @param: @param endDate @param: @param page @param: @param
	 *         getdiffBytwoDate @param: @return 担保业务流程统计数据 @return:
	 *         List<DbDataCountEntity> @throws
	 */
	private List<DbDataCountEntity> dbDataCount(Date beginDate, Date endDate, Page<DbDataCountEntity> page,
			int getdiffBytwoDate) {

		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setParamMinDate(beginDate);
		customerEntity.setParamMaxDate(endDate);

		// 开通钻石矿场的手机号数量
		List<DbDataCountEntity> customerCount = customerDao.seletCustomerCount(customerEntity);

		// 白名单用户数
		List<DbDataCountEntity> whiteCustomerCount = addWhiteCustomerDao.seletWhiteCustomerCount(customerEntity);

		// 完成任务的用户数
		List<DbDataCountEntity> completeCustomer = ctcCsgjDao.seleteCompleteCustomer(customerEntity);

		// 任务购买次数
		List<DbDataCountEntity> completeCount = ctcCsgjDao.selectCompleteCount(customerEntity);

		// 任务完成赠送CTC的数量
		customerEntity.setReason(ConstantField.missionCsgj);
		List<DbDataCountEntity> ctcNum = ctcDetailDao.selectCtcNum(customerEntity);

		// 提取成功用户数
		List<DbDataCountEntity> withdrawCustomer = withdrawOrderDao.selectWithdrawCustomer(customerEntity);

		// 提取成功次数 提取成功CTC总量
		List<DbDataCountEntity> withdrawCountAndCtcNum = withdrawOrderDao.selectWithdrawCountAndCtcNum(customerEntity);

		List<DbDataCountEntity> res = new ArrayList<DbDataCountEntity>();
		DbDataCountEntity temp = null;
		Date tempDate = null;
		String formatDate = null;
		for (int i = getdiffBytwoDate; i >= 0; i--) {
			temp = new DbDataCountEntity();
			tempDate = DateUtils.addDays(CtcMisUtils.getUTCDateToDate(beginDate), i);
			formatDate = CtcMisUtils.formatDate(tempDate, ConstantField.vodateFormatPattern);

			temp.setCustomerCount("0"); // 开通矿场的手机号数量
			temp.setWhiteCustomerCount("0"); // 白名单用户数
			temp.setCompleteCustomer("0"); // 完成任务的用户数
			temp.setCompleteCount("0"); // 完成次数

			temp.setCtcNum("0"); // 赠送CTC数量
			temp.setWithdrawCustomer("0"); // 提取成功用户数
			temp.setWithdrawNum("0"); // 提取成功次数
			temp.setWithdrawCtcNum("0"); // 提前成功CTC总量

			for (DbDataCountEntity t1 : customerCount) {
				if (StringUtils.equals(t1.getVoStrDate(), formatDate)) {
					temp.setCustomerCount(t1.getCustomerCount()); // 开通矿场的手机号数量
					break;
				}
			}

			for (DbDataCountEntity t1 : whiteCustomerCount) {
				if (StringUtils.equals(t1.getVoStrDate(), formatDate)) {
					temp.setWhiteCustomerCount(t1.getWhiteCustomerCount()); // 白名单用户数
					break;
				}
			}

			for (DbDataCountEntity t1 : completeCustomer) {
				if (StringUtils.equals(t1.getVoStrDate(), formatDate)) {
					temp.setCompleteCustomer(t1.getCompleteCustomer()); // 完成任务的用户数
					break;
				}
			}

			for (DbDataCountEntity t1 : completeCount) {
				if (StringUtils.equals(t1.getVoStrDate(), formatDate)) {
					temp.setCompleteCount(t1.getCompleteCount()); // 完成次数
					break;
				}
			}

			for (DbDataCountEntity t1 : ctcNum) {
				if (StringUtils.equals(t1.getVoStrDate(), formatDate)) {
					temp.setCtcNum(t1.getCtcNum()); // 赠送CTC数量
					break;
				}
			}
			for (DbDataCountEntity t1 : withdrawCustomer) {
				if (StringUtils.equals(t1.getVoStrDate(), formatDate)) {
					temp.setWithdrawCustomer(t1.getWithdrawCustomer());
					break;
				}
			}

			for (DbDataCountEntity t1 : withdrawCountAndCtcNum) {
				if (StringUtils.equals(t1.getVoStrDate(), formatDate)) {
					temp.setWithdrawNum(t1.getWithdrawNum()); // 提取成功次数
					temp.setWithdrawCtcNum(t1.getWithdrawCtcNum()); // 提前成功CTC总量
					break;
				}
			}

			temp.setVoStrDate(formatDate);
			res.add(temp);
		}
		return res;

	}
	@DataSource(name = DataSourceNames.SECOND)
	public List<DbDataCountEntity> dbDataCountforUserdetail(Date beginDate, Date endDate, Page<DbDataCountEntity> page,
			int getdiffBytwoDate) {
		CustomerEntity customerEntity = new CustomerEntity();
		if(beginDate!=null) {
			customerEntity.setParamMinDate(beginDate);
		}
		customerEntity.setParamMaxDate(endDate);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map paramsmap = new HashMap<String,Object>();
		String beginStr = null;
		String endStr = null;
		if(beginDate!=null) {
			beginStr=  sdf.format(beginDate);
		}
		if(endDate!=null) {
			endStr = sdf.format(endDate);
		}
		paramsmap.put("begin",beginStr);
		paramsmap.put("end", endStr);

		// 开通钻石矿场的手机号数量
		List<DbDataCountEntity> customerCount = customerDao.seletCustomerCount(customerEntity);

		// 白名单用户数
		List<DbDataCountEntity> whiteCustomerCount = addWhiteCustomerDao.seletWhiteCustomerCount(customerEntity);
		
		
		//白名单
		Map<String,Integer> whiteusermap = addWhiteCustomerDao.sumWhiteUSer(paramsmap);
		// 完成任务的用户数
		List<DbDataCountEntity> completeCustomer = ctcCsgjDao.seleteCompleteCustomer(customerEntity);
		Map<String,Double> countandcoustomermap = ctcCsgjDao.totalUserDetailDayForAmount(paramsmap);

		// 任务购买次数
		List<DbDataCountEntity> completeCount = ctcCsgjDao.selectCompleteCount(customerEntity);

		// 任务完成赠送CTC的数量
		customerEntity.setReason(ConstantField.missionCsgj);
		List<DbDataCountEntity> ctcNum = ctcDetailDao.selectCtcNum(customerEntity);

		// 提取成功用户数
		List<DbDataCountEntity> withdrawCustomer = withdrawOrderDao.selectWithdrawCustomer(customerEntity);
		

		// 提取成功次数 提取成功CTC总量
		List<DbDataCountEntity> withdrawCountAndCtcNum = withdrawOrderDao.selectWithdrawCountAndCtcNum(customerEntity);
		Map<String,Integer> withdrawCountAndCtcNummap = withdrawOrderDao.sumCTC(paramsmap);

		List<DbDataCountEntity> res = new ArrayList<DbDataCountEntity>();

		DbDataCountEntity temp = new DbDataCountEntity();
		Date tempDate = null;
		String formatDate = null;

		temp.setCustomerCount("0"); // 开通矿场的手机号数量
		temp.setWhiteCustomerCount("0"); // 白名单用户数
		temp.setCompleteCustomer("0"); // 完成任务的用户数
		temp.setCompleteCount("0"); // 完成次数

		temp.setCtcNum("0"); // 赠送CTC数量
		temp.setWithdrawCustomer("0"); // 提取成功用户数
		temp.setWithdrawNum("0"); // 提取成功次数
		temp.setWithdrawCtcNum("0"); // 提前成功CTC总量

		if(whiteusermap!=null && whiteusermap.get("count")!=null && !"".equals(whiteusermap.get("count")) && !"null".equals(whiteusermap.get("count"))) {
			temp.setWhiteCustomerCount(whiteusermap.get("count")+"");
		}else {
			temp.setWhiteCustomerCount("0");
		}
		
		if(countandcoustomermap!=null && countandcoustomermap.get("customer")!=null && !"".equals(countandcoustomermap.get("customer")) && !"null".equals(countandcoustomermap.get("customer"))) {
			temp.setCompleteCustomer(countandcoustomermap.get("customer")+"");
		}else {
			temp.setCompleteCustomer("0");
		}
		
		if(countandcoustomermap!=null && countandcoustomermap.get("count")!=null && !"".equals(countandcoustomermap.get("count")) && !"null".equals(countandcoustomermap.get("count"))) {
			temp.setCompleteCount(countandcoustomermap.get("count")+"");
		}else {
			temp.setCompleteCount("0");
		}
		
		if(withdrawCountAndCtcNummap!=null && withdrawCountAndCtcNummap.get("drawusers")!=null && !"".equals(withdrawCountAndCtcNummap.get("drawusers")) && !"null".equals(withdrawCountAndCtcNummap.get("drawusers"))) {
			temp.setWithdrawCustomer(withdrawCountAndCtcNummap.get("drawusers")+"");//提取成功用户数
		}else {
			temp.setWithdrawCustomer("0");//提取成功用户数
		}
		
		if(withdrawCountAndCtcNummap!=null && withdrawCountAndCtcNummap.get("count")!=null && !"".equals(withdrawCountAndCtcNummap.get("count")) && !"null".equals(withdrawCountAndCtcNummap.get("count"))) {
			temp.setWithdrawNum(withdrawCountAndCtcNummap.get("count")+""); // 提取成功次数
		}else {
			temp.setWithdrawNum("0"); // 提取成功次数
		}
		if(withdrawCountAndCtcNummap!=null && withdrawCountAndCtcNummap.get("amount")!=null && !"".equals(withdrawCountAndCtcNummap.get("amount")) && !"null".equals(withdrawCountAndCtcNummap.get("amount"))) {
			temp.setWithdrawCtcNum(withdrawCountAndCtcNummap.get("amount")+""); // 提前成功CTC总量amount
		}else {
			temp.setWithdrawCtcNum("0"); // 提前成功CTC总量amount
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();
		temp.setVoStrDate(sdf.format(date));
		res.add(temp);

		return res;

	}


}
