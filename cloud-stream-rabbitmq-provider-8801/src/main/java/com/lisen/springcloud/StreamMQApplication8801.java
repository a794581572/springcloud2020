package com.lisen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lisen
 * @create 2020/8/19 2:36
 */
@SpringBootApplication
@EnableEurekaClient
public class StreamMQApplication8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQApplication8801.class,args);
    }
}
