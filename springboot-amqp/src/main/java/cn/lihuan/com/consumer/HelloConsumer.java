package cn.lihuan.com.consumer;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/18 11:57
 * @Created by Dell
 */

@Component
@RabbitListener(queuesToDeclare = @Queue("springboot-hello"))
public class HelloConsumer {

    @RabbitHandler
    public void receivel(String message){
        System.out.println("消费的消息>>>>"+message);
    }
}
