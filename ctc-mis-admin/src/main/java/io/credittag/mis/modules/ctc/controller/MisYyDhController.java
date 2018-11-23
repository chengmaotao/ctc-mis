package io.credittag.mis.modules.ctc.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.modules.ctc.entity.MisYyDhEntity;
import io.credittag.mis.modules.ctc.service.MisYyDhService;
import io.credittag.mis.modules.ctc.service.MisYyDhSumService;
import io.credittag.mis.modules.ctc.utils.DateUtil;
import io.credittag.mis.modules.ctc.utils.StringUtilsTool;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.R;

/**
 * 
 *
 * @author
 * @email gen1@ctc
 * @date 2018-06-08 17:52:27
 */
@RestController
@RequestMapping("ctc/misyydh")
public class MisYyDhController {
	@Autowired
	private MisYyDhService misYyDhService;
	@Autowired
	private MisYyDhSumService misYyDhSumService;

	/**
	 * 兑换信息明细
	 */
	@RequestMapping("/listmx")
	@RequiresPermissions("ctc:misyydhmx:read")
	public R listMx(@RequestParam Map<String, Object> params) {
		PageUtils page = misYyDhService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 获取兑换信息列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ctc:misyydh:list")
	public R list(@RequestParam Map<String, Object> params) {

		return misYyDhSumService.queryPageSum(params);
	}

	/**
	 * 保存兑换信息
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ctc:misyydh:save")
	public R save(@RequestBody MisYyDhEntity misYyDh) {
		Integer sumAccount = misYyDh.getSumCount();
		String pid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < sumAccount; i++) {
			try {
				MisYyDhEntity misYyDhEntity = new MisYyDhEntity();
				String dhCode = StringUtilsTool.getRandomString(7);
				misYyDhEntity.setDhcode(dhCode);
				List<MisYyDhEntity> list = misYyDhService.queryByDhCode(dhCode);
				while (list.size() > 0) {
					dhCode = StringUtilsTool.getRandomString(7);
					misYyDhEntity.setDhcode(dhCode);
					list = misYyDhService.queryByDhCode(dhCode);
				}
				Date expriTime=DateUtil.parseDate(misYyDh.getExpireTime(), DateUtil.C_DATE_PATTON_DEFAULT);
				expriTime.setHours(23);
				expriTime.setMinutes(59);
				expriTime.setSeconds(59);
				misYyDhEntity.setCreateTime(new Date());
				misYyDhEntity.setUpdateTime(new Date());
				misYyDhEntity.setExpireTime(expriTime);
				misYyDhEntity.setPid(pid);
				misYyDhEntity.setCount(misYyDh.getCount());
				misYyDhEntity.setStatus(0);
				misYyDhEntity.setType(misYyDh.getType());
				misYyDhEntity.setRemark(misYyDh.getRemark());
				misYyDhService.insert(misYyDhEntity);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return R.ok();
	}
}
