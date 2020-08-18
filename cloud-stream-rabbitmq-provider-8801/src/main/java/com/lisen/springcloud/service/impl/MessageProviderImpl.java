package com.lisen.springcloud.service.impl;
/**
 * @author lisen
 * @create 2020/8/19 2:40
 */
import com.lisen.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author lisen
 * @create 2020/8/19 2:40
 */
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {
    // 发信通道
    @Resource
    private MessageChannel output;
    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        // 发信通道发送信息(内容serial) 收信通道使用getPayload()接收信息
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("********serial: " + serial);
        return serial;
    }
}
