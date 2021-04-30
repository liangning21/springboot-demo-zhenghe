package cn.lihuan.demo.consumer;

import cn.lihuan.demo.utils.MqUtils;
import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 李欢
 * @Date: 2021/01/11/16:11
 * @Description:
 */
//消费消息
public class ConsumerService {
    //消费消息
    public static void main(String[] args) throws IOException, TimeoutException {
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

        //消费消息
        //参数1：消费哪个消息队列
        //参数2：开始消息的自动确认机制
        //参数3：消费是的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            //匿名内部类
            @Override  //最后一个参数是从消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = " + new String(body));
            }
        });
//        channel.close();
//        connection.close();
    }

}
