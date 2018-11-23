package io.credittag.mis.modules.ctc.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.CtcCsgjDao;
import io.credittag.mis.modules.ctc.entity.AccountDetailEntity;
import io.credittag.mis.modules.ctc.entity.CtcCsgjEntity;
import io.credittag.mis.modules.ctc.entity.WithdrawOrderEntity;
import io.credittag.mis.modules.ctc.service.CtcCsgjService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;


@Service("ctcCsgjService")
public class CtcCsgjServiceImpl extends ServiceImpl<CtcCsgjDao, CtcCsgjEntity> implements CtcCsgjService {

	@Autowired
	private CtcCsgjDao ctcCsgjDao;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CtcCsgjEntity> page = this.selectPage(
                new Query<CtcCsgjEntity>(params).getPage(),
                new EntityWrapper<CtcCsgjEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 
     * @Title: ctccsgjlist
     * @Description: 信用报告购买次数
     * @param: @param params
     * @param: @return   
     * @throws
     */
	@Override
	@DataSource(name = DataSourceNames.SECOND)
    public R ctccsgjlist(Map<String, Object> params) {
		Page<CtcCsgjEntity> page = new Query<CtcCsgjEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String customerId = (String) params.get("id");
		String username = (String) params.get("username");

		Date beginDate = null;
		boolean b1 = false;
		if (StringUtils.isNotBlank(beginDates)) {
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
			b1 = true;
		}

		Date endDate = null;
		boolean b2 = false;
		if (StringUtils.isNotBlank(endDates)) {
			b2 = true;
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}

		boolean b3 = false;
		if (StringUtils.isNotBlank(customerId)) {
			customerId = customerId.trim();
			b3 = true;
		}

		boolean b4 = false;
		if (StringUtils.isNotBlank(username)) {
			username = username.trim();
			b3 = true;
		}

		// 查询条件都为空时 不返回
		if (!b1 && !b2 && !b3 && !b4) {
			return R.ok();
		}

		EntityWrapper<CtcCsgjEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates), CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<CtcCsgjEntity>) new EntityWrapper<CtcCsgjEntity>().ge(beginDate != null, "a.create_time", beginDate).le(endDate != null, "a.create_time", endDate)
			        .eq(StringUtils.isNotBlank(customerId), "a.cid", customerId).eq(StringUtils.isNotBlank(username), "c.username", username).isWhere(true);

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<CtcCsgjEntity>) new EntityWrapper<CtcCsgjEntity>().eq(StringUtils.isNotBlank(customerId), "a.cid", customerId)
			        .eq(StringUtils.isNotBlank(username), "c.username", username).isWhere(true);
		} else {
			return R.ok();
		}

		EntityWrapper<CtcCsgjEntity> wrapper = (EntityWrapper<CtcCsgjEntity>) SqlHelper.fillWrapper(page, entityWrapper);

		page.setRecords(ctcCsgjDao.withdraworderlist(page, wrapper));
		
		
				
		return R.ok().put("page", new PageUtils(page));

		
    }
	
	@Override
	@DataSource(name = DataSourceNames.SECOND)
    public R ctccsgjlistTotal(Map<String, Object> params) {
		Page<CtcCsgjEntity> page = new Query<CtcCsgjEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String customerId = (String) params.get("id");
		String username = (String) params.get("username");

		Date beginDate = null;
		boolean b1 = false;
		if (StringUtils.isNotBlank(beginDates)) {
			beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
			b1 = true;
		}

		Date endDate = null;
		boolean b2 = false;
		if (StringUtils.isNotBlank(endDates)) {
			b2 = true;
			endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));
		}

		boolean b3 = false;
		if (StringUtils.isNotBlank(customerId)) {
			customerId = customerId.trim();
			b3 = true;
		}

		boolean b4 = false;
		if (StringUtils.isNotBlank(username)) {
			username = username.trim();
			b3 = true;
		}

		// 查询条件都为空时 不返回
		if (!b1 && !b2 && !b3 && !b4) {
			return R.ok();
		}

		EntityWrapper<CtcCsgjEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates), CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<CtcCsgjEntity>) new EntityWrapper<CtcCsgjEntity>().ge(beginDate != null, "a.create_time", beginDate).le(endDate != null, "a.create_time", endDate)
			        .eq(StringUtils.isNotBlank(customerId), "a.cid", customerId).eq(StringUtils.isNotBlank(username), "c.username", username).isWhere(true);

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<CtcCsgjEntity>) new EntityWrapper<CtcCsgjEntity>().eq(StringUtils.isNotBlank(customerId), "a.cid", customerId)
			        .eq(StringUtils.isNotBlank(username), "c.username", username).isWhere(true);
		} else {
			return R.ok();
		}

		EntityWrapper<CtcCsgjEntity> wrapper = (EntityWrapper<CtcCsgjEntity>) SqlHelper.fillWrapper(page, entityWrapper);

		//page.setRecords(ctcCsgjDao.withdraworderlist(page, wrapper));
		
//		Map<String,String> parammap = new HashMap<String,String>();
//		parammap.put("begin", beginDates);
//		parammap.put("end", endDates);
//		parammap.put("customerId", customerId);
//		parammap.put("username", username);
		CtcCsgjEntity ctcCsgjEntity = ctcCsgjDao.withdraworderlistTotal(wrapper);
		PageUtils p = new PageUtils(page);
		if(ctcCsgjEntity.getCid()!=null&&
				ctcCsgjEntity.getNumber()!=null&&
				ctcCsgjEntity.getAmount()!=null&&
				ctcCsgjEntity.getCtcAmount()!=null) {
			p.setTatalUserCountInThisCondition(ctcCsgjEntity.getCid().intValue());
			p.setBuyCount(ctcCsgjEntity.getNumber());
			p.setMoneyCount(ctcCsgjEntity.getAmount().doubleValue());
			p.setCtcCount(Double.parseDouble(ctcCsgjEntity.getCtcAmount()));
		}
		
				
		return R.ok().put("page", p);

		
    }

}
