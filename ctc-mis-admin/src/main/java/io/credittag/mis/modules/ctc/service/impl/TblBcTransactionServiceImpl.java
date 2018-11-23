package io.credittag.mis.modules.ctc.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;

import io.credittag.mis.modules.ctc.dao.TblBcTransactionDao;
import io.credittag.mis.modules.ctc.entity.TblBcTransactionEntity;
import io.credittag.mis.modules.ctc.service.TblBcTransactionService;


@Service("tblBcTransactionService")
public class TblBcTransactionServiceImpl extends ServiceImpl<TblBcTransactionDao, TblBcTransactionEntity> implements TblBcTransactionService {

    @Autowired
    private TblBcTransactionDao tblBcTransactionDao;

    @DataSource(name = DataSourceNames.THREE)
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TblBcTransactionEntity> page = this.selectPage(
                new Query<TblBcTransactionEntity>(params).getPage(),
                new EntityWrapper<TblBcTransactionEntity>()
        );

        return new PageUtils(page);
    }

    @DataSource(name = DataSourceNames.THREE)
    @Override
    public TblBcTransactionEntity query(String trx_id) {
        Wrapper<TblBcTransactionEntity> wrapper = new EntityWrapper<TblBcTransactionEntity>()
                .eq("trx_id", trx_id);
        TblBcTransactionEntity entity = tblBcTransactionDao.withIdQuery(wrapper);

        return entity;
    }

}
