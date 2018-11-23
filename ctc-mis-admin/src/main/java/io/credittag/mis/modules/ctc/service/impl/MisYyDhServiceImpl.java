package io.credittag.mis.modules.ctc.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.dao.MisYyDhDao;
import io.credittag.mis.modules.ctc.entity.MisYyDhEntity;
import io.credittag.mis.modules.ctc.service.MisYyDhService;

@Service("misYyDhService")
public class MisYyDhServiceImpl extends ServiceImpl<MisYyDhDao, MisYyDhEntity> implements MisYyDhService {
	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<MisYyDhEntity> page = this.selectPage(new Query<MisYyDhEntity>(params).getPage(),
				new EntityWrapper<MisYyDhEntity>().eq("pid", params.get("pId")));

		return new PageUtils(page);
	}

	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public boolean insert(MisYyDhEntity entity) {
		return super.insert(entity);
	}

	@DataSource(name = DataSourceNames.SECOND)
	@Override
	public List<MisYyDhEntity> queryByDhCode(String dhCode) {
		return baseMapper.queryByDhCode(dhCode);
	}
}
