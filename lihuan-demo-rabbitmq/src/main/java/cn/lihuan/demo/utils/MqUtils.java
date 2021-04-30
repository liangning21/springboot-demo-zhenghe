package cn.lihuan.demo.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 李欢
 * @Date: 2021/01/11/16:31
 * @Description:
 */
public class MqUtils {
    //这样写，只创建一次，在类加载的时候，就创建好
    private static ConnectionFactory connectionFactory;
    static {
        connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("39.97.67.215");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接哪个虚拟机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("ems");
    }

    public static Connection getMqConnection() {
        try {
            return connectionFactory.newConnection();
            //重量级
//            //创建连接mq的连接工厂对象
//            ConnectionFactory connectionFactory = new ConnectionFactory();
//            //设置连接rabbitmq主机
//            connectionFactory.setHost("39.97.67.215");
//            //设置端口号
//            connectionFactory.setPort(5672);
//            //设置连接哪个虚拟机
//            connectionFactory.setVirtualHost("/ems");
//            //设置访问虚拟主机的用户名和密码
//            connectionFactory.setUsername("ems");
//            connectionFactory.setPassword("ems");
            //获取连接对象
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void MqClose(Channel channel,Connection connection){
        try {
            if(channel != null) channel.close();
            if(connection != null) connection.close();
        } catch (Exception ce){
            ce.printStackTrace();
        }
    }
}
