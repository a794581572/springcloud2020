package com.lisen.springcloud.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author lisen
 * @description 自定义过滤器
 * @create 2020/8/18 0:01
 */
@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    /**
     * <pre>
     *   重写过滤器的规则
     *   使用exchange.getRequest().getQueryParams().getFirst("uname"); 拿到uname,
     *   要求每次访问都要带有uname,  例如: 127.0.0.1:9527/payment/lb?uname=23
     *      ①如果请求带有uname为空或者不带有uname, 不给访问, 直接给错误返回.
     *      ②如果请求带有uname且不为空, 走下面的过滤器和路由
     * </pre>
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("***********come in MyLogGateWayFilter: " + new Date());

        String uname = exchange.getRequest().getQueryParams().getFirst("uname");

        if (uname == null){
            log.info("**********用户名为null, 非法用户, o(╥﹏╥)o");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
