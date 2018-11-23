package io.credittag.mis.modules.ctc.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;

import io.credittag.mis.modules.ctc.dao.StopCustomerDao;
import io.credittag.mis.modules.ctc.entity.StopCustomerEntity;
import io.credittag.mis.modules.ctc.service.StopCustomerService;


@Service("stopCustomerService")
public class StopCustomerServiceImpl extends ServiceImpl<StopCustomerDao, StopCustomerEntity> implements StopCustomerService {

    @Autowired
    private StopCustomerDao stopCustomerDao;

    @DataSource(name = DataSourceNames.SECOND)
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        Page<StopCustomerEntity> page = this.selectPage(
//                new Query<StopCustomerEntity>(params).getPage(),
//                new EntityWrapper<StopCustomerEntity>()
//        );

        Wrapper<StopCustomerEntity> wrapper = new EntityWrapper<StopCustomerEntity>()
                .eq(StringUtils.isNotBlank((String) params.get("phone")), "username", params.get("phone"));
        Page<StopCustomerEntity> page = this.selectPage(
                new Query<StopCustomerEntity>(params).getPage(), wrapper);

        page.setRecords(stopCustomerDao.withdraworderlist(page, wrapper));

        return new PageUtils(page);
    }

    @DataSource(name = DataSourceNames.SECOND)
    @Override
    public boolean updateStatusById(String id, int type) {
        Wrapper<StopCustomerEntity> wrapper = new EntityWrapper<StopCustomerEntity>()
                .eq("id", id);
        if (type == 0) {
            int freeze = stopCustomerDao.freeze(wrapper);
            if (freeze != 0)
                return true;
        } else {
            int thaw = stopCustomerDao.thaw(wrapper);
            if (thaw != 0) {
                return true;
            }
        }
        return false;
    }

}
