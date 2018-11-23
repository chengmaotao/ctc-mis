package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-06 09:24:50
 */
@TableName("total_deal_day")
public class TotalDealDayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	public String getVotime() {
		return votime;
	}
	public void setVotime(String votime) {
		this.votime = votime;
	}
	/**
	 * 
	 */
	@TableField(exist = false)
	private String votime;
	private Date createTime;
	/**
	 * 
	 */
	private Double amountMoney;
	/**
	 * 
	 */
	private Integer amountTime;
	/**
	 * 
	 */
	private Long untCtcWalletAddr;
	/**
	 * 
	 */
	private String extra;

	public Long getUntCtcWalletAddr() {
		return untCtcWalletAddr;
	}
	public void setUntCtcWalletAddr(Long untCtcWalletAddr) {
		this.untCtcWalletAddr = untCtcWalletAddr;
	}
	public Long getAmountCtcWalletAddrAll() {
		return amountCtcWalletAddrAll;
	}
	public void setAmountCtcWalletAddrAll(Long amountCtcWalletAddrAll) {
		this.amountCtcWalletAddrAll = amountCtcWalletAddrAll;
	}
	public Long getAmountCtcWalletAddrFrom() {
		return amountCtcWalletAddrFrom;
	}
	public void setAmountCtcWalletAddrFrom(Long amountCtcWalletAddrFrom) {
		this.amountCtcWalletAddrFrom = amountCtcWalletAddrFrom;
	}
	public Long getAmountCtcWalletAddrTo() {
		return amountCtcWalletAddrTo;
	}
	public void setAmountCtcWalletAddrTo(Long amountCtcWalletAddrTo) {
		this.amountCtcWalletAddrTo = amountCtcWalletAddrTo;
	}
	private Long amountCtcWalletAddr;
	@TableField(exist = false)
	private Long amountCtcWalletAddrAll;
	@TableField(exist = false)
	private Long amountCtcWalletAddrFrom;
	@TableField(exist = false)
	private Long amountCtcWalletAddrTo;
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
	/**
	 * 设置：
	 */
	public void setAmountMoney(Double amountMoney) {
		this.amountMoney = amountMoney;
	}
	/**
	 * 获取：
	 */
	public Double getAmountMoney() {
		return amountMoney;
	}
	/**
	 * 设置：
	 */
	public void setAmountTime(Integer amountTime) {
		this.amountTime = amountTime;
	}
	/**
	 * 获取：
	 */
	public Integer getAmountTime() {
		return amountTime;
	}
	/**
	 * 设置：
	 */
	public void setAmountCtcWalletAddr(Long amountCtcWalletAddr) {
		this.amountCtcWalletAddr = amountCtcWalletAddr;
	}
	/**
	 * 获取：
	 */
	public Long getAmountCtcWalletAddr() {
		return amountCtcWalletAddr;
	}
	/**
	 * 设置：
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}
	/**
	 * 获取：
	 */
	public String getExtra() {
		return extra;
	}
}
