package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * @author DELL
 * @date 2020-12-29 20:47
 */
@EnableBinding(Source.class) //定义消息的推送
public class MessageProviderImpl implements MessageProvider {

    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial= UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("serial::"+serial);
        return null;
    }
}
