package cn.lihuan.demo.workQueues;

import cn.lihuan.demo.utils.MqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 李欢
 * @Date: 2021/01/12/11:37
 * @Description:
 */
//任务型模式的队列 自动确认消息机制  和能者多劳
public class WorkConsumerServiceAutoTwo {
    public static void main(String[] args) throws IOException {
        Connection mqConnection = MqUtils.getMqConnection();
        Channel channel = mqConnection.createChannel();
        channel.basicQos(1);  //设置每次消费一个
        channel.queueDeclare("work",true,false,false,null);
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费的消息》》》》"+new String(body).toString());
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
