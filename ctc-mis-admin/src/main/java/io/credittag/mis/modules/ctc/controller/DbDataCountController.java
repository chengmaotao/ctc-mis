package io.credittag.mis.modules.ctc.controller;

import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.service.DbDataCountService;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * CTC 财神管家的响应表
 * 
 * @author gen
 * @email gen@ctc
 * @date 2018-05-31 20:24:12
 */
@RestController
@RequestMapping("ctc/dbdataCount")
public class DbDataCountController {
	@Autowired
	private DbDataCountService dbDataCountService;

	
	@RequestMapping("/dbdataCountList")
	@RequiresPermissions("ctc:dbdatacount:list")
	public R dbDataCount(@RequestParam Map<String, Object> params) {

		return dbDataCountService.dbDataCount(params);

	}
}
