package com.lisen.springcloud.service.impl;

import com.lisen.springcloud.dao.PaymentDao;
import com.lisen.springcloud.entities.Payment;
import com.lisen.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lisen
 * @create 2020/8/5 18:40
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;
    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
