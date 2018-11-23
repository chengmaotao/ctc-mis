package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-10 11:56:46
 */
@TableName("mis_auto_reply")
public class MisAutoReplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 标题
	 */
	private String name;
	/**
	 * 
	 */
	private String url;
	/**
	 * 
	 */
	private String keyword;
	/**
	 * 元素编号，用户分页获取
	 */
	private Long pid;
	/**
	 * 
	 */
	private String mediaId;
	/**
	 * 
	 */
	private String text;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

	private String keyEvent;

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
	 * 设置：类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取：类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置：标题
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：标题
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取：
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置：
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 获取：
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 设置：元素编号，用户分页获取
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * 获取：元素编号，用户分页获取
	 */
	public Long getPid() {
		return pid;
	}

	/**
	 * 设置：
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * 获取：
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * 设置：
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 获取：
	 */
	public String getText() {
		return text;
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
		if (null == updateTime) {
			return null;
		}
		return CtcMisUtils.getUTCDateToDate(updateTime);
	}

	public String getKeyEvent() {
		return keyEvent;
	}

	public void setKeyEvent(String keyEvent) {
		this.keyEvent = keyEvent;
	}

}
