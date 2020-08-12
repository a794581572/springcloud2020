package com.lisen.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author lisen
 * @create 2020/8/12 4:51
 */
@RestController
@RequestMapping("/consumer/payment")
public class OrderController {
    // 单节点可以使用ip:port
    public static final String INVOKE_URL = "http://consul-provider-payment";
//    public static final String INVOKE_URL = "http://服务器中服务实例名称";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consul")
    public String paymentInfo(){
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        return result;
    }
    @GetMapping("/index")
    public String index(){
        return "孟彪";
    }
}
