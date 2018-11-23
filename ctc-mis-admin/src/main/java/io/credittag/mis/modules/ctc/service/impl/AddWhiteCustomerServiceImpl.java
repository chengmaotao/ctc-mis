package io.credittag.mis.modules.ctc.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;

import io.credittag.mis.modules.ctc.dao.AddWhiteCustomerDao;
import io.credittag.mis.modules.ctc.entity.AddWhiteCustomerEntity;
import io.credittag.mis.modules.ctc.service.AddWhiteCustomerService;


@Service("addWhiteCustomerService")
public class AddWhiteCustomerServiceImpl extends ServiceImpl<AddWhiteCustomerDao, AddWhiteCustomerEntity> implements AddWhiteCustomerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AddWhiteCustomerEntity> page = this.selectPage(
                new Query<AddWhiteCustomerEntity>(params).getPage(),
                new EntityWrapper<AddWhiteCustomerEntity>()
        );

        return new PageUtils(page);
    }

}
