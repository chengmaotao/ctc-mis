package io.credittag.mis.modules.ctc.entity;

import java.io.Serializable;

/**
 * 
 * @author chengmaotao
 * 
 */

public class CtcDataCountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String customerCount;
	private String walletAddressCount;
	private String transactionCount;

	private String transactionAmountCount;

	private String transactionAddressCount;

	public String getCustomerCount() {
		return customerCount == null ? "0" : customerCount.trim();
	}

	public void setCustomerCount(String customerCount) {
		this.customerCount = customerCount;
	}

	public String getWalletAddressCount() {
		return walletAddressCount == null ? "0" : walletAddressCount.trim();
	}

	public void setWalletAddressCount(String walletAddressCount) {
		this.walletAddressCount = walletAddressCount;
	}

	public String getTransactionCount() {
		return transactionCount == null ? "0" : transactionCount.trim();
	}

	public void setTransactionCount(String transactionCount) {
		this.transactionCount = transactionCount;
	}

	public String getTransactionAmountCount() {
		return transactionAmountCount == null ? "0" : transactionAmountCount.trim();
	}

	public void setTransactionAmountCount(String transactionAmountCount) {
		this.transactionAmountCount = transactionAmountCount;
	}

	public String getTransactionAddressCount() {
		return transactionAddressCount == null ? "0" : transactionAddressCount.trim();
	}

	public void setTransactionAddressCount(String transactionAddressCount) {
		this.transactionAddressCount = transactionAddressCount;
	}

}
