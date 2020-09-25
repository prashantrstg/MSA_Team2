package com.api.zuulgateway.filters;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class RouteFilter extends ZuulFilter {
	
	@Value("${apigateway.username}")

	String username;
	
	@Value("${apigateway.password}")

	String password;
	 
	  @Override
	  public String filterType() {
	    return "route";
	  }
	 
	  @Override
	  public int filterOrder() {
	    return 1;
	  }
	 
	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }
	  
	  private static String[] decode(final String encoded) {
	        final byte[] decodedBytes 
	                = Base64.decodeBase64(encoded.getBytes());
	        final String pair = new String(decodedBytes);
	        final String[] userDetails = pair.split(":", 2);
	        return userDetails;
	    }
	 
	  @Override
	  public Object run(){
		  RequestContext ctx = RequestContext.getCurrentContext();
		    HttpServletRequest request = ctx.getRequest();
		    
		    System.out.println(request.getHeader("Authorization"));
		    String base64Credentials = request.getHeader("Authorization").substring("Basic ".length()).trim();
		    String[] userDetails = decode(base64Credentials);
		    String userName = userDetails[0];
	        String passWord = userDetails[1];
	        if(!(username.equals(userName) && password.equals(passWord))) {
	        	 throw new ArithmeticException("not valid");  
	        }else {
	        	 System.out.println("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
	        }
	        
		   
	   
	    return null;
	  }
	}
