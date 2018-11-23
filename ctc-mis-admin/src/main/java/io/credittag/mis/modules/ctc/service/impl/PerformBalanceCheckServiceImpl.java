package io.credittag.mis.modules.ctc.service.impl;

import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.dao.ErrorAddressBalanceDao;
import io.credittag.mis.modules.ctc.dao.PerformBalanceCheckDao;
import io.credittag.mis.modules.ctc.dao.WalletAssetDao;
import io.credittag.mis.modules.ctc.entity.ErrorAddressBalanceEntity;
import io.credittag.mis.modules.ctc.entity.PerformBalanceCheckEntity;
import io.credittag.mis.modules.ctc.entity.WalletAssetEntity;
import io.credittag.mis.modules.ctc.service.PerformBalanceCheckService;
import io.credittag.mis.modules.sys.redis.RedisService;

@Service("performBalanceCheckService")
public class PerformBalanceCheckServiceImpl extends ServiceImpl<PerformBalanceCheckDao, PerformBalanceCheckEntity> implements PerformBalanceCheckService {

	private static Logger logger = LoggerFactory.getLogger(PerformBalanceCheckServiceImpl.class);
	@Value("${wallet.socket.host}")
	private String host;

	@Value("${wallet.socket.port}")
	private int port;

	@Value("${wallet.socket.timeout}")
	private int timeout;
	private Socket socket = null;
	private boolean newConnection = false;

	@Autowired
	private PerformBalanceCheckDao performBalanceCheckDao;

	@Autowired
	private ErrorAddressBalanceDao errorAddressBalanceDao;
	@Autowired
	private WalletAssetDao walletAssetDao;

	@Autowired
	private CtcRpcService socketService;

	@Autowired
	private RedisService redisService;
	//private SysConfigRedis sysConfigRedis;

	@Override
	public PageUtils perform() {

		return null;
	}
	
	

	@Override
	@DataSource(name = DataSourceNames.FIRST)
	public String getRerformStatus() {

		Integer maxId = performBalanceCheckDao.getMaxId();

		if (maxId == null) {
			return "1"; // 没有执行中的
		}

		PerformBalanceCheckEntity selectById = this.selectById(maxId);

		if (selectById != null && "0".equals(selectById.getStatus())) {
			return "0"; // 执行中
		}

		return "1";
	}

	@Override
	@DataSource(name = DataSourceNames.FIRST)
	public PerformBalanceCheckEntity checkBalance() {

		PerformBalanceCheckEntity entity = new PerformBalanceCheckEntity();
		entity.setBeginTime(new Date());

		entity.setStatus("0");

		performBalanceCheckDao.insert(entity);

		return entity;

	}

	private boolean getSocket(CtcRpcService socketService) {
		Socket rpcConnection = getSocketConnection();

		if (rpcConnection == null) {
			logger.error("Socket 未连接 ！");
			return false;
		}
		socketService.setConnection(rpcConnection);
		boolean rpcLogin = true;
		if (isNewConnection()) {
			rpcLogin = socketService.login();
		}
		if (!rpcLogin) {
			logger.error("RPC 登录失败 ！");
			releaseConnection();
			return false;
		}
		return true;

	}

	/**
	 * 调用RPC接口 获取余额 与 lw库里的余额比较
	 */
	@Override
	public boolean performCheckBalance(PerformBalanceCheckEntity entity, List<WalletAssetEntity> walletAssetList) {

		if (getSocket(socketService)) {
			new performCheckBalance(socketService, walletAssetList, entity).start();
			return true;
		}
		return false;
	}

	class performCheckBalance extends Thread {

		private CtcRpcService ctcRpcService;
		private List<WalletAssetEntity> walletAssetList;
		private PerformBalanceCheckEntity performBalanceCheckEntity;

		private List<WalletAssetEntity> errorWalletIdList = new ArrayList<WalletAssetEntity>();

		public performCheckBalance(CtcRpcService ctcRpcService, List<WalletAssetEntity> walletAssetList, PerformBalanceCheckEntity performBalanceCheckEntity) {
			this.ctcRpcService = ctcRpcService;
			this.walletAssetList = walletAssetList;
			this.performBalanceCheckEntity = performBalanceCheckEntity;
		}

		@Override
		public void run() {
			BigDecimal blockChainBalance;
			BigDecimal ctcBalanceRedis;

			for (WalletAssetEntity walletAssetEntity : walletAssetList) {
				blockChainBalance = ctcRpcService.blockChainBalance(walletAssetEntity.getCoinaddr());

				//ctcBalanceRedis = sysConfigRedis.getCtcBalanceRedis(walletAssetEntity.getWalletid());
				
				ctcBalanceRedis = redisService.getTotalBalance(walletAssetEntity.getWalletid(), "CTC");

				if (blockChainBalance != null && ctcBalanceRedis != null && ctcBalanceRedis.compareTo(blockChainBalance) != 0) {
					errorWalletIdList.add(walletAssetEntity);
				}

			}

			if (!errorWalletIdList.isEmpty()) {

				ErrorAddressBalanceEntity errorAddressEntity = null;
				for (WalletAssetEntity walletAssetEntity : errorWalletIdList) {
					blockChainBalance = ctcRpcService.blockChainBalance(walletAssetEntity.getCoinaddr());

					//ctcBalanceRedis = sysConfigRedis.getCtcBalanceRedis(walletAssetEntity.getWalletid());
					ctcBalanceRedis = redisService.getTotalBalance(walletAssetEntity.getWalletid(), "CTC");


					if (blockChainBalance != null && ctcBalanceRedis != null && ctcBalanceRedis.compareTo(blockChainBalance) != 0) {
						// errorWalletIdList.add(walletAssetEntity);

						// 错误记录记录到 表里 error_address_balance 更新
						// 表的状态perform_balance_check
						errorAddressEntity = new ErrorAddressBalanceEntity();
						errorAddressEntity.setPerformId(performBalanceCheckEntity.getId());
						errorAddressEntity.setAddress(walletAssetEntity.getCoinaddr());
						errorAddressEntity.setCoinType(walletAssetEntity.getCointype());
						errorAddressEntity.setRedisBalance(ctcBalanceRedis);
						errorAddressEntity.setRpcBalance(blockChainBalance);
						errorAddressEntity.setCreateTime(new Date());
						errorAddressEntity.setWalletId(walletAssetEntity.getWalletid());

						performBalanceCheckEntity.setStatus("1");
						performBalanceCheckEntity.setEndTime(new Date());
						updatePerformBalanceStatusById(errorAddressEntity, performBalanceCheckEntity);
					}
				}
			} else {
				performBalanceCheckEntity.setStatus("1");
				performBalanceCheckEntity.setEndTime(new Date());
				updatePerformBalanceStatusById(null, performBalanceCheckEntity);
			}

		}

	}

	@Transactional
	public boolean updatePerformBalanceStatusById(ErrorAddressBalanceEntity errorAddressEntity, PerformBalanceCheckEntity id) {

		if (errorAddressEntity != null) {
			errorAddressBalanceDao.insert(errorAddressEntity);
		}

		return this.updateById(id);
	}

	public synchronized Socket getSocketConnection() {
		try {
			newConnection = false;
			if (socket == null) {
				socket = new Socket();
			}
			if (socket != null && !socket.isConnected()) {
				SocketAddress remoteAddr = new InetSocketAddress(host, port);
				socket.connect(remoteAddr, timeout);
				newConnection = true;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			releaseConnection();
		}
		return socket;

	}

	public boolean isNewConnection() {
		return newConnection;
	}

	public void releaseConnection() {
		try {
			socket.close();
		} catch (Exception eo) {
		}
		socket = null;
		newConnection = false;
	}

	@Override
	@DataSource(name = DataSourceNames.FIRST)
	public List<ErrorAddressBalanceEntity> queryAddressErrorBalance() {

		List<ErrorAddressBalanceEntity> res = new ArrayList<ErrorAddressBalanceEntity>();

		Integer maxId = performBalanceCheckDao.getMaxId();

		if (maxId == null) {
			logger.warn("校验余额没有执行过");
			return res;
		}

		PerformBalanceCheckEntity selectById = this.selectById(maxId);

		if (selectById != null && "0".equals(selectById.getStatus())) {
			logger.warn("校验余额 正在执行中 请稍后再查询");
			return res;
		}

		res = errorAddressBalanceDao.getListByPerformId(maxId);

		if (res != null && !res.isEmpty()) {
			return res;
		}
		
		return res;

	}
	
	
	@Override
	@DataSource(name = DataSourceNames.FIRST)
	public Integer getMaxId(){
		return performBalanceCheckDao.getMaxId();
	}
	

	public List<ErrorAddressBalanceEntity> getErrorAddressBalanceList(Integer maxId){
		return errorAddressBalanceDao.getListByPerformId(maxId);
	}
	
	
	/**
	 * 余额不对的重置
	 */
	@Override
	@DataSource(name = DataSourceNames.FOUR)
	public boolean resertAddressErrorBalance(String address,List<ErrorAddressBalanceEntity> res) {

		if (getSocket(socketService)) {

			if (StringUtils.isEmpty(address)) {
				// 重置所有的
				if (res != null && !res.isEmpty()) {

					BigDecimal blockChainBalance;
					for (ErrorAddressBalanceEntity errorAddressBalanceEntity : res) {
						blockChainBalance = socketService.blockChainBalance(errorAddressBalanceEntity.getAddress());

						// 重置缓存
						//sysConfigRedis.setCtcBalanceRedis(errorAddressBalanceEntity.getWalletId(), blockChainBalance);

						redisService.putTotalBalance(errorAddressBalanceEntity.getWalletId(), "CTC", blockChainBalance);
						
						// 重置WalletAsset表
						WalletAssetEntity assetEntity = new WalletAssetEntity();

						assetEntity.setCoinaddr(errorAddressBalanceEntity.getAddress());
						assetEntity.setWalletid(errorAddressBalanceEntity.getWalletId());
						assetEntity.setCointype("CTC");
						assetEntity.setTotalamt(blockChainBalance);
						walletAssetDao.updateTotalAmtByAddress(assetEntity);
					}
				}

			} else {
				// 根据地址查出钱包id
				WalletAssetEntity walletAssetEntity = walletAssetDao.getWalletIdByAddress(address);

				BigDecimal blockChainBalance = socketService.blockChainBalance(address);

				// sysConfigRedis.setCtcBalanceRedis(walletAssetEntity.getWalletid(), blockChainBalance);
				redisService.putTotalBalance(walletAssetEntity.getWalletid(), "CTC", blockChainBalance);
				WalletAssetEntity assetEntity = new WalletAssetEntity();

				assetEntity.setCoinaddr(address);
				assetEntity.setWalletid(walletAssetEntity.getWalletid());
				assetEntity.setCointype("CTC");
				assetEntity.setTotalamt(blockChainBalance);
				walletAssetDao.updateTotalAmtByAddress(assetEntity);
			}
		} else {
			logger.error("resertAddressErrorBalance获得rpc连接失败");
			return false;
		}
		return true;

	}

}
