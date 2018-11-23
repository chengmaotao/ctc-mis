package io.credittag.mis.modules.ctc.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;

import io.credittag.mis.modules.ctc.dao.MisWxMenuDao;
import io.credittag.mis.modules.ctc.entity.MisWxMenuEntity;
import io.credittag.mis.modules.ctc.service.MisWxMenuService;


@Service("misWxMenuService")
public class MisWxMenuServiceImpl extends ServiceImpl<MisWxMenuDao, MisWxMenuEntity> implements MisWxMenuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MisWxMenuEntity> page = this.selectPage(
                new Query<MisWxMenuEntity>(params).getPage(),
                new EntityWrapper<MisWxMenuEntity>()
        );

        return new PageUtils(page);
    }

}
