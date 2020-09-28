package com.api.zuulgateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.zuulgateway.ZuulgatewayApplication;
import com.netflix.zuul.ZuulFilter;

public class ErrorFilter extends ZuulFilter {
	
	Logger logger = LoggerFactory.getLogger(ErrorFilter.class);
	
	  @Override
	  public String filterType() {
	    return "error";
	  }
	 
	  @Override
	  public int filterOrder() {
	    return 1;
	  }
	 
	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }
	 
	  @Override
	  public Object run() {
		logger.info("Inside ErrorFilter Filter");
	    return null;
	  }
	}