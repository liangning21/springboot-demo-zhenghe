package cn.lihuan.demo.routing.direct;

import cn.lihuan.demo.utils.MqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/15 9:38
 * @Created by Dell
 */
//这是交换机，直连模式的消费者
public class DirectConsumerServiceTwo {
    public static void main(String[] args) throws IOException {
        Connection mqConnection = MqUtils.getMqConnection();
        Channel channel = mqConnection.createChannel();
        String exchange = "losg_diect"; //交换机名字
        //绑定交换机
        channel.exchangeDeclare(exchange,"direct");
        String queue = channel.queueDeclare().getQueue();  // 获取临时的队列
        String routingKey  ="error";  //路由
        //队列和交换机 ,路由绑定
        channel.queueBind(queue,exchange,routingKey);
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到的信息是》》》》"+new String(body));
            }
        });
    }
}
