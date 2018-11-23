package io.credittag.mis.modules.ctc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-08 14:00:29
 */
@TableName("error_address_balance")
public class ErrorAddressBalanceEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer performId;
	/**
	 * 
	 */
	private String address;
	/**
	 * 
	 */
	private BigDecimal rpcBalance;
	/**
	 * 
	 */
	private BigDecimal redisBalance;
	/**
	 * 钱包货币地址类型，是哪种货币
	 */
	private String coinType;
	/**
	 * 
	 */
	private Date createTime;

	private String walletId;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置：
	 */
	public void setPerformId(Integer performId) {
		this.performId = performId;
	}

	/**
	 * 获取：
	 */
	public Integer getPerformId() {
		return performId;
	}

	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置：
	 */
	public void setRpcBalance(BigDecimal rpcBalance) {
		this.rpcBalance = rpcBalance;
	}

	/**
	 * 获取：
	 */
	public BigDecimal getRpcBalance() {
		return rpcBalance;
	}

	/**
	 * 设置：
	 */
	public void setRedisBalance(BigDecimal redisBalance) {
		this.redisBalance = redisBalance;
	}

	/**
	 * 获取：
	 */
	public BigDecimal getRedisBalance() {
		return redisBalance;
	}

	/**
	 * 设置：钱包货币地址类型，是哪种货币
	 */
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	/**
	 * 获取：钱包货币地址类型，是哪种货币
	 */
	public String getCoinType() {
		return coinType;
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

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

}
