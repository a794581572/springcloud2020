package com.lisen.springcloud.config;

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
//    @LoadBalanced
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
}
