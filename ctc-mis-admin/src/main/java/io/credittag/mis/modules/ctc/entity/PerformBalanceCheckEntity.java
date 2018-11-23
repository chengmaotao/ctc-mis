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
 * @date 2018-07-08 14:00:29
 */
@TableName("perform_balance_check")
public class PerformBalanceCheckEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Date beginTime;
	/**
	 * 
	 */
	private Date endTime;
	/**
	 * 0执行中 1执行完毕
	 */
	private String status;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 获取：
	 */
	public Date getBeginTime() {
		return beginTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public Date getEndTime() {
		return endTime;
	}
	/**
	 * 设置：0执行中 1执行完毕
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：0执行中 1执行完毕
	 */
	public String getStatus() {
		return status;
	}
}
