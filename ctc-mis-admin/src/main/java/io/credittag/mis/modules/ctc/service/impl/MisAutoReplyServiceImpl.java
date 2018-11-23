package io.credittag.mis.modules.ctc.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;

import io.credittag.mis.modules.ctc.dao.MisAutoReplyDao;
import io.credittag.mis.modules.ctc.entity.MisAutoReplyEntity;
import io.credittag.mis.modules.ctc.service.MisAutoReplyService;

@Service("misAutoReplyService")
public class MisAutoReplyServiceImpl extends ServiceImpl<MisAutoReplyDao, MisAutoReplyEntity>
		implements MisAutoReplyService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		EntityWrapper<MisAutoReplyEntity> en=(EntityWrapper<MisAutoReplyEntity>) new EntityWrapper<MisAutoReplyEntity>()
				.eq(!StringUtils.isEmpty((String) params.get("type")), "type", (String) params.get("type"))
				.like(!StringUtils.isEmpty((String) params.get("name")), "name", (String) params.get("name"))
				.orderBy("create_time", false);
		Page<MisAutoReplyEntity> page = this.selectPage(new Query<MisAutoReplyEntity>(params).getPage(),en
				);

		return new PageUtils(page);
	}

	@Override
	public int msgCount(String type) {
		return baseMapper.msgCount(type);
	}

}
