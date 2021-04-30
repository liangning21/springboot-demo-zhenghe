package cn.lihuan.demo.workQueues;

import cn.lihuan.demo.utils.MqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 李欢
 * @Date: 2021/01/12/11:37
 * @Description:
 */
//任务型模式的队列 生产者
public class WorkProducerService {

    @Test
    public void sendMessages() throws IOException {
        Connection mqConnection = MqUtils.getMqConnection();

        Channel channel = mqConnection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("","work",null,(i+"work message").getBytes());
        }
        MqUtils.MqClose(channel,mqConnection);
    }
}
