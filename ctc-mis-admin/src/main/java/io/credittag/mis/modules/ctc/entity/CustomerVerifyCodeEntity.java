package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@TableName("customer_verify_code")
public class CustomerVerifyCodeEntity implements Serializable {
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
	 * 用户id
	 */
	private Long cid;
	/**
	 * 验证码
	 */
	private String verifyCode;
	/**
	 * 过期时间
	 */
	private Date expireTime;

	private String userName;
	@TableField(exist = false)
	private String createTimeVo;

	@TableField(exist = false)
	private String updateTimeVo;

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
	 * 设置：用户id
	 */
	public void setCid(Long cid) {
		this.cid = cid;
	}

	/**
	 * 获取：用户id
	 */
	public Long getCid() {
		return cid;
	}

	/**
	 * 设置：验证码
	 */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	/**
	 * 获取：验证码
	 */
	public String getVerifyCode() {
		return verifyCode;
	}

	/**
	 * 设置：过期时间
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	/**
	 * 获取：过期时间
	 */
	public Date getExpireTime() {
		return expireTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCreateTimeVo(String createTimeVo) {
		this.createTimeVo = createTimeVo;
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

	public void setUpdateTimeVo(String updateTimeVo) {
		this.updateTimeVo = updateTimeVo;
	}

}
