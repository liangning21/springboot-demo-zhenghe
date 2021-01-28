package cn.lihuan.com.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/19 11:34
 * @Created by Dell
 */
@Component
@Configuration
public class RabbitMqConfig {
    @Resource
    private RabbitAdmin rabbitAdmin;

    @Bean
    public Queue queue001() {
        return new Queue("queue001", true); //队列持久
    }

    @Bean
    public TopicExchange topicExchangeA(){
        return new TopicExchange("topicA",true,false);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue001()).to(topicExchangeA()).with("a");
    }
}
