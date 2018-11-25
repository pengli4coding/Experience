package com.pl.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pl.LogMessage;
import com.pl.MyProducer;

/**
 * Direct交换器
 * Producer测试。
 * 注意：
 * 在rabbitmq中，consumer都是listener监听模式消费消息的。
 * 一般来说，在开发的时候，都是先启动consumer，确定有什么exchange、queue、routing-key。
 * 然后再启动producer发送消息。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {

	@Autowired
	private MyProducer producer;

	@Test
	public void sendMessage() throws InterruptedException {
		long id = 1L;
		while (true) {
			Thread.sleep(1000);
			LogMessage message = new LogMessage(id, "test-log", "info", "订单服务", new Date(), id);
			this.producer.send(message);
			id++;
			System.out.println("【发送消息到mq成功】消息内容为："+message);
		}
	}
}
