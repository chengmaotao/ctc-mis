package io.credittag.mis.modules.ctc.service.impl;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.common.utils.Query;
import io.credittag.mis.common.utils.R;
import io.credittag.mis.datasources.DataSourceNames;
import io.credittag.mis.datasources.annotation.DataSource;
import io.credittag.mis.modules.ctc.dao.MisYyZlbdDao;
import io.credittag.mis.modules.ctc.entity.MisYyZlbdEntity;
import io.credittag.mis.modules.ctc.service.MisYyZlbdService;


@Service("misYyZlbdService")
public class MisYyZlbdServiceImpl extends ServiceImpl<MisYyZlbdDao, MisYyZlbdEntity> implements MisYyZlbdService {

	@Resource
	private MisYyZlbdDao misYyZlbdDao;
	
    @Override
    @DataSource(name = DataSourceNames.SECOND)
    public PageUtils queryPage(Map<String, Object> params) {
    	
    	String beginTime = (String) params.get("beginTime");
    	String endTime = (String) params.get("endTime");
    	String title = (String) params.get("title");
    	String source = (String) params.get("source");
    	String langu = (String) params.get("langu");
    	
    	 Page<MisYyZlbdEntity> page  = new Query<MisYyZlbdEntity>(params).getPage();
    	 EntityWrapper<MisYyZlbdEntity>  wrapper =  new EntityWrapper<MisYyZlbdEntity>();
    	// wrapper.isWhere(true);
//    	 if(beginTime!=null&&!"00:00:00".equals(beginTime.trim())) {
//    		 wrapper.gt("creat_time", beginTime);
//    		 
//    	 }
//    	 if(endTime!=null&&!"00:00:00".equals(endTime.trim())) {
//    		 wrapper.lt("end_time", endTime);
//    	 }
    	 
    	 wrapper.gt(beginTime!=null&&!"00:00:00".equals(beginTime.trim()), "create_time", beginTime);
    	 wrapper.le(endTime!=null&&!"24:00:00".equals(endTime.trim()), "create_time", endTime);
    	 wrapper.like(title!=null, "title",title );
    	 wrapper.like(source!=null, "channel", source);
    	 wrapper.eq(langu!=null, "language", langu);
    	 
    	 wrapper =  (EntityWrapper<MisYyZlbdEntity>) SqlHelper.fillWrapper(page,  wrapper);
         page.setRecords(baseMapper.selectPage(page, wrapper));
         
         
        return  new PageUtils(page);
    }

	@Override
	 @DataSource(name = DataSourceNames.SECOND)
	public boolean deleteByIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return this.deleteBatchIds(idList);
		
	}

	@Override
	 @DataSource(name = DataSourceNames.SECOND)
	public boolean insertSelf(MisYyZlbdEntity misYyZlbdEntity) {
		// TODO Auto-generated method stub
		return this.insert(misYyZlbdEntity);
		
	}

	@Override
	 @DataSource(name = DataSourceNames.SECOND)
	public boolean updateSelf(MisYyZlbdEntity misYyZlbdEntity) {
		// TODO Auto-generated method stub
		return this.updateAllColumnById(misYyZlbdEntity);
	}

	@Override
	 @DataSource(name = DataSourceNames.SECOND)
	public MisYyZlbdEntity findByIdself(Long id) {
		// TODO Auto-generated method stub
		return misYyZlbdDao.findByIdSelf(id);
	}

}
