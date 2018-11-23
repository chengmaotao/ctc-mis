package io.credittag.mis.modules.ctc.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

/**
 * 
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:13
 */
@TableName("withdraw_order")
public class WithdrawOrderEntity implements Serializable {
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
	 * 
	 */
	private Long cid;
	/**
	 * 
	 */
	private String orderId;
	/**
	 * 
	 */
	private String currency;
	/**
	 * 
	 */
	private BigDecimal amount;
	/**
	 * 
	 */
	private String toAddress;
	/**
	 * W待处理 U处理中 C已提交 F失败 NQ待查询
	 */
	private String status;
	/**
	 * 
	 */
	private String statusMsg;
	/**
	 * 
	 */
	private String outsideOrderId;
	/**
	 * 
	 */
	private Date finishTime;
	/**
	 * 
	 */
	private String remark;

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
	 * 设置：
	 */
	public void setCid(Long cid) {
		this.cid = cid;
	}

	/**
	 * 获取：
	 */
	public Long getCid() {
		return cid;
	}

	/**
	 * 设置：
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取：
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置：
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * 获取：
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 设置：
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取：
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置：
	 */
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	/**
	 * 获取：
	 */
	public String getToAddress() {
		return toAddress;
	}

	/**
	 * 设置：W待处理 U处理中 C已提交 F失败 NQ待查询
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 获取：W待处理 U处理中 C已提交 F失败 NQ待查询
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置：
	 */
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	/**
	 * 获取：
	 */
	public String getStatusMsg() {
		return statusMsg;
	}

	/**
	 * 设置：
	 */
	public void setOutsideOrderId(String outsideOrderId) {
		this.outsideOrderId = outsideOrderId;
	}

	/**
	 * 获取：
	 */
	public String getOutsideOrderId() {
		return outsideOrderId;
	}

	/**
	 * 设置：
	 */
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * 获取：
	 */
	public Date getFinishTime() {
		return finishTime;
	}

	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String vocreatetime;
	private String vofinishTime;
	
	

	public String getVofinishTime() {
		if (getFinishTime() == null) {
			return "";
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getFinishTime()), ConstantField.dateFormatPattern);
	}

	public void setVofinishTime(String vofinishTime) {
		this.vofinishTime = vofinishTime;
	}

	public String getVocreatetime() {
		if (getCreateTime() == null) {
			return "";
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getCreateTime()), ConstantField.dateFormatPattern);
	}

	public void setVocreatetime(String vocreatetime) {
		this.vocreatetime = vocreatetime;
	}

	

}
