package cn.lihuan.com;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AmqpApplication.class)
class AmqpApplicationTests {

	@Autowired
	private RabbitTemplate rabbitTemplate;  //默认会自动注入的

	@Test //订阅模式
	public void topics(){
		rabbitTemplate.convertAndSend("springboot-topics","word.save","这是订阅模式消费的消息");
	}


	@Test  //路由模式
	public void routing(){
		rabbitTemplate.convertAndSend("springboot-routing","user","这是路由模式发送的消息");
	}

	@Test  //广播模式
	public void fanout(){
		rabbitTemplate.convertAndSend("springboot-fanout","","这是广播模式发送的消息");
	}

	@Test // work模型
	public void work(){
		for (int i = 0; i < 10; i++) {
			rabbitTemplate.convertAndSend("springboot-work","work的消息发送"+i);
		}
	}

	//helloWord
	@Test
	public void test01() {
		rabbitTemplate.convertAndSend("springboot-hello","hello");
	}

}
