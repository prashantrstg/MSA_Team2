package com.client.productview.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class RestExceptionHandler {

      

       @ExceptionHandler(ProductFoundExceptionException.class)

       public ResponseEntity<ErrorResponse> exception(ProductFoundExceptionException e){

              ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

              errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());

              return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

       }

       @ExceptionHandler({BusinessException.class})

       public ResponseEntity<ErrorResponse> exception(BusinessException e){

              ErrorResponse errorResponse = new ErrorResponse(e.getMessage());

              errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());

              return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

       }
}
