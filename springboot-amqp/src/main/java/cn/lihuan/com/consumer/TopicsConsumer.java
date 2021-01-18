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
 * @Date 2021/1/18 14:46
 * @Created by Dell
 */
@Component
@Slf4j
public class TopicsConsumer {

    @RabbitListener(bindings={
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "springboot-topics",type = "topic"),
                    key = {"word.*","word.#"}
            )
    })
    public void receivel(String message){
        log.info("这是订阅模式消费的消息：{}",message);
        log.info("路由的格式为：word.*,word.#");
    }
}
