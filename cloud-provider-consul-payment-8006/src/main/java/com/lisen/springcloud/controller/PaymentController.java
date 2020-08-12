package com.lisen.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author lisen
 * @create 2020/8/12 4:19
 */
@RequestMapping("/payment")
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consul")
    public String paymentConsul(){
        return "SpringCloud with Consul: " + serverPort + UUID.randomUUID().toString();
    }
}
