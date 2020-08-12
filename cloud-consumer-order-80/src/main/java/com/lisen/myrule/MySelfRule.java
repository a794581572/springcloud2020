package com.lisen.myrule;

/**
 * @author lisen
 * @create 2020/8/12 9:04
 */

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡方式
 * 和springcloud分开放, 为了不让@ComponentScan注解扫到
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        // 自定义为随机方式, 默认的是roundRule 轮询
        return new RandomRule();
    }
}
