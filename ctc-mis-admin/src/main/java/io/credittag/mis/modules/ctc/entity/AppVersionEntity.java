package io.credittag.mis.modules.ctc.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
@TableName("app_version")
public class AppVersionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String appType;
	/**
	 * 
	 */
	private String version;
	/**
	 * 
	 */
	private String downloadUrl;

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
	public void setAppType(String appType) {
		this.appType = appType;
	}
	/**
	 * 获取：
	 */
	public String getAppType() {
		return appType;
	}
	/**
	 * 设置：
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：
	 */
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	/**
	 * 获取：
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}
}
