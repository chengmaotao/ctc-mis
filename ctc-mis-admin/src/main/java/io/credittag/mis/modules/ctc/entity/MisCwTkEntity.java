package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-07 10:11:16
 */
@TableName("mis_cw_tk")
public class MisCwTkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 手机号
	 */
	private String telno;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 银行卡号
	 */
	private String bankno;
	/**
	 * 开户支行
	 */
	private String bankbanchname;
	/**
	 * CTC数量
	 */
	private BigDecimal ctcamount;
	/**
	 * 单价
	 */
	private BigDecimal price;
	/**
	 * 主地址
	 */
	@NotBlank(message="主地址不能为空")
	private String mainaddress;
	/**
	 * 子地址
	 */
	@NotBlank(message="子地址不能为空")
	private String subaddress;
	/**
	 * 打款方地址
	 */
	private String personAddress;
	/**
	 * 微信号
	 */
	private String wxAppid;
	/**
	 * 微信昵称
	 */
	private String wxNickname;
	/**
	 * 0未退款1已退款
	 */
	private Integer tStatus;

	private Date createTime;

	@TableField(exist = false)
	private String createTimeVo;

	private Date updateTime;

	@TableField(exist = false)
	private String updateTimeVo;

	@TableField(exist = false)
	private BigDecimal amount;
	@TableField(exist = false)
	private String mainSubAddress;
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
	 * 设置：手机号
	 */
	public void setTelno(String telno) {
		this.telno = telno;
	}

	/**
	 * 获取：手机号
	 */
	public String getTelno() {
		return telno;
	}

	/**
	 * 设置：用户姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：用户姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：银行卡号
	 */
	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	/**
	 * 获取：银行卡号
	 */
	public String getBankno() {
		return bankno;
	}

	/**
	 * 设置：开户支行
	 */
	public void setBankbanchname(String bankbanchname) {
		this.bankbanchname = bankbanchname;
	}

	/**
	 * 获取：开户支行
	 */
	public String getBankbanchname() {
		return bankbanchname;
	}

	/**
	 * 设置：CTC数量
	 */
	public void setCtcamount(BigDecimal ctcamount) {
		this.ctcamount = ctcamount;
	}

	/**
	 * 获取：CTC数量
	 */
	public BigDecimal getCtcamount() {
		return ctcamount;
	}

	/**
	 * 设置：单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获取：单价
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置：主地址
	 */
	public void setMainaddress(String mainaddress) {
		this.mainaddress = mainaddress;
	}

	/**
	 * 获取：主地址
	 */
	public String getMainaddress() {
		return mainaddress;
	}

	/**
	 * 设置：子地址
	 */
	public void setSubaddress(String subaddress) {
		this.subaddress = subaddress;
	}

	/**
	 * 获取：子地址
	 */
	public String getSubaddress() {
		return subaddress;
	}

	/**
	 * 设置：微信号
	 */
	public void setWxAppid(String wxAppid) {
		this.wxAppid = wxAppid;
	}

	/**
	 * 获取：微信号
	 */
	public String getWxAppid() {
		return wxAppid;
	}

	/**
	 * 设置：微信昵称
	 */
	public void setWxNickname(String wxNickname) {
		this.wxNickname = wxNickname;
	}

	/**
	 * 获取：微信昵称
	 */
	public String getWxNickname() {
		return wxNickname;
	}

	/**
	 * 设置：0未退款1已退款
	 */
	public void setTStatus(Integer tStatus) {
		this.tStatus = tStatus;
	}

	/**
	 * 获取：0未退款1已退款
	 */
	public Integer getTStatus() {
		return tStatus;
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

	public Integer gettStatus() {
		return tStatus;
	}

	public void settStatus(Integer tStatus) {
		this.tStatus = tStatus;
	}

	/**
	 * 设置：打款方地址
	 */
	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	/**
	 * 获取：打款方地址
	 */
	public String getPersonAddress() {
		return personAddress;
	}

	public String getCreateTimeVo() {
		if (null == getCreateTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getCreateTime()),
				io.credittag.mis.modules.ctc.constant.ConstantField.dateFormatPattern);
	}

	public String getUpdateTimeVo() {
		if (null == getUpdateTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getUpdateTime()),
				io.credittag.mis.modules.ctc.constant.ConstantField.dateFormatPattern);
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getAmount() {
		if (null != getPrice() && null != getCtcamount()) {
			return getCtcamount().multiply(getPrice());
		}
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getMainSubAddress() {
		return getMainaddress()+getSubaddress();
	}

	public void setMainSubAddress(String mainSubAddress) {
		this.mainSubAddress = mainSubAddress;
	}

}
