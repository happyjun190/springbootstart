package com.ysb.service.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

public class RabbitConsumer implements ChannelAwareMessageListener {

    private Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);
    public static ObjectMapper objectMapper = new ObjectMapper();
    private IMessageDealService messageDealService;

    /**
     * RabbitConsumer构造器
     * @param messageDealService
     */
    public RabbitConsumer(IMessageDealService messageDealService) {
        this.messageDealService = messageDealService;
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        logger.info("Consumer JSON massage received: " + new String(message.getBody()));


        //告诉queue已经读取到了,queue删除该条消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);// 设置ack
    }
}
