package com.lisen.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author lisen
 * @create 2020/8/17 3:56
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfoOk(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfoOk ,o(╥﹏╥)o";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "-----PaymentFallbackService fall back-paymentInfoTimeOut ,o(╥﹏╥)o";
    }
}
