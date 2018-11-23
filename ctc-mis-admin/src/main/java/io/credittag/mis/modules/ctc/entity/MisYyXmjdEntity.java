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
@TableName("mis_yy_xmjd")
public class MisYyXmjdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	@TableField(exist = false)
	public String voCreateTime;
	
	@TableField(exist = false)
	private String voUpdateTime;
	
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
//		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(updateTime), ConstantField.dateFormatPattern);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  sdf.format(updateTime);
	}

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String title;
	/**
	 * 时间
	 */
	private String time;
	/**
	 * url地址
	 */
	private String url;
	/**
	 * zh,en
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
	
	
	public void setVoUpdateTime(String voUpdateTime) {
		this.voUpdateTime = voUpdateTime;
	}
	public String getVoTime() {
		return voTime;
	}
	public void setVoTime(String voTime) {
		this.voTime = voTime;
	}
	private String voTime;
	

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
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
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
	 * 设置：zh,en
	 */
	public void setLangue(String language) {
		this.language = language;
	}
	/**
	 * 获取：zh,en
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
}
