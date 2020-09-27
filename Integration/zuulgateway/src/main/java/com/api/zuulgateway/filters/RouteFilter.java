package com.api.zuulgateway.filters;

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
	        
	        if((username.equals(userName))) {
	        	System.out.println(userName + "---------Entered Credentials22222---------" + passWord);
	        	System.out.println(username + "---------Entered Credentials22222---------" + password);
	           	if(password.equals(passWord)) {
	           		System.out.println(userName + "---------Entered Credentials3333---------" + passWord);
	           		System.out.println(username + "---------Entered Credentials2222---------" + password);
	           		System.out.println("Request Method RouteFilter: " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
	           	}else {
	           		System.out.println(userName + "---------Entered Credentials44444---------" + passWord);
	           		System.out.println(username + "---------Entered Credentials22222---------" + password);
		        	throw new ArithmeticException("not valid");
		        }        	   
	        }else {
	        	System.out.println(userName + "---------Entered Credentials55555---------" + passWord);
	        	System.out.println(username + "---------Entered Credentials22222---------" + password);
	        	throw new ArithmeticException("not valid");
	        }   
	    return null;
	  }
	}
