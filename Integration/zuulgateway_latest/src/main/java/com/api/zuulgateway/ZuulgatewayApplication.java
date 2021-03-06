package com.api.zuulgateway;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.api.zuulgateway.filters.ErrorFilter;
import com.api.zuulgateway.filters.PostFilter;
import com.api.zuulgateway.filters.PreFilter;
import com.api.zuulgateway.filters.RouteFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@RefreshScope
public class ZuulgatewayApplication {
	
	Logger logger = LoggerFactory.getLogger(ZuulgatewayApplication.class);

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
 
}
