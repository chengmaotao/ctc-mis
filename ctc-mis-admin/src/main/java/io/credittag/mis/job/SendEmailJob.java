package io.credittag.mis.job;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.baomidou.mybatisplus.plugins.Page;

import io.credittag.mis.common.utils.SendDailyEmail;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.DynamicDataSource;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.dao.CtcCsgjDao;
import io.credittag.mis.modules.ctc.dao.CustomerDao;
import io.credittag.mis.modules.ctc.dao.TblBcTransactionDao;
import io.credittag.mis.modules.ctc.dao.TotalDealDayDao;
import io.credittag.mis.modules.ctc.dao.TotalUserdetailDayDao;
import io.credittag.mis.modules.ctc.dao.WalletAssetDao;
import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;
import io.credittag.mis.modules.ctc.entity.TotalDealDayEntity;
import io.credittag.mis.modules.ctc.entity.TotalUserdetailDayEntity;
import io.credittag.mis.modules.ctc.service.impl.DbDataCountServiceImpl;

@Configuration
@EnableScheduling
public class SendEmailJob {
	private final Logger logger = LoggerFactory.getLogger(getClass());

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
	@Resource
	private TotalDealData totalDealData;
	@Resource
	private TotalDealDataseveraldays totalDealDataseveraldays;

	@Value("${usernameemail}")
	private String username;
	@Value("${password}")
	private String password;
	@Value("${host}")
	private String host;
	@Value("${port}")
	private String port;
	@Value("${to}")
	private String to;

	// 定时发送日报
	@Scheduled(cron = "0 30 0 * * ?")
	//@Scheduled(cron = "*/16 * * * * ?")
	public void sendEmail() {
		System.out.println("...发送邮件...");
		MimeMultipart content = getContent();
		String subject = "ctc数据统计日报";
		SendDailyEmail.send(username, password, to, host, port, content, subject);
	}

	// 统计total_deal_day
	//@Scheduled(cron = "0 20 5 * * ?")
	@Scheduled(cron = "0 0 0 * * ?") // 每天00：00：00分开始执行
	//@Scheduled(cron = "*/12 * * * * ?")
	public void TotalDealDay() throws ParseException {
		/*// 查出数据
		TotalDealDayEntity totalDealDayEntity = totalDealDay();
		// 检查有没有相同的时间的数据
		// 插入数据
		int result = insertTotalDealDay(totalDealDayEntity);*/
		 for(int i=1; i<8; i++) {
			  //查出
			 TotalDealDayEntity totalDealDayEntity =totalDealDataseveraldays.totaldealtotal(i);
			 Date date = new Date();
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DAY_OF_MONTH, 0-i);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				Date vodate = calendar.getTime();
				String vodateStr = sdf.format(vodate);
				totalDealDayEntity.setCreateTime(vodate);
				totalDealDayEntity.setVotime(sdf.format(vodate));
			 //插入
			 int result = insertTotalDealDay(totalDealDayEntity,i);
		 }
		 
	}

	// 统计total_userdeil_day
	//@Scheduled(cron = "0 20 5 * * ?")
	@Scheduled(cron = "0 0 0 * * ?")
	//@Scheduled(cron = "*/12 * * * * ?")
	public void TotalUserDetailDay() {
		//
		 for(int i=1;i<8;i++) {//1
			 DbDataCountEntity dbDataCountEntity =  totalDealDataseveraldays.totaluser(i);
				// 添加 用户详情
			 Map<String, String> map = totalDealDataseveraldays.totalUserDetailDaySup(i);
				
			 if (map != null && map.get("1") != null) {
					dbDataCountEntity.setCustomerLogin(map.get("1"));
				} else {
					dbDataCountEntity.setCustomerLogin("0");
				}
				if (map != null && map.get("0") != null &&  map.get("1") != null) {
					dbDataCountEntity.setCustomerRegister(Integer.parseInt(map.get("0"))+Integer.parseInt(map.get("1"))+"");
				} else  if(map != null && map.get("0") != null &&  map.get("1") == null){
					dbDataCountEntity.setCustomerRegister(map.get("0") );
				}else{
					dbDataCountEntity.setCustomerRegister("0");
				}
				Date date = new Date();
				// 计算前一天的时间
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DAY_OF_MONTH, 0-i);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				Date vodate = calendar.getTime();
				String vodateStr = sdf.format(vodate);
				dbDataCountEntity.setVoStrDate(vodateStr);
				// 查询任务总金额
				//Map<String, Double> amountmap = totalUserDetailDayForAmount();
				dbDataCountEntity.setCompleteMoney("0");
				Map<String, Double> amountmap = totalDealDataseveraldays.totalUserDetailDayForAmount(i);
				if (amountmap != null && null!=amountmap.get("amount") &&!"".equals(amountmap.get("amount")) && !"null".equals(amountmap.get("amount") )) {
					dbDataCountEntity.setCompleteMoney(amountmap.get("amount") + "");
				} else {
					dbDataCountEntity.setCompleteMoney(0 + "");
				}
				DynamicDataSource.setDataSource(DataSourceNames.FIRST);
				insertTotalUserDetailDay(dbDataCountEntity);
		 }
	}

	// 插入数据
	@DataSource(name = DataSourceNames.FIRST)
	public int insertTotalDealDay(TotalDealDayEntity totalDealDayEntity,int days) {
		DynamicDataSource.setDataSource(DataSourceNames.FIRST);
		Date date = new Date();
		// 计算前一天的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 0-days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date begindate = calendar.getTime();
		calendar.set(Calendar.SECOND, 10);
		Date endDate = calendar.getTime();
		String begindateStr = sdf.format(begindate);
		String endStr = sdf.format(endDate);
		Map parammap = new HashMap<String, String>();
		parammap.put("begin",begindateStr);
		parammap.put("end", endDate);
		totalDealDayDao.deleteBySameTime(parammap);
		if (totalDealDayEntity != null) {
			return totalDealDayDao.insertDealTotal(totalDealDayEntity);
		} else {
			System.out.println("统计totalDealDay失败");
			return 0;
		}
	}

	@DataSource(name = DataSourceNames.FIRST)
	public void insertTotalUserDetailDay(DbDataCountEntity dbDataCountEntity) {
		DynamicDataSource.setDataSource(DataSourceNames.FIRST);
		// 删除重复的数据
//		Date date = new Date();
		// 计算前一天的时间
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		calendar.add(Calendar.DAY_OF_MONTH, -1);
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
//		Date vodate = calendar.getTime();
//		String vodateStr = sdf.format(vodate);
//
		Map<String, String> parammap = new HashMap<String, String>();
		parammap.put("time", dbDataCountEntity.getVoStrDate());
		totalUserdetailDayDao.deleteBysametime(parammap);
		totalUserdetailDayDao.insertTotalUserDetail(dbDataCountEntity);
	}

	public MimeMultipart getContent() {
		DynamicDataSource.setDataSource(DataSourceNames.FIRST);
		java.text.NumberFormat NF = java.text.NumberFormat.getInstance();
		NF.setGroupingUsed(false);// 去掉科学计数法显示
		StringBuilder stringBuilder = new StringBuilder();
		// 数据
		// 查询本月天的数据
		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -8);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date begindate = calendar.getTime();
		String begindateStr = sdf.format(begindate);
		// 结束时间
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(new Date());
		// calendar2.add(Calendar.HOUR_OF_DAY, -1);
		calendar2.set(Calendar.HOUR_OF_DAY, 0);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		Date enddate = calendar2.getTime();
		String enddateStr = sdf.format(enddate);
		map.put("begin", begindateStr);
		map.put("end", enddateStr);
		List<TotalDealDayEntity> listday = totalDealDayDao.query(map);
		stringBuilder.append(
			"<table width='100%' border=\"1\" bordercolor=\"#000000\" cellspacing=\"0\" style=\"font-size:9pt;border-collapse:collapse\"><tr>")
				.append("<td align=\"center\" colspan=\"10\" bgcolor=\"#F5F5F5\" style=\"font-weight:bold;\">")
				.append("总计----ctc日常交易详情--UTC时间</td></tr><tr>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">日期</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">交易额</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">交易笔数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">CTC新增现持币地址数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">CTC新增历史持币地址数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">CTC新增地址数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">转币地址数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">收币地址数</td>")
				.append("</tr>");
		
		
		if (listday != null && listday.size() > 0) {
			for (TotalDealDayEntity totalDealDayEntity : listday) {
				stringBuilder.append("<tr><td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(sdf.format(totalDealDayEntity.getCreateTime()).substring(0, 10)).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalDealDayEntity.getAmountMoney()/100000000)).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalDealDayEntity.getAmountTime())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalDealDayEntity.getAmountCtcWalletAddr())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(totalDealDayEntity.getExtra()).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalDealDayEntity.getAmountCtcWalletAddrAll())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalDealDayEntity.getAmountCtcWalletAddrFrom())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalDealDayEntity.getAmountCtcWalletAddrTo())).append("</td>")
						.append("</tr>");
			}
		}
		// 查询近7天的统计
		TotalDealDayEntity totalDealDayEntity7 = totalDealData.totaldealtotal(7);
		if (totalDealDayEntity7 != null) {
			stringBuilder.append("<tr><td align=\"center\" bgcolor=\"#F5F5F5\">").append("累计近7天").append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity7.getAmountMoney()/100000000)).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity7.getAmountTime())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity7.getAmountCtcWalletAddr())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(totalDealDayEntity7.getExtra()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity7.getAmountCtcWalletAddrAll())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity7.getAmountCtcWalletAddrFrom())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity7.getAmountCtcWalletAddrTo())).append("</td>")
					.append("</tr>");
		}

		// 查询近30天的统计
		TotalDealDayEntity totalDealDayEntity30 = totalDealData.totaldealtotal(30);
		if (totalDealDayEntity30 != null) {
			stringBuilder.append("<tr><td align=\"center\" bgcolor=\"#F5F5F5\">").append("累计近30天").append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity30.getAmountMoney()/100000000)).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity30.getAmountTime())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity30.getAmountCtcWalletAddr())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(totalDealDayEntity30.getExtra()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity30.getAmountCtcWalletAddrAll())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity30.getAmountCtcWalletAddrFrom())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntity30.getAmountCtcWalletAddrTo())).append("</td>")
					.append("</tr>");
		}

		// 总计

		TotalDealDayEntity totalDealDayEntityall = totalDealData.totaldealtotal(0);
		if (totalDealDayEntityall != null) {
			stringBuilder.append("<tr><td align=\"center\" bgcolor=\"#F5F5F5\">").append("总计").append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntityall.getAmountMoney()/100000000)).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntityall.getAmountTime())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntityall.getAmountCtcWalletAddr())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(totalDealDayEntityall.getExtra()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntityall.getAmountCtcWalletAddrAll())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntityall.getAmountCtcWalletAddrFrom())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(NF.format(totalDealDayEntityall.getAmountCtcWalletAddrTo())).append("</td>")
					.append("</tr>");
		}

		stringBuilder.append("</table><br/><br/>");
		// 用户详情
		StringBuilder stringBuilderall = getuserDetail(stringBuilder);
		MimeBodyPart text = new MimeBodyPart();
		try {
			text.setContent(stringBuilderall.toString(), "text/html; charset=utf-8");
			MimeMultipart all = new MimeMultipart();
			all.addBodyPart(text);
			return all;
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public StringBuilder getuserDetail(StringBuilder stringBuilder) {
		DynamicDataSource.setDataSource(DataSourceNames.FIRST);
		java.text.NumberFormat NF = java.text.NumberFormat.getInstance();
		NF.setGroupingUsed(false);// 去掉科学计数法显示
		// 数据
		// 查询本月天的数据
		Date date = new Date();
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -8);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date begindate = calendar.getTime();
		String begindateStr = sdf.format(begindate);

		// 结束时间
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(new Date());
		// calendar2.add(Calendar.HOUR_OF_DAY, -1);
		calendar2.set(Calendar.HOUR_OF_DAY, 0);
		calendar2.set(Calendar.MINUTE, 0);
		calendar2.set(Calendar.SECOND, 0);
		Date enddate = calendar2.getTime();
		String enddateStr = sdf.format(enddate);
		map.put("begin", begindateStr);
		map.put("end", enddateStr);

		List<TotalUserdetailDayEntity> listday = totalUserdetailDayDao.query(map);
		stringBuilder.append(
				"<table width='100%' border=\"1\" bordercolor=\"#000000\" cellspacing=\"0\" style=\"font-size:9pt;border-collapse:collapse\"><tr>")
				.append("<td align=\"center\" colspan=\"10\" bgcolor=\"#F5F5F5\" style=\"font-weight:bold;\">")
				.append("总计----ctc用户详情--北京时间</td></tr><tr>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">日期</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">当天注册用户数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">当天注册登录用户数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">导入财神管家用户数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">完成任务用户数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">完成任务笔数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">完成任务总金额</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">提取资产用户数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">提取成功笔数</td>")
				.append("<td align=\"center\" bgcolor=\"#1A77BE\" style=\"color:#FFFFFF\">提取成功ctc数量</td>")
				.append("</tr>");
		if (listday != null && listday.size() > 0) {
			for (TotalUserdetailDayEntity totalUserdetailDayEntity : listday) {
				stringBuilder.append("<tr><td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(sdf.format(totalUserdetailDayEntity.getCreateTime()).substring(0, 10)).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalUserdetailDayEntity.getCountRegister())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalUserdetailDayEntity.getCountLogin())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalUserdetailDayEntity.getWhiteUser())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalUserdetailDayEntity.getCompeleteUser())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalUserdetailDayEntity.getCompeleteTask())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalUserdetailDayEntity.getCompleteMoney())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalUserdetailDayEntity.getExtractUser())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalUserdetailDayEntity.getExtractSuccessTime())).append("</td>")
						.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
						.append(NF.format(totalUserdetailDayEntity.getAmountCtc())).append("</td>")
						.append("</tr>");
			}
		}

		// 查询近7天的统计
		DbDataCountEntity dbDataCountEntity7 = totalDealData.totaluser(7);
		// TotalUserdetailDayEntity totalUserdetailDayEntity7 =
		// totalUserdetailDayDao.total(map7);
		if (dbDataCountEntity7 != null) {
			stringBuilder.append("<tr><td align=\"center\" bgcolor=\"#F5F5F5\">").append("累计近7天").append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity7.getCustomerRegister()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity7.getCustomerLogin())
					.append("</td>").append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity7.getWhiteCustomerCount()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity7.getCompleteCustomer()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity7.getCompleteCount())
					.append("</td>").append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity7.getCompleteMoney()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append((dbDataCountEntity7.getWithdrawCustomer())).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">").append((dbDataCountEntity7.getWithdrawNum()))
					.append("</td>").append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append((dbDataCountEntity7.getWithdrawCtcNum())).append("</td>").append("</tr>");
		}

		// 查询近30天的统计

		// TotalUserdetailDayEntity totalUserdetailDayEntity30 =
		// totalUserdetailDayDao.total(map30);
		DbDataCountEntity dbDataCountEntity30 = totalDealData.totaluser(30);

		if (dbDataCountEntity30 != null) {
			stringBuilder.append("<tr><td align=\"center\" bgcolor=\"#F5F5F5\">").append("累计近30天").append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity30.getCustomerRegister()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">").append(dbDataCountEntity30.getCustomerLogin())
					.append("</td>").append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity30.getWhiteCustomerCount()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity30.getCompleteCustomer()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">").append(dbDataCountEntity30.getCompleteCount())
					.append("</td>").append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity30.getCompleteMoney()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntity30.getWithdrawCustomer()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">").append(dbDataCountEntity30.getWithdrawNum())
					.append("</td>").append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append((dbDataCountEntity30.getWithdrawCtcNum())).append("</td>").append("</tr>");
		}

		// 统计所有
		DbDataCountEntity dbDataCountEntityall = totalDealData.totaluser(0);
		if (dbDataCountEntityall != null) {
			stringBuilder.append("<tr><td align=\"center\" bgcolor=\"#F5F5F5\">").append("总计").append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntityall.getCustomerRegister()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">").append(dbDataCountEntityall.getCustomerLogin())
					.append("</td>").append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntityall.getWhiteCustomerCount()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntityall.getCompleteCustomer()).append("</td>")//
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">").append(dbDataCountEntityall.getCompleteCount())
					.append("</td>")//
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">").append(dbDataCountEntityall.getCompleteMoney())
					.append("</td>").append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntityall.getWithdrawCustomer()).append("</td>")
					.append("<td align=\"center\" bgcolor=\"#F5F5F5\">").append(dbDataCountEntityall.getWithdrawNum())
					.append("</td>").append("<td align=\"center\" bgcolor=\"#F5F5F5\">")
					.append(dbDataCountEntityall.getWithdrawCtcNum()).append("</td>").append("</tr>");

		}
		stringBuilder.append("</table><br/><br/>");
		return stringBuilder;
	}

}
