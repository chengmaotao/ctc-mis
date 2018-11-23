package io.credittag.mis.modules.ctc.dao;

public interface TrxCtcDao {
	void deleteByTxid(String trxId);
	void deleteByInTxid(String trxId);
	void deleteByOutTxid(String trxId);
}
