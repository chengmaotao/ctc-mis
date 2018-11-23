package io.credittag.mis.modules.ctc.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import com.alibaba.fastjson.JSONObject;

@Configuration
//@RabbitListener(queues = "merchantTrx.queue")
public class Listener {

	private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);

	@Bean
	public Queue fooQueue() {
		return new Queue("merchantTrx.queue");
	}

	@RabbitHandler
	public void process(@Payload JSONObject foo) {
		LOGGER.info("rabbitmq Listener: " + foo.toJSONString());
	}
}
