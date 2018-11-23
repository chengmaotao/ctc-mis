package io.credittag.mis.common.utils;

public class ConstantField {

	public static String getReason(String reson) {
		switch (reson) {
		case "invite":
			return "邀请好友";
		case "mining":
			return "日常领取";
		case "dimtoctc":
			return "兑换CTC";
		case "mission_csgj":
			return "财神";
		case "diamond_exchange":
			return "信用钻兑换";
		case "withdraw":
			return "提现";
		case "withdraw_refund":
			return "提现返还";
		default:
			return "新用户注册";
		}
	}

}
