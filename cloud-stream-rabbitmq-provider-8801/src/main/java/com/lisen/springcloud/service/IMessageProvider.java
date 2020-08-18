package com.lisen.springcloud.service;

/**
 * @author lisen
 * @description
 * <pre>
 *     接口, 消息提供方发送消息
 *     (通过stream发送消息到中间件, 这里我们并不需要关注中间件是哪一种)
 * </pre>
 * @create 2020/8/19 2:37
 */
public interface IMessageProvider {
    /**
     * 消息生产者发送消息
     * @return
     */
    String send();
}
