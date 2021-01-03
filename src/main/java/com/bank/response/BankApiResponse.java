package com.bank.response;

import lombok.Data;

@Data
public class BankApiResponse<T> {
    private T message;
    private int status;

    public BankApiResponse() {
    }

    public BankApiResponse(int status, T message) {
        this.status = status;
        this.message = message;
    }
}
