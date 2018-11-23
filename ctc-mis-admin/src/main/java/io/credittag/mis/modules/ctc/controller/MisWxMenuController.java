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

import io.credittag.mis.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import io.credittag.mis.modules.ctc.entity.MisWxMenuEntity;
import io.credittag.mis.modules.ctc.service.MisWxMenuService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-11 15:02:54
 */
@RestController
@RequestMapping("ctc/miswxmenu")
public class MisWxMenuController {
	private static final String SYN_DATA_MENU_URL = "https://wx.wecredit.io/api/synMenuData";
	@Autowired
	private MisWxMenuService misWxMenuService;

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ctc:miswxmenu:list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = misWxMenuService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ctc:miswxmenu:info")
	public R info(@PathVariable("id") Long id) {
		MisWxMenuEntity misWxMenu = misWxMenuService.selectById(id);

		return R.ok().put("misWxMenu", misWxMenu);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ctc:miswxmenu:save")
	public R save(@RequestBody MisWxMenuEntity misWxMenu) {
		misWxMenu.setMenuJson(misWxMenu.getMenuJson().replaceAll("&amp;","&"));
		misWxMenuService.insert(misWxMenu);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ctc:miswxmenu:update")
	public R update(@RequestBody MisWxMenuEntity misWxMenu) {
		ValidatorUtils.validateEntity(misWxMenu);
		misWxMenu.setMenuJson(misWxMenu.getMenuJson().replaceAll("&amp;","&"));
		misWxMenuService.updateAllColumnById(misWxMenu);// 全部更新

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ctc:miswxmenu:delete")
	public R delete(@RequestBody Long[] ids) {
		misWxMenuService.deleteBatchIds(Arrays.asList(ids));

		return R.ok();
	}

	@RequestMapping("/uptowx")
	public R uptowx() {
		JSONObject jSONObject = new JSONObject();
		String req = getJsonData(jSONObject, SYN_DATA_MENU_URL);
		JSONObject resJson = JSONObject.parseObject(req);
		if (resJson.containsKey("code")) {
			if (resJson.getString("code").equals("0000")) {
				return R.ok("发布成功");
			} else {
				return R.error(5, "调用接口同步失败");
			}
		} else {
			return R.error(6, "接口调用失败");
		}
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
