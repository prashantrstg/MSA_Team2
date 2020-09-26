package meru.product.service.product_service.exception;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException excep,HttpServletRequest request)
	{
		ExceptionResponse ExcpResponse = new ExceptionResponse();
		ExcpResponse.setErrorMessage(excep.getMessage());
		ExcpResponse.setRequestedURI(request.getRequestURI());
		
		return ExcpResponse;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(Exception excep, HttpServletRequest request)
	{
		ExceptionResponse ExcpResponse = new ExceptionResponse();
		ExcpResponse.setErrorMessage(excep.getMessage());
		ExcpResponse.setRequestedURI(request.getRequestURI());
		return ExcpResponse;
	}
	
}