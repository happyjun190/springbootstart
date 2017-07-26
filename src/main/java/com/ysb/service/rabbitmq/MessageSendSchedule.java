package com.ysb.service.rabbitmq;

import com.ysb.config.RabbitMQConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MessageSendSchedule {
    private  final static Logger logger = LoggerFactory.getLogger(MessageSendSchedule.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private int i = 1;

    @Scheduled(fixedDelay = 1 * 1000)
    public void scheduleSendMessageToRabbit() {
        try {
            rabbitTemplate.convertAndSend(RabbitMQConfiguration.queueName, (Object) ("Message " + i++), new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setPriority(new Integer(1));
                    return message;
                }
            });
        } catch(Exception e){
            logger.error("发送短信到rabbitmq失败！",e);
        } finally {
            logger.error("消息发送完毕,消息内容为:{}", "Message " + i);
        }
    }

}
