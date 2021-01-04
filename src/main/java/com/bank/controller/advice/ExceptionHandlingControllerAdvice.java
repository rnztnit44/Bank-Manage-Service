package com.bank.controller.advice;

import com.bank.controller.AdminController;
import com.bank.exception.BankException;
import com.bank.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.bank.constant.ApiConstant.SERVER_ERROR_MESSAGE;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlingControllerAdvice.class);
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.error("Exception while processing the request ", ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),SERVER_ERROR_MESSAGE);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BankException.class)
    public ResponseEntity<ExceptionResponse> handleFoodTruckException(BankException ex) {
        log.error("handleUnnatiLoanProcessException Exception :{}", ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
