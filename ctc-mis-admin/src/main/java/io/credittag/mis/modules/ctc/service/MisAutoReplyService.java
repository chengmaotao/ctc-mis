package io.credittag.mis.modules.ctc.service;

import com.baomidou.mybatisplus.service.IService;
import io.credittag.mis.common.utils.PageUtils;
import io.credittag.mis.modules.ctc.entity.MisAutoReplyEntity;

import java.util.Map;

/**
 * 
 *
 * @author gen
 * @email gen1@ctc
 * @date 2018-07-10 11:56:46
 */
public interface MisAutoReplyService extends IService<MisAutoReplyEntity> {

	PageUtils queryPage(Map<String, Object> params);

	int msgCount(String type);
}
