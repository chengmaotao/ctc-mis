package io.credittag.mis.modules.ctc.entity;

import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 钱包用户表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:30:09
 */
@TableName("wallet")
public class WalletEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 钱包表自增主键
	 */
	@TableId
	private Long id;
	/**
	 * 钱包唯一ID
	 */
	@TableField(exist = false)
	private String walletid;
	/**
	 * 
	 */
	@TableField(exist = false)
	private Date createtime;
	/**
	 * 备注
	 */
	private String bak;

	/**
	 * 设置：钱包表自增主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：钱包表自增主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：钱包唯一ID
	 */
	public void setWalletid(String walletid) {
		this.walletid = walletid;
	}

	/**
	 * 获取：钱包唯一ID
	 */
	public String getWalletid() {
		return walletid;
	}

	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * 设置：备注
	 */
	public void setBak(String bak) {
		this.bak = bak;
	}

	/**
	 * 获取：备注
	 */
	public String getBak() {
		return bak;
	}

	/**
	 * 钱包货币地址
	 */
	private String coinaddr;
	/**
	 * 钱包货币地址类型，是哪种货币
	 */
	private String cointype;

	public String getCoinaddr() {
		return coinaddr;
	}

	public void setCoinaddr(String coinaddr) {
		this.coinaddr = coinaddr;
	}

	public String getCointype() {
		return cointype;
	}

	public void setCointype(String cointype) {
		this.cointype = cointype;
	}

	private String vocreatetime;

	public String getVocreatetime() {
		if (getCreatetime() == null) {
			return "";
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getCreatetime()), ConstantField.dateFormatPattern);
	}

}
