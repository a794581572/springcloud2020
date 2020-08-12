package com.lisen.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author lisen
 * @description RestTemplate通过配置文件配置bean, 然后在其他类就可以通过自动注入使用.
 *              相当于spring中xml配bean,只是这里使用的是@Bean注解
 * @create 2020/8/6 2:20
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    // @LoadBalanced是eureka默认的负载均衡机制, 集群环境调用服务名称通过这个自动分配(轮询方式,1:1分配,一次a实例, 一次b实例),
    // 不添加注解会报错: 服务名称; nested exception is java.net.UnknownHostException: 服务名称,他不知道用哪个服务实例
    @LoadBalanced
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}