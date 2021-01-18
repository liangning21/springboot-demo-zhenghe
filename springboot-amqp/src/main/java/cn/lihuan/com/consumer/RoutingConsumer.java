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
 * @Date 2021/1/18 14:35
 * @Created by Dell
 */
//这是路由模式的消费者
@Component
@Slf4j
public class RoutingConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "springboot-routing",type = "direct"),//自定义交换机和类型
                    key = {"user","info"}
            )
    })
    public void receivel(String message){
        log.info("这是路由模式的消费者：{}",message);
        log.info("user,info");
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "springboot-routing",type = "direct"),//自定义交换机和类型
                    key = {"user"}
            )
    })
    public void receive2(String message){
        log.info("这是路由模式的消费者：{}",message);
        log.info("user");
    }
}
