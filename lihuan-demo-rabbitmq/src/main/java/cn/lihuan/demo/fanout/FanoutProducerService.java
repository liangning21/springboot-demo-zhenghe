package cn.lihuan.demo.fanout;

import cn.lihuan.demo.utils.MqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/14 17:04
 * @Created by Dell
 */
//广播模式的生成者
public class FanoutProducerService {
    public static void main(String[] args) throws IOException {
        Connection mqConnection = MqUtils.getMqConnection();
        Channel channel = mqConnection.createChannel();
        // 参数一为交换机的名字 参数二为交换机的模式
        channel.exchangeDeclare("logs", "fanout");
        channel.basicPublish("logs","",null,"广播模式的".getBytes());
        MqUtils.MqClose(channel,mqConnection);
    }
}
