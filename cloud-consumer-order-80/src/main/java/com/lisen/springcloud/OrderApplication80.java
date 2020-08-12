package com.lisen.springcloud;

import com.lisen.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author lisen
 * @create 2020/8/6 2:04
 */
@SpringBootApplication
@EnableEurekaClient
// ribbon注解, 在这个微服务上使用我们自定义的负载均衡方式
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication80.class, args);
    }
}
