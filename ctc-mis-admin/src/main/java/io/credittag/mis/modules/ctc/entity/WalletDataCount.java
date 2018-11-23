package io.credittag.mis.modules.ctc.entity;

import java.io.Serializable;

public class WalletDataCount implements Serializable{

	private String walletAddressCount;
	
	private String voStrDate;
	
	private String tranAmtCount;
	
	private String tranCount;
	
	private String tranAddressCount;
	
	

	public String getVoStrDate() {
		return voStrDate;
	}

	public void setVoStrDate(String voStrDate) {
		this.voStrDate = voStrDate;
	}

	

	public String getWalletAddressCount() {
		return walletAddressCount;
	}

	public void setWalletAddressCount(String walletAddressCount) {
		this.walletAddressCount = walletAddressCount;
	}

	public String getTranAmtCount() {
		return tranAmtCount;
	}

	public void setTranAmtCount(String tranAmtCount) {
		this.tranAmtCount = tranAmtCount;
	}

	public String getTranCount() {
		return tranCount;
	}

	public void setTranCount(String tranCount) {
		this.tranCount = tranCount;
	}

	public String getTranAddressCount() {
		return tranAddressCount;
	}

	public void setTranAddressCount(String tranAddressCount) {
		this.tranAddressCount = tranAddressCount;
	}
	
	
	
	
	
}
