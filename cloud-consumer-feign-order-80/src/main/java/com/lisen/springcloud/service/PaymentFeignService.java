package com.lisen.springcloud.service;

import com.lisen.springcloud.entities.Payment;
import com.lisen.springcloud.vo.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lisen
 * @create 2020/8/12 18:25
 */
@Component
// 这里声明是对CLOUD-PAYMENT-SERVICE服务的负载均衡调用, 7001,7002监控的8001,8002微服务
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();
}
