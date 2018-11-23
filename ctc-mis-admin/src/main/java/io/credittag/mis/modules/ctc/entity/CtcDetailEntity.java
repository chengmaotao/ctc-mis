package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

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
@TableName("ctc_detail")
public class CtcDetailEntity implements Serializable {
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
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
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
		return reason;
	}
}
