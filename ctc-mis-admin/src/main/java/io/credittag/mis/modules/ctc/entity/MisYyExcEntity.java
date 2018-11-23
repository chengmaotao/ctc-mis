package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-25 12:14:16
 */
@TableName("mis_yy_exc")
public class MisYyExcEntity implements Serializable {
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
	 * 兑换数量
	 */
	private BigDecimal count;
	/**
	 * 奖励类型0：信用钻1：算力2：ctc
	 */
	private Integer type;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 微信昵称
	 */
	private String wxNickName;
	/**
	 * 标签
	 */
	private String mark;
	/**
	 * 0:初始状态1：删除状态（兑换码生成之前可以删除）
	 */
	private Integer status;
	/**
	 * 过期时间
	 */
	private Date expireTime;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

	/**
	 * 批处理
	 */
	@TableField(exist = false)
	private String pid;

	/**
	 * 格式化创建时间
	 */
	@TableField(exist = false)
	private String createTimeVo;

	/**
	 * 格式化过期时间
	 */
	@TableField(exist = false)
	private String expireTimeVo;

	@TableField(exist = false)
	private Integer useState;

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
	 * 设置：兑换数量
	 */
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	/**
	 * 获取：兑换数量
	 */
	public BigDecimal getCount() {
		return count;
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
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：微信昵称
	 */
	public void setWxNickName(String wxNickName) {
		this.wxNickName = wxNickName;
	}
	/**
	 * 获取：微信昵称
	 */
	public String getWxNickName() {
		return wxNickName;
	}
	/**
	 * 设置：标签
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}
	/**
	 * 获取：标签
	 */
	public String getMark() {
		return mark;
	}
	/**
	 * 设置：0:初始状态1：删除状态（兑换码生成之前可以删除）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0:初始状态1：删除状态（兑换码生成之前可以删除）
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCreateTimeVo() {
		if (null == getCreateTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getCreateTime()),
				io.credittag.mis.modules.ctc.constant.ConstantField.dateFormatPattern);
	}

	public String getExpireTimeVo() {
		if (null == getExpireTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(getExpireTime(), ConstantField.vodateFormatPattern);
	}

	public Integer getUseState() {
		return useState;
	}

	public void setUseState(Integer useState) {
		this.useState = useState;
	}
}
