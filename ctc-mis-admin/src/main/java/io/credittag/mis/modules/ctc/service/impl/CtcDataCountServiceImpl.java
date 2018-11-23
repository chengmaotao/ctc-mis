package io.credittag.mis.modules.ctc.service.impl;

import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.dao.CtcDataCountDao;
import io.credittag.mis.modules.ctc.dao.CustomerDao;
import io.credittag.mis.modules.ctc.dao.WalletAssetDao;
import io.credittag.mis.modules.ctc.dao.WalletTranDao;
import io.credittag.mis.modules.ctc.entity.CtcDataCountEntity;
import io.credittag.mis.modules.ctc.service.CtcDataCountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service("ctcDataCountServiceImpl")
public class CtcDataCountServiceImpl extends ServiceImpl<CtcDataCountDao, CtcDataCountEntity> implements CtcDataCountService {

	@Autowired
	private CtcDataCountDao ctcDataCountDao;

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private WalletAssetDao walletAssetDao;

	@Autowired
	private WalletTranDao walletTranDao;


	// 用户总量
	@DataSource(name = DataSourceNames.SECOND)
	public String getCountCustomerNum() {
		return customerDao.selectCountCustomerNum();
	}

	// 钱包地址数
	@DataSource(name = DataSourceNames.FOUR)
	public String getWalletAddressCount() {
		return walletAssetDao.selectWalletAddressCount();
	}

	// 交易总量 交易总笔数
	@DataSource(name = DataSourceNames.FOUR)
	public CtcDataCountEntity getTransactionInfo() {
		return walletTranDao.getTransactionInfo();
	}

	// 交易地址
	@DataSource(name = DataSourceNames.FOUR)
	public String getTransactionAddressCount() {
		return walletTranDao.getTransactionAddressCount();
	}

}
