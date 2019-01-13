package com.pl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 消息发送者 - Producer。
 * Producer对象，必须交由Spring容器管理，所以必须加上@Component注解
 * 使用SpringBoot提供的AMQP启动器，来访问rabbitmq的时候，都是通过AmqpTemplate来实现的。
 */
@Component
public class MyProducer {
	
	// AMQP启动器会自动在spring容器启动的时候初始化一个AmqpTemplate对象，我们只需要注入进来就可以了
	@Autowired
	private AmqpTemplate rabbitAmqpTemplate;
	
	// 将配置文件中的交换器名称注入进来
	@Value("${mq.config.exchange}")
	private String exchange;
	
	// 将配置文件中的路由键名称注入进来
	@Value("${mq.config.queue.info.routing.key}")
	private String routingkey;
	
	// 发送消息
	public void send(LogMessage msg) {
		/**
		 * convertAndSend - 转换并发送消息的template方法。
		 * 是将传入的普通java对象，转换为rabbitmq中需要的message类型对象，并发送消息到rabbitmq中。
		 * 参数一：交换器名称。 类型是String
		 * 参数二：路由键。 类型是String
		 * 参数三：消息，是要发送的消息内容对象。类型是Object
		 */
		this.rabbitAmqpTemplate.convertAndSend(this.exchange, this.routingkey,msg);
	}
	
}
