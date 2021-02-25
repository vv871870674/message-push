package com.official.messagepush.listener;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.official.messagepush.service.SendMessageService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author: cww
 * @date: 2021/2/24 10:37
 */

@Component
@RabbitListener(queues = "topic.message.send")
public class MessageSendListener  {

    @Resource
    private SendMessageService sendMessageService;
    private final static Logger LOGGER = LoggerFactory.getLogger(MessageSendListener.class);

    @RabbitHandler
    public void process(Message message, String messageJson, Channel channel) throws IOException {
        LOGGER.info("MessageSendListener消费者收到消息:{}",messageJson);
        long deliveryTag=message.getMessageProperties().getDeliveryTag();
        //将JSON格式数据转换为Map对象
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructMapType(Map.class, String.class, String.class);
        Map<String, String> messageMap = mapper.readValue(message.getBody(),javaType);
        if (sendMessageService.send(messageMap)){
            //签收
            channel.basicAck(deliveryTag,false);
        }
    }

}
