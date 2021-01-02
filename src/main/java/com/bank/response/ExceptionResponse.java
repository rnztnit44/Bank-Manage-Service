package com.bank.response;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String message;
    private long timestamp;
    private int status;

    public ExceptionResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public ExceptionResponse(int status) {
        this.timestamp = System.currentTimeMillis();
        this.status = status;
    }

    public ExceptionResponse(String message) {
        this.timestamp = System.currentTimeMillis();
        this.message = message;
    }
    public ExceptionResponse(int status, String message) {
        this.timestamp = System.currentTimeMillis();
        this.status = status;
        this.message = message;
    }
}
