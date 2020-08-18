package com.lisen.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lisen
 * @create 2020/8/19 2:55
 */
@RestController
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {
    @Value("${server.port}")
    private String severPort;

    /**
     * 监听收信, 使用getPayload()接受生产者发送的消息
     * @param message
     */
    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("消费者1号，----------->接收到的消息："+message.getPayload()+"\t port"+severPort);
    }
}
