package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统支持的货币列表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
@TableName("coin_type")
public class CoinTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 货币类型
	 */
	private String coinType;
	/**
	 * 状态 1.可用 -1:不可用
	 */
	private Integer status;
	/**
	 * 是否开通交易所交易,1:开通  -1:未开通
	 */
	private Integer marketTrade;
	/**
	 * 
	 */
	private BigDecimal minFee;
	/**
	 * 
	 */
	private BigDecimal maxFee;
	/**
	 * 
	 */
	private BigDecimal recommendFee;
	/**
	 * 固定手续费
	 */
	private BigDecimal fixedFee;
	/**
	 * 手续费类型：1.用户自定义 2.固定手续费
	 */
	private Integer feeType;

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
	 * 设置：货币类型
	 */
	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}
	/**
	 * 获取：货币类型
	 */
	public String getCoinType() {
		return coinType;
	}
	/**
	 * 设置：状态 1.可用 -1:不可用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1.可用 -1:不可用
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：是否开通交易所交易,1:开通  -1:未开通
	 */
	public void setMarketTrade(Integer marketTrade) {
		this.marketTrade = marketTrade;
	}
	/**
	 * 获取：是否开通交易所交易,1:开通  -1:未开通
	 */
	public Integer getMarketTrade() {
		return marketTrade;
	}
	/**
	 * 设置：
	 */
	public void setMinFee(BigDecimal minFee) {
		this.minFee = minFee;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMinFee() {
		return minFee;
	}
	/**
	 * 设置：
	 */
	public void setMaxFee(BigDecimal maxFee) {
		this.maxFee = maxFee;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMaxFee() {
		return maxFee;
	}
	/**
	 * 设置：
	 */
	public void setRecommendFee(BigDecimal recommendFee) {
		this.recommendFee = recommendFee;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getRecommendFee() {
		return recommendFee;
	}
	/**
	 * 设置：固定手续费
	 */
	public void setFixedFee(BigDecimal fixedFee) {
		this.fixedFee = fixedFee;
	}
	/**
	 * 获取：固定手续费
	 */
	public BigDecimal getFixedFee() {
		return fixedFee;
	}
	/**
	 * 设置：手续费类型：1.用户自定义 2.固定手续费
	 */
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}
	/**
	 * 获取：手续费类型：1.用户自定义 2.固定手续费
	 */
	public Integer getFeeType() {
		return feeType;
	}
}
