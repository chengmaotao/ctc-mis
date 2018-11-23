package io.credittag.mis.modules.ctc.entity;

import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 钱包交易表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:10
 */
@TableName("wallet_tran")
public class WalletTranEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 交易表
	 */
	@TableId
	private Long id;
	/**
	 * 交易ID
	 */
	@TableField(exist = false)
	private String txid;
	/**
	 * 交易支付地址
	 */
	@TableField(exist = false)
	private String srcaddr;
	/**
	 * 交易收款地址
	 */
	@TableField(exist = false)
	private String dstaddr;
	/**
	 * 交易地址类型，即货币类型
	 */
	private String cointype;
	/**
	 * 交易金额
	 */
	
	@TableField(exist = false)
	private BigDecimal tranamt;
	
	public BigDecimal getTranamt() {
		return tranamt;
	}

	public void setTranamt(BigDecimal tranamt) {
		this.tranamt = tranamt;
	}
	
	/**
	 * 交易小费
	 */
	private BigDecimal tranfee;
	/**
	 * 交易类型，1:转出，2:转入
	 */
	private Integer trantype;
	/**
	 * 交易状态，1:未确认，2:已成功
	 */
	@TableField(exist = false)
	private Integer transtate;
	/**
	 * 
	 */
	@TableField(exist = false)
	private Date createtime;

	/**
	 * 
	 */
	private Date updatetime;

	/**
	 * 备注
	 */
	private String bak;

	/**
	 * 设置：交易表
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：交易表
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：交易ID
	 */
	public void setTxid(String txid) {
		this.txid = txid;
	}

	/**
	 * 获取：交易ID
	 */
	public String getTxid() {
		return txid;
	}

	/**
	 * 设置：交易支付地址
	 */
	public void setSrcaddr(String srcaddr) {
		this.srcaddr = srcaddr;
	}

	/**
	 * 获取：交易支付地址
	 */
	public String getSrcaddr() {
		return srcaddr;
	}

	/**
	 * 设置：交易收款地址
	 */
	public void setDstaddr(String dstaddr) {
		this.dstaddr = dstaddr;
	}

	/**
	 * 获取：交易收款地址
	 */
	public String getDstaddr() {
		return dstaddr;
	}

	/**
	 * 设置：交易地址类型，即货币类型
	 */
	public void setCointype(String cointype) {
		this.cointype = cointype;
	}

	/**
	 * 获取：交易地址类型，即货币类型
	 */
	public String getCointype() {
		return cointype;
	}

	/**
	 * 设置：交易金额
	 */
	

	/**
	 * 设置：交易小费
	 */
	public void setTranfee(BigDecimal tranfee) {
		this.tranfee = tranfee;
	}

	/**
	 * 获取：交易小费
	 */
	public BigDecimal getTranfee() {
		return tranfee;
	}

	/**
	 * 设置：交易类型，1:转出，2:转入
	 */
	public void setTrantype(Integer trantype) {
		this.trantype = trantype;
	}

	/**
	 * 获取：交易类型，1:转出，2:转入
	 */
	public Integer getTrantype() {
		return trantype;
	}

	/**
	 * 设置：交易状态，1:未确认，2:已成功
	 */
	public void setTranstate(Integer transtate) {
		this.transtate = transtate;
	}

	/**
	 * 获取：交易状态，1:未确认，2:已成功
	 */
	public Integer getTranstate() {
		return transtate;
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

	private String vocreatetime;
	private String voupdatetime;

	public String getVocreatetime() {
		if (getCreatetime() == null) {
			return "";
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getCreatetime()), ConstantField.dateFormatPattern);
	}

	public void setVocreatetime(String vocreatetime) {
		this.vocreatetime = vocreatetime;
	}

	public String getVoupdatetime() {

		if (getUpdatetime() == null) {
			return "";
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getUpdatetime()), ConstantField.dateFormatPattern);

	}

	public void setVoupdatetime(String voupdatetime) {
		this.voupdatetime = voupdatetime;
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
