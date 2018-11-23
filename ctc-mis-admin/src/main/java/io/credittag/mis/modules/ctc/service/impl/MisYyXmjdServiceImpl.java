package io.credittag.mis.modules.ctc.service.impl;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.dao.MisYyXmjdDao;
import io.credittag.mis.modules.ctc.entity.MisYyXmjdEntity;
import io.credittag.mis.modules.ctc.service.MisYyXmjdService;


@Service("misYyXmjdService")
public class MisYyXmjdServiceImpl extends ServiceImpl<MisYyXmjdDao, MisYyXmjdEntity> implements MisYyXmjdService {
	
	@Resource
	private MisYyXmjdDao misYyXmjdDao;

    @Override
    @DataSource(name = DataSourceNames.SECOND)
    public PageUtils queryPage(Map<String, Object> params) {
    	
    	String beginTime= (String) params.get("beginTime");
    	String endTime = (String) params.get("endTime");
    	String langu = (String) params.get("langu");
    	String title = (String) params.get("title");
    	
    	
        Page<MisYyXmjdEntity> page = new Query<MisYyXmjdEntity>(params).getPage();
        EntityWrapper<MisYyXmjdEntity> entityWrapper = new EntityWrapper<MisYyXmjdEntity>();
        //添加搜索条件
        entityWrapper.isWhere(true);
        
        if(endTime!=null&&!"24:00:00".equals(endTime.trim())) {
        	entityWrapper.le("create_time", endTime);
        	
        }
        if(beginTime!=null&&!"00:00:00".equals(endTime.trim())) {
        	entityWrapper.gt("create_time", beginTime);
        }
        if(langu!=null&&!langu.trim().equals("")) {
        	entityWrapper.eq("language", langu);
        }
        if(title!=null&&!title.trim().equals("")) {
        	entityWrapper.like("title", title);
        }
        
        entityWrapper = (EntityWrapper<MisYyXmjdEntity>) SqlHelper.fillWrapper(page,  entityWrapper);
        List<MisYyXmjdEntity> list = misYyXmjdDao.queryPage(page, entityWrapper);
        page.setRecords( misYyXmjdDao.queryPage(page, entityWrapper));
        return new PageUtils(page);
    }

	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public boolean deleteBatchIdsByOverride(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return this.deleteBatchIds(idList);
	}

	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public boolean updateAllColumnByIdByOverride(MisYyXmjdEntity misYyXmjd) {
//		// TODO Auto-generated method stub
		misYyXmjd.setVoCreateTime(null);
		misYyXmjd.setVoUpdateTime(null);
		misYyXmjd.setVoTime(null);
		misYyXmjd.setUpdateTime(new Date());
		try {
			misYyXmjdDao.updateById(misYyXmjd);
			 //misYyXmjdDao.updateByIdOverride(misYyXmjd);
			 return true;
		}catch(Exception e) {
			return false;
		}
		
		
	}

	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public boolean insertByOverride(MisYyXmjdEntity misYyXmjd) {
		// TODO Auto-generated method stub
		
		return  this.insert(misYyXmjd);
		
	}
	
	
	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public MisYyXmjdEntity selectById(Serializable id) {
		// TODO Auto-generated method stub
		return super.selectById(id);
	}

	@Override
	@DataSource(name = DataSourceNames.SECOND)
	public MisYyXmjdEntity findById(Long id) {
		// TODO Auto-generated method stub
		
		
		return misYyXmjdDao.findById(id);
		
		
	}

}
