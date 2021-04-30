package cn.lihuan.demo.routing.topic;

import cn.lihuan.demo.utils.MqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/15 17:24
 * @Created by Dell
 */
// 订阅模式的 生产者
public class TopicProduserService {
    public static void main(String[] args) throws IOException {
        Connection mqConnection = MqUtils.getMqConnection();
        Channel channel = mqConnection.createChannel();
        //绑定交换机
        channel.exchangeDeclare("topic","topic");
//        String routingKey ="user.save";
        String routingKey ="lihuan.user.save";
        //发送信息
        channel.basicPublish("topic",routingKey,null,("topice{"+routingKey+"}"+"发送消息").getBytes());
        MqUtils.MqClose(channel,mqConnection);
    }
}
