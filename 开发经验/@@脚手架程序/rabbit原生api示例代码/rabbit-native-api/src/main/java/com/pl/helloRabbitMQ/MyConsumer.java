package com.pl.helloRabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MyConsumer {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "382346819pl";
	private static final String QUEUE_NAME = "helloRabbitMQ.queue";
	private static final String IP = "129.204.58.20";
	private static final int PORT = 5672;

	public static void main(String[] args) throws IOException, TimeoutException {
		// 创建地址对象
		Address[] addresses = new Address[] { new Address(IP, PORT) };
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUsername(USERNAME);
		factory.setPassword(PASSWORD);
		// 创建连接
		Connection connection = factory.newConnection(addresses);
		// 创建信道
		final Channel channel = connection.createChannel();
		// 设置客户端最多接收未被ack的消息的个数
		channel.basicQos(64);
		// 设置回调函数
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("接收到mq的消息为：" + new String(body));
				try {
					TimeUnit.SECONDS.sleep(1);// 设置10秒之后发送ack
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				channel.basicAck(envelope.getDeliveryTag(), false);
			}

		};
		// 开始消费消息
		channel.basicConsume(QUEUE_NAME, consumer);// 消费者只关心队列，不关心交换器和路由键
	}

}
