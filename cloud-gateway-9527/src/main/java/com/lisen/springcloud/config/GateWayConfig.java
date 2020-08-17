package com.lisen.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lisen
 * @description
 * <pre>
 * 路由配置(RouteLocator)有2种,
 *      1. yml文件配置, 我们用这种就够用了
 *      2. config, 写java代码, 就是这个 , 这里作为延伸演示第二种路由配置的写法
 * </pre>
 * @create 2020/8/17 23:49
 */
@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        // 规则:
        // 核心内容和yml是一样的, route 有个id(这个id是为了区分多个路由), 有个uri(路由的uri)
        // 当我们通过网关9527访问/guonei 如果成功, 会跳转到这个uri
        routes.route("path_route_lisen",
                r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();

        return routes.build();
    }
}
