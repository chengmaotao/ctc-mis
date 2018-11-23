package io.credittag.mis.modules.ctc.service.impl;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.CustomerDao;
import io.credittag.mis.modules.ctc.entity.AccountDetailEntity;
import io.credittag.mis.modules.ctc.entity.CustomerEntity;
import io.credittag.mis.modules.ctc.entity.DbDataCountEntity;
import io.credittag.mis.modules.ctc.service.CustomerService;
import io.credittag.mis.modules.ctc.utils.ConfigUtils;
import io.credittag.mis.modules.ctc.utils.CtcMisUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public PageUtils queryPage(Map<String, Object> params) {
		Page<CustomerEntity> page = this.selectPage(new Query<CustomerEntity>(params).getPage(),
				new EntityWrapper<CustomerEntity>().like(StringUtils.isNotBlank((String) params.get("username")), "username",
						(String)params.get("username"), SqlLike.DEFAULT));

		return new PageUtils(page);
	}

	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public CustomerEntity selectById(Serializable id) {
		// TODO Auto-generated method stub
		return super.selectById(id);
	}

	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R updateRewardById(CustomerEntity entity) {
		R error = R.error();
		if (null == entity) {
			error.put("code", ConstantField.error_code_99);
			error.put("msg", "系统参数异常");
			return error;
		} else if (null == entity.getId()) {
			error.put("code", ConstantField.error_code_99);
			error.put("msg", "系统参数异常");
			return error;
		} else if (entity.getInviteRewardCount() == 0) {
			error.put("code", ConstantField.error_code_99);
			error.put("msg", "邀请次数异常");
			return error;
		}
		CustomerEntity customer = customerDao.selectById(entity.getId());
		if (customer.getInviteCount() > entity.getInviteRewardCount()) {
			error.put("code", ConstantField.error_code_99);
			error.put("msg", "已邀请用户数大于设置奖励次数，设置失败，设置次数应大于奖励次数,已奖励次数为" + customer.getInviteCount());
			return error;
		}
		switch(entity.getStatus()) {
		case "已注册" :entity.setStatus(1);
		case "已发送验证码": entity.setStatus(0);
		case "冻结":entity.setStatus(2);
		case "已登录": entity.setStatus(9);
		
			
		}
		entity.setUpdateTime(new Date());
		customerDao.updateRewardById(entity);
		return R.ok();
	}

	/**
	 * 
	 * @Title: customerInvitationlist @Description: 成功发起邀请人明细 @param: @param
	 *         params @param: @return @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R customerInvitationlist(Map<String, Object> params) {
		return  this.serverForcustomerInvitationlistself(params);
			
	}
	
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R customerInvitationlistTotal(Map<String, Object> params) {
		
		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String customerId = (String) params.get("id");
		String username = (String) params.get("username");
		Map<String,String> map = new HashMap<String,String>();
		map.put("beginDates", beginDates);
		map.put("endDates", endDates);
		map.put("customerId", customerId);
		map.put("username", username);
		CustomerEntity customerEntity = customerDao.customerInvitationlistTotalself(map);
		PageUtils p = new PageUtils(page);
		
		if(customerEntity.getId()!=null) {
			p.setTatalUserCountInThisCondition(customerEntity.getId().intValue());
		}
		if(customerEntity.getInviteCount()!=null) {
			p.setTotalDetailsInThisCondition(customerEntity.getInviteCount());
		}
		R ok = R.ok();

		ok.put("page", p);

		return ok;
	}
	public R serverForcustomerInvitationlistself(Map<String,Object> params){
		Map<String,String> map = new HashMap<String,String>();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String customerId = (String) params.get("id");
		String username = (String) params.get("username");
		map.put("beginDates", beginDates);
		map.put("endDates", endDates);
		map.put("customerId", customerId);
		map.put("username", username);
		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
		page.setRecords(customerDao.customerInvitationlistself(page,map));
		R ok = R.ok();
		ok.put("page", new PageUtils(page));
		return ok;
	}
	public R serverForcustomerInvitationlist(Map<String,Object> params){
		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
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

		EntityWrapper<CustomerEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
					CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>()
					.ge(beginDate != null, "create_time", beginDate).le(endDate != null, "create_time", endDate)
					.eq(StringUtils.isNotBlank(customerId), "id", customerId)
					.eq(StringUtils.isNotBlank(username), "username", username).gt("invite_count", 0).isWhere(true);

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>()
					.eq(StringUtils.isNotBlank(customerId), "id", customerId)
					.eq(StringUtils.isNotBlank(username), "username", username).gt("invite_count", 0).isWhere(true);
		} else {
			return R.ok();
		}

		EntityWrapper<CustomerEntity> wrapper = (EntityWrapper<CustomerEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);

		page.setRecords(customerDao.customerInvitationlist(page, wrapper));

		R ok = R.ok();

		ok.put("page", new PageUtils(page));

		return ok;
	}

	/**
	 * 
	 * @Title: customerbeInvitedlist @Description: 被邀请人明细 @param: @param
	 *         params @param: @return @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R customerbeInvitedlist(Map<String, Object> params) {
		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String customerId = (String) params.get("id");
		String username = (String) params.get("username");
		String status = (String) params.get("status");
		String phoneN = ((String)params.get("phoneN")).trim();//被邀请人手机号
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("beginT",beginDates);
		map.put("endT", endDates);
		map.put("id", customerId);
		map.put("username",username);
		map.put("status",status);
		map.put("phoneN", phoneN);
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

		EntityWrapper<CustomerEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
					CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>().and()
					.ge(beginDate != null, "create_time", beginDate).le(endDate != null, "create_time", endDate)
					.eq(StringUtils.isNotBlank(customerId), "id", customerId)
					.eq(StringUtils.isNotBlank(username), "username", username);

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>().and()
					.eq(StringUtils.isNotBlank(customerId), "id", customerId)
					.eq(StringUtils.isNotBlank(username), "username", username);
		} else {
			return R.ok();
		}
		
		
		
		EntityWrapper<CustomerEntity> wrapper = (EntityWrapper<CustomerEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);
		wrapper.eq(StringUtils.isNotBlank(status), "status", status);
		
		//page.setRecords(customerDao.customerbeInvitedlist(page, wrapper));
		
		page.setRecords(customerDao.customerbeInvitedlistUseMap(page,map));
		
		R ok = R.ok();

		ok.put("page", new PageUtils(page));

		return ok;
	}

	/**
	 * 
	 * @Title: customerDataCount @Description: 邀请统计数据 @param: @param
	 *         params @param: @return @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R customerDataCount(Map<String, Object> params) {
		
		return this.serverForcustomerDataCount(params);
		
				
	}
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R customerDataCountTotal(Map<String, Object> params) {
		params.put("page", null);
		params.put("limit", null);
		params.put("total", "true");
		R r = this.serverForcustomerDataCount(params);
		if((int)r.get("code")!=0) {
			//出错
			return R.ok().put("page", new PageUtils(new Page<CustomerEntity>()));
		}
		PageUtils ptotal  =  (PageUtils) r.get("page");
		//新加累计领取数量，累计用户
				int invitusercount = 0;
				int invitedusercountAndInit = 0;
				int invitedUserCountAndlogin = 0;
				
				
				List<CustomerEntity> list = (List<CustomerEntity>) ptotal.getList() ;
				if(list!=null&&list.size()>0) {
					for(CustomerEntity customerEntity : list) {
						invitusercount += Integer.parseInt(customerEntity.getSumInviteCount());
					    invitedusercountAndInit += Integer.parseInt(customerEntity.getCounts());
						invitedUserCountAndlogin += Integer.parseInt(customerEntity.getLoginCounts());
					}
				}
				
				
				ptotal.setTatalUserCountInThisCondition(invitusercount);
				ptotal.setCtcCount(invitedusercountAndInit);
				ptotal.setBuyCount(invitedUserCountAndlogin);
				
				return R.ok().put("page", ptotal);
		
	}
	public R serverForcustomerDataCount(Map<String, Object> params) {
		// 原始参数 北京时间
		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");

		// 日期为空时 不返回
		if (StringUtils.isBlank(beginDates) || StringUtils.isBlank(endDates)) {
			return R.ok();
		}

		// 参数 UTC时间
		Date beginDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMinDateByDay(beginDates));
		Date endDate = CtcMisUtils.getDateToUTC(CtcMisUtils.getMaxDateByDay(endDates));

		int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
				CtcMisUtils.StringToDate(endDates));
		if (getdiffBytwoDate >= ConstantField.max_date_long) {
			R error = R.error();
			error.put("code", ConstantField.error_code_99);
			error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
			return error;
		}
		List<CustomerEntity> customerDataCount = customerDataCount(beginDate, endDate, page, getdiffBytwoDate);
		if(params.get("total")!=null&&params.get("total").equals("true")) {
			//进行汇总
			
			page.setRecords(customerDataCount);
			page.setTotal(customerDataCount.size());
			R ok = R.ok();
			ok.put("page", new PageUtils(page));
			return ok;
		}else {
			//查询数据
			Integer index =  Integer.parseInt(((String) params.get("page")).trim());
			Integer limit =  Integer.parseInt(((String) params.get("limit")).trim());
			List<CustomerEntity> customerDataCountNeed=null;
			if(customerDataCount.size()>0) {
				if(customerDataCount.size()<index*limit) {
					customerDataCountNeed = customerDataCount.subList((index-1)*limit, customerDataCount.size());
				}else {
					customerDataCountNeed = customerDataCount.subList((index-1)*limit,index*limit);
				}
			}
			page.setRecords(customerDataCountNeed);
			page.setTotal(customerDataCount.size());
			R ok = R.ok();
			ok.put("page", new PageUtils(page));
			return ok;
			
			
		}
		
	}
	

	/**
	 * 
	 * @Title: customerDataCount @Description: 邀请统计 @param: @param beginDate
	 *         开始日期 @param: @param endDate 结束日期 @param: @param
	 *         page @param: @param getdiffBytwoDate 两个日期间隔 @return: void @throws
	 */
	private List<CustomerEntity> customerDataCount(Date beginDate, Date endDate, Page<CustomerEntity> page,
			int getdiffBytwoDate) {

		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setParamMinDate(beginDate);
		customerEntity.setParamMaxDate(endDate);

		// 成功发起邀请用户（发放CTC 的）
		List<CustomerEntity> CTCCountByDate = customerDao.getCTCCountByDate(customerEntity);

		// 被邀请注册数
		customerEntity.setSqlelement("AND (status = 1 or status = 0) ");
		List<CustomerEntity> counts = customerDao.getCounts(customerEntity);

		// 被邀请登录数
		customerEntity.setSqlelement("AND status = 1 ");
		List<CustomerEntity> loginCounts = customerDao.getCounts(customerEntity);

		List<CustomerEntity> res = new ArrayList<CustomerEntity>();
		CustomerEntity temp = null;
		Date tempDate = null;
		String formatDate = null;
		for (int i = getdiffBytwoDate; i >= 0; i--) {
			temp = new CustomerEntity();
			tempDate = DateUtils.addDays(CtcMisUtils.getUTCDateToDate(beginDate), i);
			formatDate = CtcMisUtils.formatDate(tempDate, ConstantField.vodateFormatPattern);

			temp.setSumInviteCount("0"); //
			temp.setCounts("0"); // 被邀请注册数
			temp.setLoginCounts("0"); // 被邀请登录数

			// 成功发起邀请用户（发放CTC 的）
			for (CustomerEntity t1 : CTCCountByDate) {
				if (StringUtils.equals(t1.getVoStrDate(), formatDate)) {
					temp.setSumInviteCount(t1.getSumInviteCount()); //
					break;
				}
			}

			// 被邀请注册数
			for (CustomerEntity t2 : counts) {
				if (StringUtils.equals(t2.getVoStrDate(), formatDate)) {
					temp.setCounts(t2.getCounts()); // 被邀请注册数
					break;
				}
			}
			// 被邀请登录数
			for (CustomerEntity t3 : loginCounts) {
				if (StringUtils.equals(t3.getVoStrDate(), formatDate)) {
					temp.setLoginCounts(t3.getCounts()); // 被邀请登录数
					break;
				}
			}
			temp.setVoStrDate(formatDate);
			res.add(temp);
		}
		return res;

	}

	/**
	 * 
	 * @Title: customerdetaillist @Description: 矿场人数明细 @param: @param
	 *         params @param: @return @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R customerdetaillist(Map<String, Object> params) {

		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String customerId = (String) params.get("id");
		String username = (String) params.get("username");
		String status =(String) params.get("status");

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

		EntityWrapper<CustomerEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
					CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>()
					.ge(beginDate != null, "create_time", beginDate).le(endDate != null, "create_time", endDate)
					.eq(StringUtils.isNotBlank(customerId), "id", customerId)
					.eq(StringUtils.isNotBlank(username), "username", username).isWhere(true);

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>()
					.eq(StringUtils.isNotBlank(customerId), "id", customerId)
					.eq(StringUtils.isNotBlank(username), "username", username).isWhere(true);
		} else {
			return R.ok();
		}
		if(status!=null&&!"null".equals(status)){
			entityWrapper.eq("status", status);
		}

		EntityWrapper<CustomerEntity> wrapper = (EntityWrapper<CustomerEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);

		page.setRecords(customerDao.customerdetaillist(page, wrapper));
		
		
		return R.ok().put("page", new PageUtils(page));

		
	}
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R customerdetaillistTotal(Map<String, Object> params) {

		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
		String beginDates = (String) params.get("beginDate");
		String endDates = (String) params.get("endDate");
		String customerId = (String) params.get("id");
		String username = (String) params.get("username");
		String status =(String) params.get("status");

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

		EntityWrapper<CustomerEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
					CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>()
					.ge(beginDate != null, "create_time", beginDate).le(endDate != null, "create_time", endDate)
					.eq(StringUtils.isNotBlank(customerId), "id", customerId)
					.eq(StringUtils.isNotBlank(username), "username", username).isWhere(true);

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>()
					.eq(StringUtils.isNotBlank(customerId), "id", customerId)
					.eq(StringUtils.isNotBlank(username), "username", username).isWhere(true);
		} else {
			return R.ok();
		}
		if(status!=null&&!"null".equals(status)){
			entityWrapper.eq("status", status);
		}

		EntityWrapper<CustomerEntity> wrapper = (EntityWrapper<CustomerEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);

		//page.setRecords(customerDao.customerdetaillist(page, wrapper));
		
		CustomerEntity customerEntity = customerDao.customerdetaillistTotal(wrapper);
		PageUtils p = new PageUtils(page);
		
		if(customerEntity.getId()!=null) {
			p.setTatalUserCountInThisCondition(customerEntity.getId().intValue());
		}
	
		
		return R.ok().put("page", p);

		
	}

	/**
	 * 
	 * @Title: whiteCustomerList @Description: CTC手机号白名单明细 @param: @param
	 *         params @param: @return @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R whiteCustomerList(Map<String, Object> params) {
		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
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

		EntityWrapper<CustomerEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
					CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>();

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>();
		} else {
			return R.ok();
		}
		CustomerEntity param = new CustomerEntity();
		param.setParamMinDate(beginDate);
		param.setParamMaxDate(endDate);
		param.setCid(customerId);
		param.setUsername(username);

		entityWrapper.setEntity(param);
		EntityWrapper<CustomerEntity> wrapper = (EntityWrapper<CustomerEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);

		page.setRecords(customerDao.whiteCustomerList(page, wrapper));

		//新加累计领取数量，累计用户
				double totalDetailsInPage=0;
				int userNum=0;
				Set<Long> set = new HashSet();
				List<CustomerEntity> list = (List<CustomerEntity>) page.getRecords();
				if(list!=null&&list.size()>0) {
					
					for(CustomerEntity customerEntity : list) {
						userNum+=1;
						
					}
				}
				PageUtils p = new PageUtils(page);
				
				p.setTatalUserCountInThisCondition(userNum);
				
				return R.ok().put("page", p);
				
		
	}
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R whiteCustomerListTotal(Map<String, Object> params) {
		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
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

		EntityWrapper<CustomerEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
					CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>();

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>();
		} else {
			return R.ok();
		}
		CustomerEntity param = new CustomerEntity();
		param.setParamMinDate(beginDate);
		param.setParamMaxDate(endDate);
		param.setCid(customerId);
		param.setUsername(username);

		entityWrapper.setEntity(param);
		EntityWrapper<CustomerEntity> wrapper = (EntityWrapper<CustomerEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);

		//page.setRecords(customerDao.whiteCustomerList(page, wrapper));
		PageUtils p = new PageUtils(page);
		CustomerEntity customerEntity = customerDao.whiteCustomerListTotal(wrapper);
		if(customerEntity.getId()!=null) {
			p.setTatalUserCountInThisCondition(customerEntity.getId().intValue());
		}
		
		return R.ok().put("page", p);
				
		
	}

	/**
	 * 
	 * @Title: customerbuylist @Description: 信用报告购买人数明细 @param: @param
	 *         params @param: @return @throws
	 */
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R customerbuylist(Map<String, Object> params) {
		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
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

		EntityWrapper<CustomerEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
					CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>();

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>();
		} else {
			return R.ok();
		}
		CustomerEntity param = new CustomerEntity();
		param.setParamMinDate(beginDate);
		param.setParamMaxDate(endDate);
		param.setCid(customerId);
		param.setUsername(username);

		entityWrapper.setEntity(param);
		EntityWrapper<CustomerEntity> wrapper = (EntityWrapper<CustomerEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);

		page.setRecords(customerDao.customerbuylist(page, wrapper));
		
		return R.ok().put("page", new PageUtils(page));

	}
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public R customerbuylistTotal(Map<String, Object> params) {
		Page<CustomerEntity> page = new Query<CustomerEntity>(params).getPage();
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

		EntityWrapper<CustomerEntity> entityWrapper = null;
		if (b1 && b2) {
			int getdiffBytwoDate = CtcMisUtils.getdiffBytwoDate(CtcMisUtils.StringToDate(beginDates),
					CtcMisUtils.StringToDate(endDates));
			if (getdiffBytwoDate >= ConstantField.max_date_long) {
				R error = R.error();
				error.put("code", ConstantField.error_code_99);
				error.put("msg", ConfigUtils.getValue("date_long_error", ConstantField.max_date_long));
				return error;
			}

			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>();

		} else if (b3 || b4) {
			entityWrapper = (EntityWrapper<CustomerEntity>) new EntityWrapper<CustomerEntity>();
		} else {
			return R.ok();
		}
		CustomerEntity param = new CustomerEntity();
		param.setParamMinDate(beginDate);
		param.setParamMaxDate(endDate);
		param.setCid(customerId);
		param.setUsername(username);

		entityWrapper.setEntity(param);
		EntityWrapper<CustomerEntity> wrapper = (EntityWrapper<CustomerEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);

		//page.setRecords(customerDao.customerbuylist(page, wrapper));
		CustomerEntity customerEntity = customerDao.customerbuylistTotal(wrapper);
		PageUtils p = new PageUtils(page);
		if(customerEntity.getId()!=null) {
			p.setTatalUserCountInThisCondition(customerEntity.getId().intValue());
		}
		//p.setTatalUserCountInThisCondition(userNum);
				
		return R.ok().put("page", p);

	}

}
