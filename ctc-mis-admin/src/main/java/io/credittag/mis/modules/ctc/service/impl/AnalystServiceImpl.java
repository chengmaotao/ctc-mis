package io.credittag.mis.modules.ctc.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.credittag.mis.common.utils.DateUtils;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.AnalystDao;
import io.credittag.mis.modules.ctc.entity.AccountDetailEntity;
import io.credittag.mis.modules.ctc.entity.AnalystEntity;
import io.credittag.mis.modules.ctc.service.AnalystService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

@Service("analystService")
public class AnalystServiceImpl extends ServiceImpl<AnalystDao, AnalystEntity> implements AnalystService {
	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public R queryPage(Map<String, Object> params) {
		Page<AnalystEntity> page = new Query<AnalystEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String phoneNo = (String) params.get("phoneNo");
		Date beginDate = null;
		if (!StringUtils.isEmpty(beginDates)) {
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
		}

		Date endDate = null;
		if (!StringUtils.isEmpty(endDates)) {
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}
		int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
				CtcMisUtils.StringToDate(endDates));
		if (getdiffBytwoDate >= ConstantField.max_date_long) {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
			return error;
		}
		String sql = "SELECT a.`username` as phoneNo,a.id as id,"
				+ "(SELECT SUM(b.`amount`) FROM ctc_detail b WHERE a.id=b.`cid` AND b.`reason`='mission_csgj'"
				+ " and b.create_time >= '" + CtcMisUtils.formatDate(beginDate, ConstantField.dateFormatPattern)
				+ "' and b.update_time< '" + CtcMisUtils.formatDate(endDate, ConstantField.dateFormatPattern) + "'"
				+ ") AS ctcAmount,(SELECT SUM(c.`amount`) FROM ctc_detail c WHERE a.id=c.`cid` AND c.`reason`='diamond_exchange'"
				+ "and	c.create_time >= '" + CtcMisUtils.formatDate(beginDate, ConstantField.dateFormatPattern)
				+ "' and c.update_time< '" + CtcMisUtils.formatDate(endDate, ConstantField.dateFormatPattern)
				+ "') AS dhCtcAmount,(SELECT SUM(d.`amount`) FROM ctc_detail d WHERE a.id=d.`cid` AND d.`reason`='withdraw'"
				+ "and	d.create_time >= '" + CtcMisUtils.formatDate(beginDate, ConstantField.dateFormatPattern)
				+ "' and d.update_time< '" + CtcMisUtils.formatDate(endDate, ConstantField.dateFormatPattern)
				+ "') AS txCtcAmount FROM customer a " + (null == phoneNo ? "" : " where a.userName='" + phoneNo + "'");
		EntityWrapper<AnalystEntity> entityWrapper = (EntityWrapper<AnalystEntity>) new EntityWrapper<AnalystEntity>()
				.setSqlSelect(sql);
		EntityWrapper<AnalystEntity> wrapper = (EntityWrapper<AnalystEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);
		page.setRecords(baseMapper.getRecord(page, wrapper));
		return R.ok().put("page", new PageUtils(page));
	}

}
