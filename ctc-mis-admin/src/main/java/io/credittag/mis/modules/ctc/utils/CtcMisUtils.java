package io.credittag.mis.modules.ctc.utils;

import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.CustomerDao;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CtcMisUtils {
	private static Logger logger = LoggerFactory.getLogger(CtcMisUtils.class);
	private static CustomerDao customerMapper = SpringContextUtil.getBean(CustomerDao.class);

	// 根据用户名 获得用户 对象
	public static CustomerEntity getCustomerByUsername(Map<String, Object> params) {
		return customerMapper.getCustomerByUsername(params);
	}

	// 字符串转日期
	public static Date StringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(ConstantField.dateFormatPattern);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error("日期格式化错误");
			return new Date();
		}
	}

	// 北京时间转UTC时间
	public static Date getDateToUTC(String date) {
		return getDateToUTC(StringToDate(date));

	}

	// 北京时间转UTC时间
	public static Date getDateToUTC(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, ConstantField.hour_8);
		return calendar.getTime();
	}
	// UTC时间 转北京时间
	public static Date getUTCDateToDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, ConstantField.vohour_8);
		return calendar.getTime();
	}

	// 日期格式化
	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	// 获得两个日期之间的天数
	public static int getdiffBytwoDate(Date beginDate, Date endDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		long to = calendar.getTime().getTime();

		Calendar bcalendar = Calendar.getInstance();
		bcalendar.setTime(beginDate);
		bcalendar.set(Calendar.HOUR_OF_DAY, 0);
		bcalendar.set(Calendar.MINUTE, 0);
		bcalendar.set(Calendar.SECOND, 0);

		long from = bcalendar.getTime().getTime();

		return (int) ((to - from) / (1000 * 60 * 60 * 24));
	}

	// 当日最小2018-05-30 00:00:00
	public static Date getMinDateByDay(String date) {

		return getMinDateByDay(StringToDate(date));
	}

	// 当日最大2018-05-30 23:59:59
	public static Date getMaxDateByDay(String date) {

		return getMaxDateByDay(StringToDate(date));
	}

	// 当日最小2018-05-30 00:00:00
	public static Date getMinDateByDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	// 当日最大2018-05-30 23:59:59
	public static Date getMaxDateByDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, 86399);
		return calendar.getTime();
	}
}
