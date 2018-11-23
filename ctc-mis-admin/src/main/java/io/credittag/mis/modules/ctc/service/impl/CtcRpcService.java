package io.credittag.mis.modules.ctc.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 调用底层钱包的入口 Created by mayakui on 2018/3/25 0025.
 */
@Service
public class CtcRpcService {

	private static Logger logger = LoggerFactory.getLogger(CtcRpcService.class);

	@Value("${wallet.socket.username}")
	private String username;

	@Value("${wallet.socket.password}")
	private String password;

	private Socket rpcConnection;

	public String send(String method, List<Object> params) throws Exception {
		String result = null;

		JSONObject sendObject = new JSONObject();
		sendObject.put("jsonrpc", "2.0");
		sendObject.put("id", System.currentTimeMillis());
		sendObject.put("method", method);

		// list 转换位 json
		JSONArray paramsObject = new JSONArray();
		paramsObject.addAll(params);

		sendObject.put("params", paramsObject);

		String sendMessage = sendObject.toJSONString();
		logger.info("【发送字符串】：{}", sendMessage);

		PrintWriter os = null;

		BufferedReader is = null;

		os = new PrintWriter(rpcConnection.getOutputStream());
		is = new BufferedReader(new InputStreamReader(rpcConnection.getInputStream()));

		// 发送报文
		os.println(sendMessage);
		os.flush();
		// 获取报文
		String returnMessage = is.readLine();

		logger.info("【返回报文信息】：{}", returnMessage);
		JSONObject returnObject = JSONObject.parseObject(returnMessage);
		if (returnObject.getJSONObject("error") != null) {
			throw new Exception(returnObject.getJSONObject("error").getString("message"));
		}
		result = returnObject.getString("result");
		return result;
	}

	public boolean login() {
		boolean login = false;
		JSONObject sendObject = new JSONObject();
		sendObject.put("jsonrpc", "2.0");
		sendObject.put("id", System.currentTimeMillis());
		sendObject.put("method", "login");

		JSONArray paramsObject = new JSONArray();
		paramsObject.add(username);
		paramsObject.add(password);

		sendObject.put("params", paramsObject);

		String sendMessage = sendObject.toJSONString();

		PrintWriter os = null;
		BufferedReader is = null;
		try {
			os = new PrintWriter(rpcConnection.getOutputStream());
			is = new BufferedReader(new InputStreamReader(rpcConnection.getInputStream()));

			os.println(sendMessage);
			os.flush();

			String returnMessage = is.readLine();

			JSONObject returnObject = JSONObject.parseObject(returnMessage);

			String result = returnObject.getString("result");

			if ("true".equals(result)) {
				login = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return login;
	}

	/**
	 * 从链上获取地址的余额
	 * 
	 * @param address
	 * @return
	 * @throws Exception
	 */
	public BigDecimal blockChainBalance(String address) {
		List<Object> params = new ArrayList<Object>();
		params.add(address);
		try{
			String result = send("blockchain_list_address_balances", params);
			JSONArray jsa = JSONArray.parseArray(result);
			if (jsa == null || jsa.size() == 0) {
				return new BigDecimal(0);
			} else {
				JSONArray amountArr = jsa.getJSONArray(0);
				Long amout = amountArr.getJSONObject(1).getLong("balance");
				return new BigDecimal(amout).divide(new BigDecimal(100000000L));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new BigDecimal(0);
		
	}

	public void setConnection(Socket rpcConnection) {
		this.rpcConnection = rpcConnection;

	}
}
