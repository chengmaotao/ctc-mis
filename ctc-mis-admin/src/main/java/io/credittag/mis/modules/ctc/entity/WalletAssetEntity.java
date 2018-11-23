package io.credittag.mis.modules.ctc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 钱包地址表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
@TableName("wallet_asset")
public class WalletAssetEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 钱包地址表自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 钱包唯一ID
	 */
	private String walletid;
	/**
	 * 钱包货币地址
	 */
	@TableField(exist = false)
	private String coinaddr;
	/**
	 * 钱包货币地址类型，是哪种货币
	 */
	private String cointype;
	/**
	 * 该地址的可用总余额
	 */
	private BigDecimal totalamt;
	/**
	 * 待确认的总金额
	 */
	private BigDecimal unconfirmamt;
	/**
	 * 
	 */
	private BigDecimal availableamt;
	/**
	 * 备注
	 */
	private String bak;
	/**
	 * 
	 */
	@TableField(exist = false)
	private Date createtime;
	/**
	 * 
	 */
	@TableField(exist = false)
	private String voCreateTime;
	public String getVoCreateTime() {
		if(createtime==null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(createtime);
		
	}

	public void setVoCreateTime(String voCreateTime) {
		this.voCreateTime = voCreateTime;
	}

	private Date updatetime;

	/**
	 * 设置：钱包地址表自增主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：钱包地址表自增主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：钱包唯一ID
	 */
	public void setWalletid(String walletid) {
		this.walletid = walletid;
	}

	/**
	 * 获取：钱包唯一ID
	 */
	public String getWalletid() {
		return walletid;
	}

	/**
	 * 设置：钱包货币地址
	 */
	public void setCoinaddr(String coinaddr) {
		this.coinaddr = coinaddr;
	}

	/**
	 * 获取：钱包货币地址
	 */
	public String getCoinaddr() {
		return coinaddr;
	}

	/**
	 * 设置：钱包货币地址类型，是哪种货币
	 */
	public void setCointype(String cointype) {
		this.cointype = cointype;
	}

	/**
	 * 获取：钱包货币地址类型，是哪种货币
	 */
	public String getCointype() {
		return cointype;
	}

	/**
	 * 设置：该地址的可用总余额
	 */
	public void setTotalamt(BigDecimal totalamt) {
		this.totalamt = totalamt;
	}

	/**
	 * 获取：该地址的可用总余额
	 */
	public BigDecimal getTotalamt() {
		return totalamt;
	}

	/**
	 * 设置：待确认的总金额
	 */
	public void setUnconfirmamt(BigDecimal unconfirmamt) {
		this.unconfirmamt = unconfirmamt;
	}

	/**
	 * 获取：待确认的总金额
	 */
	public BigDecimal getUnconfirmamt() {
		return unconfirmamt;
	}

	/**
	 * 设置：
	 */
	public void setAvailableamt(BigDecimal availableamt) {
		this.availableamt = availableamt;
	}

	/**
	 * 获取：
	 */
	public BigDecimal getAvailableamt() {
		return availableamt;
	}

	/**
	 * 设置：备注
	 */
	public void setBak(String bak) {
		this.bak = bak;
	}

	/**
	 * 获取：备注
	 */
	public String getBak() {
		return bak;
	}

	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * 设置：
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * 获取：
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	private Date paramMinDate;
	private Date paramMaxDate;

	public Date getParamMinDate() {
		return paramMinDate;
	}

	public void setParamMinDate(Date paramMinDate) {
		this.paramMinDate = paramMinDate;
	}

	public Date getParamMaxDate() {
		return paramMaxDate;
	}

	public void setParamMaxDate(Date paramMaxDate) {
		this.paramMaxDate = paramMaxDate;
	}
	
	

	
}
