package com.app.financial.quotation.adapter.btc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerExceptionBTC extends  ResponseEntityExceptionHandler{

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        BTCErrorDetails btcErrorDetails =  new BTCErrorDetails("HandlerExceptionBTC -> Error when get automatic Quotation - " + ex.getMessage());
        return new ResponseEntity<>(btcErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}