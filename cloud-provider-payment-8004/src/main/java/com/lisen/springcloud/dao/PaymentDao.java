package com.lisen.springcloud.dao;

import com.lisen.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author lisen
 * @create 2020/8/5 18:11
 */
@Mapper
public interface PaymentDao {
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
    Payment getPaymentById(@Param("id") Long id);
}
