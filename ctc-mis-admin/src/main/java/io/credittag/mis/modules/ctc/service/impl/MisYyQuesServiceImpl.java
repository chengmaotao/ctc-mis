package io.credittag.mis.modules.ctc.service.impl;

import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;

import io.credittag.mis.modules.ctc.dao.MisYyQuesDao;
import io.credittag.mis.modules.ctc.entity.MisYyQuesEntity;
import io.credittag.mis.modules.ctc.service.MisYyQuesService;


@Service("misYyQuesService")
public class MisYyQuesServiceImpl extends ServiceImpl<MisYyQuesDao, MisYyQuesEntity> implements MisYyQuesService {

    @DataSource(name = DataSourceNames.SECOND)
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MisYyQuesEntity> page = this.selectPage(
                new Query<MisYyQuesEntity>(params).getPage(),
                new EntityWrapper<MisYyQuesEntity>()
        );

        return new PageUtils(page);
    }

    @DataSource(name = DataSourceNames.SECOND)
    @Override
    public MisYyQuesEntity selectById(Serializable id) {
        return super.selectById(id);
    }


    @DataSource(name = DataSourceNames.SECOND)
    @Override
    public boolean updateAllColumnById(MisYyQuesEntity entity) {
        return super.updateAllColumnById(entity);
    }

    @DataSource(name = DataSourceNames.SECOND)
    @Override
    public boolean insert(MisYyQuesEntity entity) {
        return super.insert(entity);
    }
}
