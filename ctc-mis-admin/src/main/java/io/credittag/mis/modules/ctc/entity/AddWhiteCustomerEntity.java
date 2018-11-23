package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 添加到第三方白名单的用户
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@TableName("add_white_customer")
public class AddWhiteCustomerEntity implements Serializable {
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
	 * 第三方id
	 */
	private String target;
	/**
	 * 
	 */
	private String langueCode;

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
	 * 设置：第三方id
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	/**
	 * 获取：第三方id
	 */
	public String getTarget() {
		return target;
	}
	/**
	 * 设置：
	 */
	public void setLangueCode(String langueCode) {
		this.langueCode = langueCode;
	}
	/**
	 * 获取：
	 */
	public String getLangueCode() {
		return langueCode;
	}
}
