package io.credittag.mis.modules.ctc.dao;

import io.credittag.mis.modules.ctc.entity.MisAutoReplyEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
 * 
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-10 11:56:46
 */
public interface MisAutoReplyDao extends BaseMapper<MisAutoReplyEntity> {
	int msgCount(String type);
}
