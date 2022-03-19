package com.webkoding.wbkdnggateway.filter;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;


@Slf4j
@Component
public class LogRequestInfoFilter extends AbstractGatewayFilterFactory<LogRequestInfoFilter.Config> {

    public LogRequestInfoFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            HttpMethod method = request.getMethod();
            RequestPath path = request.getPath();
            InetSocketAddress address = request.getRemoteAddress();
            log.info("method -> {}, url -> {}, address -> {},", method, path, address);
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }
}
