package io.credittag.mis.modules.ctc.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class AnalystEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String phoneNo;
	private BigDecimal ctcAmount;
	private BigDecimal dhCtcAmount;
	private BigDecimal txCtcAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public BigDecimal getCtcAmount() {
		return null == ctcAmount ? new BigDecimal(0) : ctcAmount;
	}

	public void setCtcAmount(BigDecimal ctcAmount) {
		this.ctcAmount = ctcAmount;
	}

	public BigDecimal getDhCtcAmount() {
		return null == dhCtcAmount ? new BigDecimal(0) : dhCtcAmount;
	}

	public void setDhCtcAmount(BigDecimal dhCtcAmount) {
		this.dhCtcAmount = dhCtcAmount;
	}

	public BigDecimal getTxCtcAmount() {
		return null == txCtcAmount ? new BigDecimal(0) : txCtcAmount;
	}

	public void setTxCtcAmount(BigDecimal txCtcAmount) {
		this.txCtcAmount = txCtcAmount;
	}

}
