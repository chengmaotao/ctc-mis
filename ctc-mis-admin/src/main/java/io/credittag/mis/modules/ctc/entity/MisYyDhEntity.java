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
 * @date 2018-06-08 17:52:27
 */
@TableName("mis_yy_dh")
public class MisYyDhEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 兑换码
	 */

	private String dhcode;
	/**
	 * 奖励类型0：信用钻1：算力2：ctc
	 */
	private Integer type;
	/**
	 * 奖励数量
	 */
	private BigDecimal count;
	/**
	 * 0未核销1已核销
	 */
	private Integer status;
	/**
	 * 过期时间
	 */
	private Date expireTime;
	@TableField(exist = false)
	private String expireTimeVo;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 批次号
	 */
	private String pid;
	/**
	 * 
	 */
	private Date createTime;
	@TableField(exist = false)
	private String createTimeVo;
	/**
	 * 
	 */
	private Date updateTime;
	@TableField(exist = false)
	private Integer sumCount;

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
	 * 设置：兑换码
	 */
	public void setDhcode(String dhcode) {
		this.dhcode = dhcode;
	}

	/**
	 * 获取：兑换码
	 */
	public String getDhcode() {
		return dhcode;
	}

	/**
	 * 设置：奖励类型0：信用钻1：算力2：ctc
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获取：奖励类型0：信用钻1：算力2：ctc
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置：奖励数量
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}

	/**
	 * 获取：奖励数量
	 */
	public BigDecimal getCount() {
		return count;
	}

	/**
	 * 设置：0未核销1已核销
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：0未核销1已核销
	 */
	public Integer getStatus() {
		return status;
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

	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置：批次号
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * 获取：批次号
	 */
	public String getPid() {
		return pid;
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

	public Integer getSumCount() {
		return sumCount;
	}

	public void setSumCount(Integer sumCount) {
		this.sumCount = sumCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExpireTimeVo() {
		if (null == getExpireTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getExpireTime()),
				io.credittag.mis.modules.ctc.constant.ConstantField.vodateFormatPattern);
	}

	public void setExpireTimeVo(String expireTimeVo) {
		this.expireTimeVo = expireTimeVo;
	}

	public String getCreateTimeVo() {
		if (null == getCreateTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getCreateTime()),
				io.credittag.mis.modules.ctc.constant.ConstantField.dateFormatPattern);
	}

	public void setCreateTimeVo(String createTimeVo) {
		this.createTimeVo = createTimeVo;
	}

}
