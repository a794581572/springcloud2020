package com.lisen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lisen
 * @create 2020/8/7 20:29
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderZkApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderZkApplication80.class, args);
    }
}
