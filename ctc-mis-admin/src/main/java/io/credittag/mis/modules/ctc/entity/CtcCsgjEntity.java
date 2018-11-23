package io.credittag.mis.modules.ctc.entity;

import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * CTC 财神管家的响应表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@TableName("ctc_csgj")
public class CtcCsgjEntity implements Serializable {
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
	 * ctc明细id
	 */
	private Long ctcDetailId;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 商品编号
	 */
	private String goods;
	/**
	 * 数量
	 */
	private Integer number;
	/**
	 * 总金额
	 */
	private BigDecimal amount;
	/**
	 * 订单时间
	 */
	private Date date;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * 设置：
	 */
	
	private String price;
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
	 * 设置：ctc明细id
	 */
	public void setCtcDetailId(Long ctcDetailId) {
		this.ctcDetailId = ctcDetailId;
	}

	/**
	 * 获取：ctc明细id
	 */
	public Long getCtcDetailId() {
		return ctcDetailId;
	}

	/**
	 * 设置：订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 获取：订单号
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * 设置：商品编号
	 */
	public void setGoods(String goods) {
		this.goods = goods;
	}

	/**
	 * 获取：商品编号
	 */
	public String getGoods() {
		return goods;
	}

	/**
	 * 设置：数量
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 * 获取：数量
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * 设置：总金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获取：总金额
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置：订单时间
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * 获取：订单时间
	 */
	public Date getDate() {
		return date;
	}

	private String ctcAmount;

	public String getCtcAmount() {
		return ctcAmount;
	}

	public void setCtcAmount(String ctcAmount) {
		this.ctcAmount = ctcAmount;
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String vocreatetime;
	private String voDate;
	
	

	public String getVoDate() {
		return CtcMisUtils.formatDate(getDate(), ConstantField.dateFormatPattern);
	}

	public void setVoDate(String voDate) {
		this.voDate = voDate;
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
