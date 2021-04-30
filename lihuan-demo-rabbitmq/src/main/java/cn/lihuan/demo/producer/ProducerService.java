package cn.lihuan.demo.producer;

import cn.lihuan.demo.utils.MqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 李欢
 * @Date: 2021/01/11/15:17
 * @Description:
 */
// 生产者
public class ProducerService {
    //生产消息的
    @Test
    public void sendMessage() throws IOException, TimeoutException {
//        //创建连接mq的连接工厂对象
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        //设置连接rabbitmq主机
//        connectionFactory.setHost("39.97.67.215");
//        //设置端口号
//        connectionFactory.setPort(5672);
//        //设置连接哪个虚拟机
//        connectionFactory.setVirtualHost("/ems");
//        //设置访问虚拟主机的用户名和密码
//        connectionFactory.setUsername("ems");
//        connectionFactory.setPassword("ems");

        //获取连接对象
//        Connection connection = connectionFactory.newConnection();
        Connection connection = MqUtils.getMqConnection();
        //获取连接中通道
        Channel channel = connection.createChannel();

        //通道绑定对应消息队列
        //参数1：队列名称，如果队列不存则自动创建
        //参数2: 用来定义队列特性是否要持久化
        //参数3：exclusive 是否独占队列 true 独占队列 false 不独占队列
        //参数4：是否在消费完成后自动删除队列 true自动删除 false不删除
        //参数5：额外附加参数
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1：交换机名称 参数2：队列名称 参数3：传递消息额外设置  参数4：消息的具体内容
        channel.basicPublish("","hello",null,"hello rabbitmq".getBytes());

        MqUtils.MqClose(channel,connection);
//        channel.close();
//        connection.close();
    }
}
