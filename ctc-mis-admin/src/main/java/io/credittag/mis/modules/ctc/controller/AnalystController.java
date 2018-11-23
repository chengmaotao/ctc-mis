package io.credittag.mis.modules.ctc.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.service.AnalystService;

@RestController
@RequestMapping("ctc/analyst")
public class AnalystController {
	@Autowired
	private AnalystService analystService;

	@RequestMapping("/list")
	@RequiresPermissions("ctc:analyst:list")
	public R list(@RequestParam Map<String, Object> params) {
		return analystService.queryPage(params);
	}
}
