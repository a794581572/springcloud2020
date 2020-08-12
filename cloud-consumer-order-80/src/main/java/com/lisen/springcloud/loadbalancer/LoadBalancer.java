package com.lisen.springcloud.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author lisen
 * @create 2020/8/12 10:03
 */
public interface LoadBalancer {
    /**
     * 负载均衡算法, 方式为轮询
     * rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标, 每次服务器重启后rest接口计数从1开始
     * @param serviceInstances
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
