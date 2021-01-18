package cn.lihuan.com.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/18 14:21
 * @Created by Dell
 */
//广播模式的
@Component
@Slf4j
public class FanoutConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(   //绑定一个队列
                    value = @Queue,  //不写队列名字，代表默认创建一个
                    exchange = @Exchange(value = "springboot-fanout",type = "fanout")  //绑定交换机
            )
    })
    public void receivel(String message){
        log.info("一广播模式消费的消息是：{}",message);
    }


    @RabbitListener(bindings = {
            @QueueBinding(   //绑定一个队列
                    value = @Queue,  //不写队列名字，代表默认创建一个
                    exchange = @Exchange(value = "springboot-fanout",type = "fanout")  //绑定交换机
            )
    })
    public void receive2(String message){
        log.info("二广播模式消费的消息是：{}",message);
    }

}
