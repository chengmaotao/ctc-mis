package io.credittag.mis.modules.ctc.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.dao.MisYyDhSumDao;
import io.credittag.mis.modules.ctc.entity.MisYySumEntity;
import io.credittag.mis.modules.ctc.service.MisYyDhSumService;

@Service("misYyDhSumService")
public class MisYyDhSumServiceImpl extends ServiceImpl<MisYyDhSumDao, MisYySumEntity> implements MisYyDhSumService {

	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public R queryPageSum(Map<String, Object> params) {
		Page<MisYySumEntity> page = new Query<MisYySumEntity>(params).getPage();
		EntityWrapper<MisYySumEntity> entityWrapper = (EntityWrapper<MisYySumEntity>) new EntityWrapper<MisYySumEntity>();

		EntityWrapper<MisYySumEntity> wrapper = (EntityWrapper<MisYySumEntity>) SqlHelper.fillWrapper(page,
				entityWrapper);
		entityWrapper.orderBy("createTime", false);
		page.setRecords(baseMapper.queryPageSum(page, wrapper));
		return R.ok().put("page", new PageUtils(page));
	}

}
