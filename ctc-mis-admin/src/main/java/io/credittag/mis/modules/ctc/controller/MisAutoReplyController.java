package io.credittag.mis.modules.ctc.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.credittag.mis.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sun.net.httpserver.HttpServer;

import io.credittag.mis.modules.ctc.entity.MisAutoReplyEntity;
import io.credittag.mis.modules.ctc.service.MisAutoReplyService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-10 11:56:46
 */
@RestController
@RequestMapping("ctc/misautoreply")
public class MisAutoReplyController { 
	private static final String SYN_DATA_URL = "https://wx.wecredit.io/api/synYjData";
	@Autowired
	private MisAutoReplyService misAutoReplyService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ctc:misautoreply:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = misAutoReplyService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ctc:misautoreply:info")
	public R info(@PathVariable("id") Long id) {
		MisAutoReplyEntity misAutoReply = misAutoReplyService.selectById(id);

		return R.ok().put("misAutoReply", misAutoReply);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ctc:misautoreply:save")
	public R save(@RequestBody MisAutoReplyEntity misAutoReply) {
		misAutoReplyService.insert(misAutoReply);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ctc:misautoreply:update")
	public R update(@RequestBody MisAutoReplyEntity misAutoReply) {
		ValidatorUtils.validateEntity(misAutoReply);
		misAutoReplyService.updateAllColumnById(misAutoReply);// 全部更新

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ctc:misautoreply:delete")
	public R delete(@RequestBody Long[] ids) {
		misAutoReplyService.deleteBatchIds(Arrays.asList(ids));

		return R.ok();
	}

	/**
	 * 同步数据
	 */
	@RequestMapping("/sysnData")
	@RequiresPermissions("ctc:misautoreply:sysnData")
	public R sysnData(HttpServletRequest request) {
		String type=request.getParameter("type");
		int count = 0;
		if (type.equalsIgnoreCase("news")) {
			count = misAutoReplyService.msgCount("news");
		} else if (type.equalsIgnoreCase("image")) {
			count = misAutoReplyService.msgCount("image");
		} else {
			return R.error(4, "类型非法");
		}
		JSONObject jSONObject = new JSONObject();
		jSONObject.put("offet", count);
		jSONObject.put("type", type);
		String req = getJsonData(jSONObject, SYN_DATA_URL);
		JSONObject resJson = JSONObject.parseObject(req);
		if (resJson.containsKey("code")) {
			if (resJson.getString("code").equals("0000")) {
				return R.ok("同步成功");
			} else {
				return R.error(5, "调用接口同步失败");
			}
		} else {
			return R.error(6, "接口调用失败");
		}
	}

	public static void main(String args[]) {
		int count = 0;
		String type = "news";
		JSONObject jSONObject = new JSONObject();
		jSONObject.put("offet", count);
		jSONObject.put("type", type);
		String req = getJsonData(jSONObject, SYN_DATA_URL);
		System.out.println(req);
	}

	public static String getJsonData(JSONObject jsonParam, String urls) {
		StringBuffer sb = new StringBuffer();
		try {
			// 创建url资源
			URL url = new URL(urls);
			// 建立http连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置允许输出
			conn.setDoOutput(true);
			// 设置允许输入
			conn.setDoInput(true);
			// 设置不用缓存
			conn.setUseCaches(false);
			// 设置传递方式
			conn.setRequestMethod("POST");
			// 设置维持长连接
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 设置文件字符集:
			conn.setRequestProperty("Charset", "UTF-8");
			// 转换为字节数组
			byte[] data = (jsonParam.toString()).getBytes();
			// 设置文件长度
			conn.setRequestProperty("Content-Length", String.valueOf(data.length));
			// 设置文件类型:
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			// 开始连接请求
			conn.connect();
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// 写入请求的字符串
			out.write((jsonParam.toString()).getBytes());
			out.flush();
			out.close();

			// 请求返回的状态
			if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
				System.out.println("连接成功");
				// 请求返回的数据
				InputStream in1 = conn.getInputStream();

				String readLine = new String();
				BufferedReader responseReader = new BufferedReader(new InputStreamReader(in1, "UTF-8"));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				responseReader.close();

			} else {
			}

		} catch (Exception e) {
		}

		return sb.toString();

	}

}
