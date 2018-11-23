package io.credittag.mis.modules.sys.redis;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis存取服务类 Created by mayakui on 2018/3/25 0005.
 */
@Service
public class RedisService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	 private static JedisPool jedisPool = null;
	static{
		JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        jedisPool = new JedisPool(config, "172.31.2.234", 6379, 6000, "Ctc23491792#9F042LPi");
	}
	
	
	public synchronized static Jedis getResource() {
        return jedisPool.getResource();
    }
	
	
	/**
	 * 获取资产的金额
	 *
	 * @param walletId
	 * @param coinType
	 * @return
	 */
	public BigDecimal getTotalBalance(String walletId, String coinType) {
		
		
		
		String key = new StringBuffer("amount:").append(walletId).append(":total:").append(coinType).toString();

		//Object result = redisTemplate.opsForValue().get(key);
		Jedis resource = getResource();
		
		String result = resource.get(key);
		resource.close();
		if (result == null) {
			return new BigDecimal(0);
		} else {
			// "[\"java.math.BigDecimal\",0.000000000000000000]"
			String res = result.substring(result.indexOf(",") + 1,result.lastIndexOf("]"));
			return new BigDecimal(res);
		}
		
	}

	/**
	 * 更新资产
	 *
	 * @param walletId
	 * @param coinType
	 * @param balance
	 */
	public void putTotalBalance(String walletId, String coinType, BigDecimal balance) {
		String key = new StringBuffer("amount:").append(walletId).append(":total:").append(coinType).toString();
		//redisTemplate.opsForValue().set(key, balance);
		Jedis resource = getResource();
		// "[\"java.math.BigDecimal\",0.000000000000000000]"
		String val = new StringBuffer().append("[\"java.math.BigDecimal\",").append(balance).append("]").toString();
		resource.set(key, val);
		resource.close();
	}

}
