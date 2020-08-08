package com.lisen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @author lisen
 * @create 2020/8/7 19:43
 */
@SpringBootApplication
@EnableDiscoveryClient //该注解用于向使用consul或zk作为注册中心时注册服务
public class PaymentApplication8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8004.class, args);
    }
}
