package com.lisen.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lisen
 * @description 服务降级接口
 * @create 2020/8/17 3:56
 */
@Component
// FeignClient中的fallback, 配置全局服务降级, 这个接口的所有方法 出现运行异常, 超时, 宕机 都会自动走服务降级,
// 走到PaymentFallbackService方法里,优先级最低, 外层(Service,Controller)的服务降级优先级高
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoOk(@PathVariable("id") Integer id);
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id);
}
