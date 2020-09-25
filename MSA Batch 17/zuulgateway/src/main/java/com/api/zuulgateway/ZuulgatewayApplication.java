package com.api.zuulgateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.zuulgateway.filters.ErrorFilter;
import com.api.zuulgateway.filters.PostFilter;
import com.api.zuulgateway.filters.PreFilter;
import com.api.zuulgateway.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@RefreshScope
public class ZuulgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulgatewayApplication.class, args);
	}

	@Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
    
    @Value("${apigateway.message}")

	String gatewayMessage;

	@RequestMapping(value = "/msg")

	public String configMessageText() {

		return gatewayMessage;

	}
}
