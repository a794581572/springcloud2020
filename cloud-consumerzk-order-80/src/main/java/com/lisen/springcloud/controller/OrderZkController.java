package com.lisen.springcloud.controller;

import com.lisen.springcloud.entities.Payment;
import com.lisen.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author lisen
 * @description 服务消费者
 * @create 2020/8/6 2:13
 */
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderZkController {
    // 单节点可以使用ip:port
    public static final String INVOKE_URL = "http://cloud-provider-payment";
//    public static final String INVOKE_URL = "http://服务器中服务实例名称";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/zk")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
        return result;
    }
}
