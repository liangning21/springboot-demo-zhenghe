package cn.lihuan.demo.routing.direct;

import cn.lihuan.demo.utils.MqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Classname lihuan
 * @Date 2021/1/15 9:16
 */
// 这是直连方式，通过交换机 生产者
public class DirectProducerService {
    public static void main(String[] args) throws IOException {

        Connection mqConnection = MqUtils.getMqConnection();
        Channel channel = mqConnection.createChannel();
        //绑定交换机，第一个参数交换机的名字 第二个参数交换机的类型
        channel.exchangeDeclare("losg_diect","direct");
        String routingKey  ="info";
//        String routingKey  ="error";
        channel.basicPublish("losg_diect",routingKey,null,("这是direct{"+routingKey+"}").getBytes());
        MqUtils.MqClose(channel,mqConnection);
    }
}
