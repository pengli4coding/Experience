package com.pl.helloRabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class MyProducer {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "382346819pl";
	private static final String EXCHANGE_NAME = "helloRabbitMQ.exchange";
	private static final String ROUTING_KEY = "helloRabbitMQ.routingKey";
	private static final String QUEUE_NAME = "helloRabbitMQ.queue";
	private static final String IP = "129.204.58.20";
	private static final int PORT = 5672;

	public static void main(String[] args) throws IOException, TimeoutException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(IP);
		factory.setPort(PORT);
		factory.setUsername(USERNAME);
		factory.setPassword(PASSWORD);
		// 创建连接
		Connection connection = factory.newConnection();
		// 创建信道
		Channel channel = connection.createChannel();
		// 创建一个类型为direct的、持久化的、非自动删除的交换器
		channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);
		// 创建一个持久化的、非排他的、非自动删除的队列
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		// 将交换器与队列通过路由键绑定
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
		// 发送持久化消息
		for (int i = 1; i <= 500; i++) {
			String message = "hello rabbitmq" + i;
			channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
		}
		// 关闭资源
		channel.close();
		connection.close();
	}

}
