package com.ysb.config;

import com.ysb.service.rabbitmq.IMessageDealService;
import com.ysb.service.rabbitmq.RabbitConsumer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfiguration {

    @Autowired
    private IMessageDealService messageDealService;

    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;

    public static final String queueName = "websocket-0";

    private int concurrentConsumers = 5;// 并发consumer数量
    private int maxConcurrentConsumers = 10;// // 最大consumer数量
    private int prefetchCount = 10;// 设置每次发送到comsumer多少个消息
    private int txSize = 10;// For best results it should be less than or equal to prefetch count

    /**
     * 初始化queue,如果已经初始化了,不会覆盖有的queue
     * @return
     */
    @Bean
    Queue queue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-max-priority", Integer.valueOf(5));// 设置优先级
        Queue queue = new Queue(queueName, true, false, false, arguments);
        return queue;
    }


    /**
     * rabbitMQ监听器(接收消息并处理消息)
     * @param connectionFactory
     * @return
     */
    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(new RabbitConsumer(messageDealService));
        container.setConcurrentConsumers(concurrentConsumers);// 并发consumer数量
        container.setMaxConcurrentConsumers(maxConcurrentConsumers);// 最大consumer数量

        container.setPrefetchCount(prefetchCount);// 设置每次发送到comsumer多少个消息
        container.setTxSize(txSize);

        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);// 设置ack手动
        return container;
    }

    /**
     * 返回队列连接
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, 5672);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setChannelCacheSize(5);
        return connectionFactory;
    }


    /**
     * rabbitMQ producer
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(new JsonMessageConverter());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }


    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

}
