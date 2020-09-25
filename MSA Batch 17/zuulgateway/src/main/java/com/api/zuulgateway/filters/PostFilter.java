package com.api.zuulgateway.filters;

import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter {
	
	private static final String FILTER_TYPE = "error";
    private static final String THROWABLE_KEY = "throwable";
    private static final int FILTER_ORDER = -1;
	 
	  @Override
	  public String filterType() {
	    return "post";
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
	   System.out.println("Inside PostFilter Filter");
	   final RequestContext context = RequestContext.getCurrentContext();
       final Object throwable = context.get(THROWABLE_KEY);
	   if (throwable instanceof Exception) {
           
    	   System.out.println(" of this type");
           ZuulException zuulException = new ZuulException("Invalid Credentials Zuul Exception Body", 401, "Invalid Credentials Zuul Exception Body");
           throw new ZuulRuntimeException(zuulException);
       }else {
    	   System.out.println("not of this type");
       }
	 
	    return null;
	  }
	}
