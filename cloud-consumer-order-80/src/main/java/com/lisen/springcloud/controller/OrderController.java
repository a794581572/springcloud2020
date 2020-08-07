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
public class OrderController {
    // 单节点可以使用ip:port
//    public static final String PAYMENT_URL = "http://127.0.0.1:8001";
    // 集群环境就不写ip:port了, 因为同一个服务有很多个实例, 我们只关注服务名称
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    /**
     * 消费者使用RestTemplate.postForObject(url,参数,返回类型(这里是我们封装的json格式))方法请求生产者
     * @param payment
     * @return
     */
    @GetMapping("/create") //消费者统一发送get请求
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @RequestMapping("/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
