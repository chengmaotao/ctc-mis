package io.credittag.mis.modules.ctc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.dao.TrxCtcDao;
import io.credittag.mis.modules.ctc.service.TrxCtcService;

@Service
public class TrxCtcServiceImpl implements TrxCtcService {
	@Autowired
	private TrxCtcDao trxCtcDao;

	@Override
	@DataSource(name = DataSourceNames.FIVE)
	public void deleteByTxid(String trxId) {
		trxCtcDao.deleteByTxid(trxId);

	}

	@Override
	@DataSource(name = DataSourceNames.FIVE)
	public void deleteByInTxid(String trxId) {
		trxCtcDao.deleteByInTxid(trxId);
		
	}

	@Override
	@DataSource(name = DataSourceNames.FIVE)
	public void deleteByOutTxid(String trxId) {
		trxCtcDao.deleteByOutTxid(trxId);
		
	}

}
