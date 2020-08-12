package com.lisen.springcloud.loadbalancer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lisen
 * @create 2020/8/12 10:03
 */
@Slf4j
@Component
public class MyLoadBalancer implements LoadBalancer{

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 自旋锁
     * @return
     */
    private final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current +1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        log.info("*****第" + next +"次访问****");
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
