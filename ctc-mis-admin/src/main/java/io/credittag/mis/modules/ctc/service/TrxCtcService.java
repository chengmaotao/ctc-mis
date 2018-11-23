package io.credittag.mis.modules.ctc.service;

public interface TrxCtcService {
	void deleteByTxid(String trxId);

	void deleteByInTxid(String trxId);

	void deleteByOutTxid(String trxId);
}
