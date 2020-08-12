package com.lisen.springcloud.controller;

import com.lisen.springcloud.entities.Payment;
import com.lisen.springcloud.service.PaymentFeignService;
import com.lisen.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lisen
 * @create 2020/8/12 18:31
 */
@Slf4j
@RestController
@RequestMapping("/consumer/payment")
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout(){
        // openFeign 客户端一般默认等待一秒钟,单位毫秒, 可在yml中配置
        return paymentFeignService.paymentFeignTimeout();
    }
}
