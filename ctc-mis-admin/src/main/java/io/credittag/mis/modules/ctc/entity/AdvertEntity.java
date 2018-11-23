package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 广告  浮层
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@TableName("advert")
public class AdvertEntity implements Serializable {
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
	 * 英文图片url
	 */
	private String imgUrlEn;
	/**
	 * 中文图片url
	 */
	private String imgUrlZh;
	/**
	 * 跳转路径
	 */
	private String targetUrl;
	/**
	 * 优先级； 越小越靠前
	 */
	private Integer priority;
	/**
	 * 0有效  1删除
	 */
	private String delFlag;

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
	 * 设置：英文图片url
	 */
	public void setImgUrlEn(String imgUrlEn) {
		this.imgUrlEn = imgUrlEn;
	}
	/**
	 * 获取：英文图片url
	 */
	public String getImgUrlEn() {
		return imgUrlEn;
	}
	/**
	 * 设置：中文图片url
	 */
	public void setImgUrlZh(String imgUrlZh) {
		this.imgUrlZh = imgUrlZh;
	}
	/**
	 * 获取：中文图片url
	 */
	public String getImgUrlZh() {
		return imgUrlZh;
	}
	/**
	 * 设置：跳转路径
	 */
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	/**
	 * 获取：跳转路径
	 */
	public String getTargetUrl() {
		return targetUrl;
	}
	/**
	 * 设置：优先级； 越小越靠前
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	/**
	 * 获取：优先级； 越小越靠前
	 */
	public Integer getPriority() {
		return priority;
	}
	/**
	 * 设置：0有效  1删除
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：0有效  1删除
	 */
	public String getDelFlag() {
		return delFlag;
	}
}
