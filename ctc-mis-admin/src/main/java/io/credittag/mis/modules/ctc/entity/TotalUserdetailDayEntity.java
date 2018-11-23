package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-06 09:24:50
 */
@TableName("total_userdetail_day")
public class TotalUserdetailDayEntity implements Serializable {
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
	private Integer countRegister;
	/**
	 * 
	 */
	private Integer countLogin;
	/**
	 * 
	 */
	private Integer whiteUser;
	/**
	 * 
	 */
	private Integer compeleteUser;
	/**
	 * 
	 */
	private Integer compeleteTask;
	/**
	 * 
	 */
	private Integer completeMoney;
	/**
	 * 
	 */
	private Integer extractUser;
	/**
	 * 
	 */
	private Integer extractSuccessTime;
	/**
	 * 
	 */
	private Long amountCtc;

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
	public void setCountRegister(Integer countRegister) {
		this.countRegister = countRegister;
	}
	/**
	 * 获取：
	 */
	public Integer getCountRegister() {
		return countRegister;
	}
	/**
	 * 设置：
	 */
	public void setCountLogin(Integer countLogin) {
		this.countLogin = countLogin;
	}
	/**
	 * 获取：
	 */
	public Integer getCountLogin() {
		return countLogin;
	}
	/**
	 * 设置：
	 */
	public void setWhiteUser(Integer whiteUser) {
		this.whiteUser = whiteUser;
	}
	/**
	 * 获取：
	 */
	public Integer getWhiteUser() {
		return whiteUser;
	}
	/**
	 * 设置：
	 */
	public void setCompeleteUser(Integer compeleteUser) {
		this.compeleteUser = compeleteUser;
	}
	/**
	 * 获取：
	 */
	public Integer getCompeleteUser() {
		return compeleteUser;
	}
	/**
	 * 设置：
	 */
	public void setCompeleteTask(Integer compeleteTask) {
		this.compeleteTask = compeleteTask;
	}
	/**
	 * 获取：
	 */
	public Integer getCompeleteTask() {
		return compeleteTask;
	}
	/**
	 * 设置：
	 */
	public void setCompleteMoney(Integer completeMoney) {
		this.completeMoney = completeMoney;
	}
	/**
	 * 获取：
	 */
	public Integer getCompleteMoney() {
		return completeMoney;
	}
	/**
	 * 设置：
	 */
	public void setExtractUser(Integer extractUser) {
		this.extractUser = extractUser;
	}
	/**
	 * 获取：
	 */
	public Integer getExtractUser() {
		return extractUser;
	}
	/**
	 * 设置：
	 */
	public void setExtractSuccessTime(Integer extractSuccessTime) {
		this.extractSuccessTime = extractSuccessTime;
	}
	/**
	 * 获取：
	 */
	public Integer getExtractSuccessTime() {
		return extractSuccessTime;
	}
	/**
	 * 设置：
	 */
	public void setAmountCtc(Long amountCtc) {
		this.amountCtc = amountCtc;
	}
	/**
	 * 获取：
	 */
	public Long getAmountCtc() {
		return amountCtc;
	}
}
