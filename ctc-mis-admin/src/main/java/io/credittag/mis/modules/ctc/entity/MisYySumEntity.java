package io.credittag.mis.modules.ctc.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;

import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

public class MisYySumEntity {
	private BigDecimal sumCount;
	private Integer type;
	private Integer dhCount;
	private Date expireTime;
	private String remark;
	private String pId;
	private Date createTime;
	private String expireTimeVo;
	private String createTimeVo;

	public String getSumCount() {
		return sumCount.toPlainString();
	}

	public void setSumCount(BigDecimal sumCount) {
		this.sumCount = sumCount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDhCount() {
		return dhCount;
	}

	public void setDhCount(Integer dhCount) {
		this.dhCount = dhCount;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExpireTimeVo() {
		if (null == getExpireTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getExpireTime()),
				io.credittag.mis.modules.ctc.constant.ConstantField.vodateFormatPattern);
	}

	public void setExpireTimeVo(String expireTimeVo) {
		this.expireTimeVo = expireTimeVo;
	}

	public String getCreateTimeVo() {
		if (null == getCreateTime()) {
			return null;
		}
		return CtcMisUtils.formatDate(CtcMisUtils.getUTCDateToDate(getCreateTime()),
				io.credittag.mis.modules.ctc.constant.ConstantField.dateFormatPattern);
	}

	public void setCreateTimeVo(String createTimeVo) {
		this.createTimeVo = createTimeVo;
	}

}
