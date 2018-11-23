package io.credittag.mis.modules.ctc.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述：
 *
 * @author shawn
 * @date 2018/7/3
 */

@Component
public class Sender {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void send(JSONObject msg) {
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		this.rabbitTemplate.convertAndSend("merchantTrx-exchange", "merchantTrx.queue.key", msg);
	}

	 
}
