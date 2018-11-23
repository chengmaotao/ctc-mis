package io.credittag.mis.modules.ctc.service.impl;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.constant.ConstantField;
import io.credittag.mis.modules.ctc.dao.CustomerVerifyCodeDao;
import io.credittag.mis.modules.ctc.entity.CustomerVerifyCodeEntity;
import io.credittag.mis.modules.ctc.service.CustomerVerifyCodeService;

@Service("customerVerifyCodeService")
public class CustomerVerifyCodeServiceImpl extends ServiceImpl<CustomerVerifyCodeDao, CustomerVerifyCodeEntity>
		implements CustomerVerifyCodeService {
	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public PageUtils queryPageRegister(Map<String, Object> params) {

		Page<CustomerVerifyCodeEntity> page = new Query<CustomerVerifyCodeEntity>(params).getPage();

		if (params == null || params.get("phoneNo") == null) {

		} else {
			String mobile = (String) params.get("phoneNo");

			Date beginDate = DateUtils.addMinutes(new Date(), ConstantField.VERIFY_CODE_TIME);

			params.put("beginDate", beginDate);

			EntityWrapper<CustomerVerifyCodeEntity> entityWrapper = (EntityWrapper<CustomerVerifyCodeEntity>) new EntityWrapper<CustomerVerifyCodeEntity>()
					.like("c.username", mobile, SqlLike.LEFT).and().gt("a.create_time", beginDate).isWhere(true);
			EntityWrapper<CustomerVerifyCodeEntity> wrapper = (EntityWrapper<CustomerVerifyCodeEntity>) SqlHelper
					.fillWrapper(page, entityWrapper);
			page.setRecords(baseMapper.getRecordByMobileRegister(page, wrapper));

		}
		return new PageUtils(page);
	}

	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public PageUtils queryPageWithDraw(Map<String, Object> params) {

		Page<CustomerVerifyCodeEntity> page = new Query<CustomerVerifyCodeEntity>(params).getPage();

		if (params == null || params.get("phoneNo") == null) {

		} else {
			String mobile = (String) params.get("phoneNo");

			Date beginDate = DateUtils.addMinutes(new Date(), ConstantField.VERIFY_CODE_TIME);

			params.put("beginDate", beginDate);

			EntityWrapper<CustomerVerifyCodeEntity> entityWrapper = (EntityWrapper<CustomerVerifyCodeEntity>) new EntityWrapper<CustomerVerifyCodeEntity>()
					.like("c.username", mobile, SqlLike.LEFT).and().gt("a.create_time", beginDate).isWhere(true);
			EntityWrapper<CustomerVerifyCodeEntity> wrapper = (EntityWrapper<CustomerVerifyCodeEntity>) SqlHelper
					.fillWrapper(page, entityWrapper);
			page.setRecords(baseMapper.getRecordByMobileWithDraw(page, wrapper));

		}
		return new PageUtils(page);
	}

	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public PageUtils queryPageDl(Map<String, Object> params) {

		Page<CustomerVerifyCodeEntity> page = new Query<CustomerVerifyCodeEntity>(params).getPage();

		if (params == null || params.get("phoneNo") == null) {

		} else {
			String mobile = (String) params.get("phoneNo");

			Date beginDate = DateUtils.addMinutes(new Date(), ConstantField.VERIFY_CODE_TIME);

			params.put("beginDate", beginDate);

			EntityWrapper<CustomerVerifyCodeEntity> entityWrapper = (EntityWrapper<CustomerVerifyCodeEntity>) new EntityWrapper<CustomerVerifyCodeEntity>()
					.like("c.username", mobile, SqlLike.LEFT).and().gt("a.create_time", beginDate).isWhere(true);
			EntityWrapper<CustomerVerifyCodeEntity> wrapper = (EntityWrapper<CustomerVerifyCodeEntity>) SqlHelper
					.fillWrapper(page, entityWrapper);
			page.setRecords(baseMapper.getRecordByMobileDl(page, wrapper));

		}
		return new PageUtils(page);
	}

}
