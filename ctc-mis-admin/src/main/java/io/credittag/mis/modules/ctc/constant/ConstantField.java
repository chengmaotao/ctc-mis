package io.credittag.mis.modules.ctc.constant;

public class ConstantField {
	public static final int VERIFY_CODE_TIME = -20; // 查询20分钟之内的 验证码

	public static final int error_code_99 = 99; // 查询日期间隔 超过最大值
	public static final int max_date_long = 180; // 查询日期间隔

	public static final int hour_8 = -8; // 北京时间转UTC时间
	public static final int vohour_8 = 8; // UTC时间 转北京时间

	public static final String dateFormatPattern = "yyyy-MM-dd HH:mm:ss";
	public static final String vodateFormatPattern = "yyyy-MM-dd";
	
	
	public static final String missionCsgj = "mission_csgj";

}
