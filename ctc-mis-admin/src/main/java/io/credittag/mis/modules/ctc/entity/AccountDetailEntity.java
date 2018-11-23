package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.credittag.mis.common.utils.ConstantField;
import io.credittag.mis.common.utils.DateUtils;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@TableName("account_detail")
public class AccountDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 客户编号
	 */
	private Long cid;
	/**
	 * 金额
	 */
	private BigDecimal amount;

	private String amountVo;
	/**
	 * 余额方向，收入0，支出1
	 */
	private Integer direction;
	/**
	 * 余额
	 */
	private BigDecimal balance;
	/**
	 * 事由
	 */
	private String reason;
	/**
	 * 所在期数
	 */
	private String accountPeriod;

	/**
	 * 日期
	 */
	private Date dayTime;
	/**
	 * 当日领取总金额
	 */
	private BigDecimal amountBalance;
	/**
	 * 当日领取总人数
	 */
	private int personCount;
	/**
	 * 用户手机号
	 */
	private String phoneNo;

	private String createTimeVo;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：
	 */
	public Date getCreateTime() {

		return createTime;
	}

	public String getCreateTimeVo() {
		if (null == getCreateTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getCreateTime()),
				io.credittag.mis.modules.ctc.constant.ConstantField.dateFormatPattern);
	}

	public void setCreateTimeVo(String createTimeVo) {
		this.createTimeVo = createTimeVo;
	}

	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		if (null == updateTime) {
			return null;
		}
		return CtcMisUtils.getUTCDateToDate(updateTime);
	}

	/**
	 * 设置：客户编号
	 */
	public void setCid(Long cid) {
		this.cid = cid;
	}

	/**
	 * 获取：客户编号
	 */
	public Long getCid() {
		return cid;
	}

	/**
	 * 设置：金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取：金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置：余额方向，收入0，支出1
	 */
	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	/**
	 * 获取：余额方向，收入0，支出1
	 */
	public Integer getDirection() {
		return direction;
	}

	/**
	 * 设置：余额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * 获取：余额
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 设置：事由
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * 获取：事由
	 */
	public String getReason() {
		if (null != reason) {
			return ConstantField.getReason(reason);
		} else {
			return "";
		}

	}

	/**
	 * 设置：所在期数
	 */
	public void setAccountPeriod(String accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	public String getAmountVo() {
		if (null != amount) {
			return getDirection() == 0 ? "+" + amount.toPlainString() : "-" + amount.toPlainString();
		} else {
			return "";
		}

	}

	public void setAmountVo(String amountVo) {
		this.amountVo = amountVo;
	}

	/**
	 * 获取：所在期数
	 */
	public String getAccountPeriod() {
		return accountPeriod;
	}

	public String getDayTime() {
		return DateUtils.format(dayTime);
	}

	public void setDayTime(Date dayTime) {
		this.dayTime = dayTime;
	}

	public BigDecimal getAmountBalance() {
		return amountBalance;
	}

	public void setAmountBalance(BigDecimal amountBalance) {
		this.amountBalance = amountBalance;
	}

	public int getPersonCount() {
		return personCount;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
