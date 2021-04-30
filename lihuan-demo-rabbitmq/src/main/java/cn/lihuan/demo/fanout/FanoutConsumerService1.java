package cn.lihuan.demo.fanout;

import cn.lihuan.demo.utils.MqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/14 17:25
 * @Created by Dell
 */
//广播模式的消费者1
public class FanoutConsumerService1 {
    public static void main(String[] args) throws IOException {
        Connection mqConnection = MqUtils.getMqConnection();
        Channel channel = mqConnection.createChannel();
        //绑定交换机
        channel.exchangeDeclare("logs","fanout");
        //获取一个队列临时的
        String queueName = channel.queueDeclare().getQueue();
        System.out.println("queueName = " + queueName);
        //交换机和队列绑定
        channel.queueBind(queueName,"logs","");
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1消费的消息是：》》》》"+new String(body));
            }
        });
    }
}
