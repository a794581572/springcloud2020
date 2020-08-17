package com.lisen.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lisen
 * @create 2020/8/17 2:09
 */
@Service
public class PaymentService {
    static AtomicInteger count = new AtomicInteger(0);

    /**
     * 接口正常的
     *
     * @param id
     * @return
     */
    public String paymentInfoOK(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "\t" + "paymentInfoOK(),id:" + id + "\t" + " \"O(∩_∩) 成功返回哈哈哈";
    }

    /**
     * <pre>
     *     接口报错的, 服务降级场景,
     *     fallbackMethod=paymentInfoTimeOutHandle
     *     如果异常,自动降级到paymentInfoTimeOutHandle方法
     *     commandProperties 走服务降级的条件, 这里我们设置的3s超时, 这个方法要睡5s 必然报错走服务降级.
     * </pre>
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfoTimeOutHandle", commandProperties =
            {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    public String paymentInfoTimeOut(Integer id) {

        System.out.println("第" + count.getAndIncrement() + "请求");
        Integer timeOutNumber = 5;
        // int age  = 10/0;
        try {
            TimeUnit.SECONDS.sleep(timeOutNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池:  " + Thread.currentThread().getName() + " id:  " + id + "\t"
                + "O(∩_∩)O哈哈~" + "  耗时(秒): " + timeOutNumber;
    }

    public String paymentInfoTimeOutHandle(Integer id) {
        return "服务繁忙,,," + " id:  " + id + "\t" + "o(╥﹏╥)o";
    }


    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker( Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
