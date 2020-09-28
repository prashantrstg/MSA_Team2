package com.api.zuulgateway.filters;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class RouteFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(RouteFilter.class);

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
		final byte[] decodedBytes = Base64.decodeBase64(encoded.getBytes());
		final String pair = new String(decodedBytes);
		final String[] userDetails = pair.split(":", 2);
		return userDetails;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		logger.info(request.getHeader("Authorization"));

		String base64Credentials = request.getHeader("Authorization").substring("Basic ".length()).trim();
		String[] userDetails = decode(base64Credentials);
		String userName = userDetails[0];
		String passWord = userDetails[1];

		if ((username.equals(userName))) {
			if (password.equals(passWord)) {
				logger.info("Request Method RouteFilter: " + request.getMethod() + " Request URL : "
						+ request.getRequestURL().toString());
			} else {
				logger.info(username + "---------Entered Credentials---------" + password);
				logger.info(userName + "---------Correct Credentials---------" + passWord);
				throw new ArithmeticException("not valid");
			}
		} else {
			logger.info(username + "---------Entered Credentials---------" + password);
			logger.info(userName + "---------Correct Credentials---------" + passWord);
			throw new ArithmeticException("not valid");
		}
		return null;
	}
}
