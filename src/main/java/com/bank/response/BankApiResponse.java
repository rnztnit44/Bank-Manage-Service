package com.bank.response;

import lombok.Data;

@Data
public class BankApiResponse {
    private String message;
    private int status;

    public BankApiResponse() {
    }

    public BankApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
