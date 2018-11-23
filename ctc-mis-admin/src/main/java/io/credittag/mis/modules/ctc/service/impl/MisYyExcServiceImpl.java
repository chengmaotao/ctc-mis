package io.credittag.mis.modules.ctc.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.credittag.mis.common.utils.DateUtils;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.entity.MisYyDhEntity;
import io.credittag.mis.modules.ctc.service.MisYyDhService;
import io.credittag.mis.modules.ctc.utils.DateUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;

import io.credittag.mis.modules.ctc.dao.MisYyExcDao;
import io.credittag.mis.modules.ctc.entity.MisYyExcEntity;
import io.credittag.mis.modules.ctc.service.MisYyExcService;
import org.springframework.transaction.annotation.Transactional;


@Service("misYyExcService")
public class MisYyExcServiceImpl extends ServiceImpl<MisYyExcDao, MisYyExcEntity> implements MisYyExcService {

    @Autowired
    private MisYyDhService misYyDhService;
    @Autowired
    private MisYyExcService misYyExcService;
    @Autowired
    private MisYyExcDao misYyExcDao;

    @DataSource(name = DataSourceNames.SECOND)
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MisYyExcEntity> wrapper = new EntityWrapper<MisYyExcEntity>()
                .eq(StringUtils.isNotBlank((String) params.get("phone")), "phone", params.get("phone"))
                .eq(StringUtils.isNotBlank((String) params.get("mark")), "mark", params.get("mark"))
                .orderBy("create_time", false);
        Page<MisYyExcEntity> page = this.selectPage(
                new Query<MisYyExcEntity>(params).getPage(), wrapper);

        page.setRecords(misYyExcDao.withdraworderlist(page,wrapper));

        return new PageUtils(page);
    }

    @DataSource(name = DataSourceNames.SECOND)
    @Override
    public boolean insert(MisYyExcEntity entity) {
        List<MisYyExcEntity> list = misYyExcDao.queryRepeat(entity);
        if(list.size()>0){
            return false;
        }
        super.insert(entity);
        MisYyDhEntity misYyDhEntity = new MisYyDhEntity();
        misYyDhEntity.setDhcode(entity.getDhcode());
        misYyDhEntity.setCount(entity.getCount());
        misYyDhEntity.setType(entity.getType());
        misYyDhEntity.setExpireTime(entity.getExpireTime());
        misYyDhEntity.setRemark(entity.getMark());
        misYyDhEntity.setCreateTime(entity.getCreateTime());
        misYyDhEntity.setUpdateTime(entity.getUpdateTime());
        misYyDhEntity.setStatus(0);
        misYyDhEntity.setPid(entity.getPid());
        misYyDhService.insert(misYyDhEntity);
        return true;
    }

}
