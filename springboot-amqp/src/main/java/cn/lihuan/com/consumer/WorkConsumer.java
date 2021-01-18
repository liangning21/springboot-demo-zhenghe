package cn.lihuan.com.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/18 14:09
 * @Created by Dell
 */
//work的消费者
@Component
@Slf4j
public class WorkConsumer {


    @RabbitListener(queuesToDeclare = @Queue("springboot-work"))
    public void receivel(String message){
        log.info("一工作模式消费的消：{}",message);
    }

    @RabbitListener(queuesToDeclare = @Queue("springboot-work"))
    public void receive2(String message){
        log.info("二工作模式消费的消：{}",message);
    }

}
