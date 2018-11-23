package io.credittag.mis.modules.ctc.service;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;

import io.credittag.mis.common.utils.R;
import io.credittag.mis.modules.ctc.entity.AccountDetailEntity;
import io.credittag.mis.modules.ctc.entity.AnalystEntity;

public interface AnalystService extends IService<AnalystEntity> {

	R queryPage(Map<String, Object> params);

}
