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
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@TableName("customer")
public class CustomerEntity implements Serializable {
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
	 * 状态，9已发送验证码，0已注册，1denglu ,2冻结，3注销
	 */
	private Integer status;
	/**
	 * 
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
	 * 已邀请人数
	 */
	private Integer inviteCount;
	/**
	 * 
	 */
	private BigDecimal ctcBalance;
	/**
	 * 
	 */
	private String channelId;
	/**
	 * 
	 */
	private String appVersion;
	/**
	 * 撒币标识
	 */
	private Integer sbFlag;

	private Integer inviteRewardCount;
	
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
	public String getStatus() {
		if(status!=null) {
			switch (status) {
			case 9:return "已发送验证码";
			case 0:return "已注册";
			case 1:return "已登录";
			default : return status+"";
		}
		}
		return "未知状态";
		
	//	return status;
	}

	/**
	 * 设置：
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	/**
	 * 获取：
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
	 * 设置：已邀请人数
	 */
	public void setInviteCount(Integer inviteCount) {
		this.inviteCount = inviteCount;
	}

	/**
	 * 获取：已邀请人数
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
	 * 设置：撒币标识
	 */
	public void setSbFlag(Integer sbFlag) {
		this.sbFlag = sbFlag;
	}

	/**
	 * 获取：撒币标识
	 */
	public Integer getSbFlag() {
		return sbFlag;
	}
	
	@TableField(exist = false)
	private String fromUsername;

	public String getFromUsername() {
		return fromUsername;
	}

	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}
	@TableField(exist = false)
	private String vocreatetime;
	@TableField(exist = false)
	private String voupdatetime;

	public String getVocreatetime() {
		if (getCreateTime() == null) {
			return "";
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getCreateTime()), ConstantField.dateFormatPattern);
	}

	public void setVocreatetime(String vocreatetime) {
		this.vocreatetime = vocreatetime;
	}

	public String getVoupdatetime() {

		if (getUpdateTime() == null) {
			return "";
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getUpdateTime()), ConstantField.dateFormatPattern);

	}

	public void setVoupdatetime(String voupdatetime) {
		this.voupdatetime = voupdatetime;
	}
	@TableField(exist = false)
	private Date paramMinDate;
	@TableField(exist = false)
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
	@TableField(exist = false)
	private String sumInviteCount;
	@TableField(exist = false)
	private String counts;
	@TableField(exist = false)
	private String loginCounts;

	public String getSumInviteCount() {
		return sumInviteCount;
	}

	public void setSumInviteCount(String sumInviteCount) {
		this.sumInviteCount = sumInviteCount;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getLoginCounts() {
		return loginCounts;
	}

	public void setLoginCounts(String loginCounts) {
		this.loginCounts = loginCounts;
	}
	@TableField(exist = false)
	private String sqlelement;

	public String getSqlelement() {
		return sqlelement;
	}

	public void setSqlelement(String sqlelement) {
		this.sqlelement = sqlelement;
	}
	@TableField(exist = false)
	private String voStrDate;

	public String getVoStrDate() {
		return voStrDate;
	}

	public void setVoStrDate(String voStrDate) {
		this.voStrDate = voStrDate;
	}
	@TableField(exist = false)
	private String cid;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	@TableField(exist = false)
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getInviteRewardCount() {
		return inviteRewardCount;
	}

	public void setInviteRewardCount(Integer inviteRewardCount) {
		this.inviteRewardCount = inviteRewardCount;
	}

}
