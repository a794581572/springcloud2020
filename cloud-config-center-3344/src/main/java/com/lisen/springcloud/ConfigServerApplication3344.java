package com.lisen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lisen
 * @create 2020/8/18 1:29
 */
@SpringBootApplication
@EnableConfigServer
//@EnableEurekaClient
public class ConfigServerApplication3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication3344.class, args);
    }
}
