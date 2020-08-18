package com.lisen.springcloud.controller;

import com.lisen.springcloud.entities.Payment;
import com.lisen.springcloud.loadbalancer.LoadBalancer;
import com.lisen.springcloud.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author lisen
 * @description 服务消费者
 * @create 2020/8/6 2:13
 */
@RestController
@Slf4j
@RequestMapping("/consumer/payment")
public class OrderController {
    // 单节点可以使用ip:port
//    public static final String PAYMENT_URL = "http://127.0.0.1:8001";
    // 集群环境就不写ip:port了, 因为同一个服务有很多个实例, 我们只关注服务名称
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    /**
     * RestTemplate 的调用方法有get 和post 2种, 每种有2种返回样式
     * 分别为: postForObject postForEntity
     *       getForObject getForEntity
     *  forObject 返回类型直接为泛型<T>(被调用方返回类型)
     *  forEntity 返回类型为ResponseEntity<T>, 然后通过ResponseEntity<T>.getBody()方法获得被调用方法返回类型, 当然也有getHead()等其他自己研究
     */
    @Resource
    private RestTemplate restTemplate;
    @Resource // 自定义负载均衡算法
    private LoadBalancer loadBalancer;
    @Resource
    private DiscoveryClient discoveryClient;
    @Value("${server.port}")
    private String serverPort;

    /**
     * 消费者使用RestTemplate.postForObject(url,参数,返回类型(这里是我们封装的json格式))方法请求生产者
     * @param payment
     * @return
     */
    @GetMapping("/create") //消费者统一发送get请求
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @RequestMapping("/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @RequestMapping("/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444,"操作失败");
        }
    }

    @GetMapping("/lb")
    public String getPaymentLB(){
        //查该微服务名的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");

        if(instances == null || instances.size()<=0){
            return null;
        }

        // 使用我们手写的负载均衡来监听实例
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        String url = uri + "/payment/lb";
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * 链路追踪
     * @return
     */
    @GetMapping("/zipkin")
    public String paymentZipkin(){
        return restTemplate.getForObject("http://127.0.0.1:8001"+"/payment/zipkin", String.class);
    }
}
