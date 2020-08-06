package com.lisen.springcloud.service;

import com.lisen.springcloud.entities.Payment;

/**
 * @author lisen
 * @create 2020/8/5 18:39
 */
public interface PaymentService {
    /**
     * 创建信息
     * @param payment
     * @return
     */
    int create(Payment payment);

    /**
     * 通过id查询信息
     * @param id
     * @return
     */
    Payment getPaymentById(Long id);
}
