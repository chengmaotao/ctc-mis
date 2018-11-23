package io.credittag.mis.modules.ctc.entity;

import java.io.Serializable;

/**
 * 
 * @author chengmaotao
 * 
 */

public class DbDataCountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String customerCount; // 开通矿场的手机号数量
	private String whiteCustomerCount; // 白名单用户数
	private String completeCustomer; // 完成任务的用户数
	private String completeCount; // 完成次数
	private String ctcNum; // 赠送CTC数量
	private String withdrawCustomer; // 提取成功用户数
	private String withdrawNum; // 提取成功次数
	private String withdrawCtcNum; // 提前成功CTC总量
	
	public String getCustomerRegister() {
		return customerRegister;
	}

	public void setCustomerRegister(String customerRegister) {
		this.customerRegister = customerRegister;
	}

	public String getCustomerLogin() {
		return customerLogin;
	}

	public void setCustomerLogin(String customerLogin) {
		this.customerLogin = customerLogin;
	}

	public String getCompleteMoney() {
		return completeMoney;
	}

	public void setCompleteMoney(String completeMoney) {
		this.completeMoney = completeMoney;
	}

	private String customerRegister;
	private String customerLogin;
	private String completeMoney;

	private String voStrDate;

	public String getVoStrDate() {
		return voStrDate;
	}

	public void setVoStrDate(String voStrDate) {
		this.voStrDate = voStrDate;
	}

	public String getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(String customerCount) {
		this.customerCount = customerCount;
	}

	public String getWhiteCustomerCount() {
		return whiteCustomerCount;
	}

	public void setWhiteCustomerCount(String whiteCustomerCount) {
		this.whiteCustomerCount = whiteCustomerCount;
	}

	public String getCompleteCustomer() {
		return completeCustomer;
	}

	public void setCompleteCustomer(String completeCustomer) {
		this.completeCustomer = completeCustomer;
	}

	public String getCompleteCount() {
		return completeCount;
	}

	public void setCompleteCount(String completeCount) {
		this.completeCount = completeCount;
	}

	public String getCtcNum() {
		return ctcNum;
	}

	public void setCtcNum(String ctcNum) {
		this.ctcNum = ctcNum;
	}

	public String getWithdrawCustomer() {
		return withdrawCustomer;
	}

	public void setWithdrawCustomer(String withdrawCustomer) {
		this.withdrawCustomer = withdrawCustomer;
	}

	public String getWithdrawNum() {
		return withdrawNum;
	}

	@Override
	public String toString() {
		return "DbDataCountEntity [customerCount=" + customerCount + ", whiteCustomerCount=" + whiteCustomerCount
				+ ", completeCustomer=" + completeCustomer + ", completeCount=" + completeCount + ", ctcNum=" + ctcNum
				+ ", withdrawCustomer=" + withdrawCustomer + ", withdrawNum=" + withdrawNum + ", withdrawCtcNum="
				+ withdrawCtcNum + ", customerRegister=" + customerRegister + ", customerLogin=" + customerLogin
				+ ", completeMoney=" + completeMoney + ", voStrDate=" + voStrDate + "]";
	}

	public void setWithdrawNum(String withdrawNum) {
		this.withdrawNum = withdrawNum;
	}

	public String getWithdrawCtcNum() {
		return withdrawCtcNum;
	}

	public void setWithdrawCtcNum(String withdrawCtcNum) {
		this.withdrawCtcNum = withdrawCtcNum;
	}

}
