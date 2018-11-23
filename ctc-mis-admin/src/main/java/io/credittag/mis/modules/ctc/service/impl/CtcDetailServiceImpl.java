package io.credittag.mis.modules.ctc.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;

import io.credittag.mis.modules.ctc.dao.CtcDetailDao;
import io.credittag.mis.modules.ctc.entity.CtcDetailEntity;
import io.credittag.mis.modules.ctc.service.CtcDetailService;


@Service("ctcDetailService")
public class CtcDetailServiceImpl extends ServiceImpl<CtcDetailDao, CtcDetailEntity> implements CtcDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CtcDetailEntity> page = this.selectPage(
                new Query<CtcDetailEntity>(params).getPage(),
                new EntityWrapper<CtcDetailEntity>()
        );

        return new PageUtils(page);
    }

}
