package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-03 10:42:16
 */
@TableName("customer")
public class StopCustomerEntity implements Serializable {
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
	 * 用户名，默认为手机号
	 */
	private String username;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像链接
	 */
	private String photoUrl;
	/**
	 * 算力
	 */
	private Integer power;
	/**
	 * 钻石余额
	 */
	private BigDecimal diamondBalance;
	/**
	 * 国家区号
	 */
	private String countryAreaCode;
	/**
	 * 状态，0已发送验证码，1已注册，2冻结，3注销
	 */
	private Integer status;
	/**
	 * cmt自己的邀请码
	 */
	private String inviteCode;
	/**
	 * 来源邀请用户id
	 */
	private Long fromInviteCid;
	/**
	 * 邀请发放状态，0未发放，1已发放
	 */
	private Integer invitePriseStatus;
	/**
	 * 
	 */
	private Integer inviteCount;
	/**
	 * 
	 */
	private BigDecimal ctcBalance;
	/**
	 * 
	 */
	private Integer inviteRewardCount;
	/**
	 * 
	 */
	private String channelId;
	/**
	 * 
	 */
	private String appVersion;
	/**
	 * 
	 */
	private Integer sbFlag;

	/**
	 * 格式化创建时间
	 */
	@TableField(exist = false)
	private String createTimeVo;

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
	 * 设置：用户名，默认为手机号
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名，默认为手机号
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：头像链接
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	/**
	 * 获取：头像链接
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}
	/**
	 * 设置：算力
	 */
	public void setPower(Integer power) {
		this.power = power;
	}
	/**
	 * 获取：算力
	 */
	public Integer getPower() {
		return power;
	}
	/**
	 * 设置：钻石余额
	 */
	public void setDiamondBalance(BigDecimal diamondBalance) {
		this.diamondBalance = diamondBalance;
	}
	/**
	 * 获取：钻石余额
	 */
	public BigDecimal getDiamondBalance() {
		return diamondBalance;
	}
	/**
	 * 设置：国家区号
	 */
	public void setCountryAreaCode(String countryAreaCode) {
		this.countryAreaCode = countryAreaCode;
	}
	/**
	 * 获取：国家区号
	 */
	public String getCountryAreaCode() {
		return countryAreaCode;
	}
	/**
	 * 设置：状态，0已发送验证码，1已注册，2冻结，3注销
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态，0已发送验证码，1已注册，2冻结，3注销
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：cmt自己的邀请码
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	/**
	 * 获取：cmt自己的邀请码
	 */
	public String getInviteCode() {
		return inviteCode;
	}
	/**
	 * 设置：来源邀请用户id
	 */
	public void setFromInviteCid(Long fromInviteCid) {
		this.fromInviteCid = fromInviteCid;
	}
	/**
	 * 获取：来源邀请用户id
	 */
	public Long getFromInviteCid() {
		return fromInviteCid;
	}
	/**
	 * 设置：邀请发放状态，0未发放，1已发放
	 */
	public void setInvitePriseStatus(Integer invitePriseStatus) {
		this.invitePriseStatus = invitePriseStatus;
	}
	/**
	 * 获取：邀请发放状态，0未发放，1已发放
	 */
	public Integer getInvitePriseStatus() {
		return invitePriseStatus;
	}
	/**
	 * 设置：
	 */
	public void setInviteCount(Integer inviteCount) {
		this.inviteCount = inviteCount;
	}
	/**
	 * 获取：
	 */
	public Integer getInviteCount() {
		return inviteCount;
	}
	/**
	 * 设置：
	 */
	public void setCtcBalance(BigDecimal ctcBalance) {
		this.ctcBalance = ctcBalance;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getCtcBalance() {
		return ctcBalance;
	}
	/**
	 * 设置：
	 */
	public void setInviteRewardCount(Integer inviteRewardCount) {
		this.inviteRewardCount = inviteRewardCount;
	}
	/**
	 * 获取：
	 */
	public Integer getInviteRewardCount() {
		return inviteRewardCount;
	}
	/**
	 * 设置：
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	/**
	 * 获取：
	 */
	public String getChannelId() {
		return channelId;
	}
	/**
	 * 设置：
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	/**
	 * 获取：
	 */
	public String getAppVersion() {
		return appVersion;
	}
	/**
	 * 设置：
	 */
	public void setSbFlag(Integer sbFlag) {
		this.sbFlag = sbFlag;
	}
	/**
	 * 获取：
	 */
	public Integer getSbFlag() {
		return sbFlag;
	}

	public String getCreateTimeVo() {
		if (null == getCreateTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getCreateTime()),
				io.credittag.mis.modules.ctc.constant.ConstantField.dateFormatPattern);
	}
}
