package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-06-28 16:36:00
 */
@TableName("mis_yy_zlbd")
public class MisYyZlbdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableField(exist = false)
	private String voCreateTime;
	@TableField(exist = false)
	private String voUpdateTime;
	
	
	/**
	 * 
	 */
	@TableId
	private Long id;
	
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 频道
	 */
	private String channel;
	/**
	 * 时间
	 */
	private String time;
	/**
	 * url地址
	 */
	private String url;
	/**
	 * en,zh
	 */
	private String language;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

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
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：频道
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	/**
	 * 获取：频道
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * 设置：时间
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * 获取：时间
	 */
	public String getTime() {
		return time;
	}
	/**
	 * 设置：url地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：url地址
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：en,zh
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * 获取：en,zh
	 */
	public String getLanguage() {
		return language;
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
	
	public String getVoCreateTime() {
		if(createTime==null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  sdf.format(createTime);
	}
	public void setVoCreateTime(String voCreateTime) {
		this.voCreateTime = voCreateTime;
	}
	public String getVoUpdateTime() {
		if(updateTime==null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  sdf.format(updateTime);
	}
	public void setVoUpdateTime(String voUpdateTime) {
		this.voUpdateTime = voUpdateTime;
	}
}
