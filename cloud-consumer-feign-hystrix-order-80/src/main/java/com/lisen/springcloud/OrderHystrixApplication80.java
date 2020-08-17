package com.lisen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lisen
 * @create 2020/8/17 3:55
 */
@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
public class OrderHystrixApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixApplication80.class, args);
    }
}
