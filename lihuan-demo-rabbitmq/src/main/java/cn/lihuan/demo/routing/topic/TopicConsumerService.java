package cn.lihuan.demo.routing.topic;

import cn.lihuan.demo.utils.MqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/15 17:29
 * @Created by Dell
 */
// 订阅模式的 消费者1
public class TopicConsumerService {
    public static void main(String[] args) throws IOException {
        Connection mqConnection = MqUtils.getMqConnection();
        Channel channel = mqConnection.createChannel();
        //绑定交换机
        channel.exchangeDeclare("topic","topic");
        //得到一个临时队列
        String queue = channel.queueDeclare().getQueue();
         String routingKey ="user.*";
        //绑定交换机
        channel.queueBind(queue,"topic",routingKey);
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1 消费的信息>>>>"+new String(body) +">>>>路由是》》》"+routingKey);
            }
        });
    }
}
