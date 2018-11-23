package io.credittag.mis.modules.ctc.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;

import io.credittag.mis.modules.ctc.dao.MisCwTkDao;
import io.credittag.mis.modules.ctc.entity.MisCwTkEntity;
import io.credittag.mis.modules.ctc.service.MisCwTkService;


@Service("misCwTkService")
public class MisCwTkServiceImpl extends ServiceImpl<MisCwTkDao, MisCwTkEntity> implements MisCwTkService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MisCwTkEntity> page = this.selectPage(
                new Query<MisCwTkEntity>(params).getPage(),
				new EntityWrapper<MisCwTkEntity>().eq(StringUtils.isNotBlank((String) params.get("telNo")), "telNo",
						params.get("telNo")).orderBy("create_time", false));
        
        

		return new PageUtils(page);
	}

}
