package com.bank.bean;

import com.bank.enums.AccountType;

public class AccountPojo {
    AccountType accountType;
    String accountNo;
    int amount;

    public AccountPojo(){}
    private AccountPojo(Builder builder) {
        this.accountType = builder.accountType;
        this.accountNo = builder.accountNo;
        this.amount = builder.amount;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static Builder newAccountPojo() {
        return new Builder();
    }


    public static final class Builder {
        private AccountType accountType;
        private String accountNo;
        private int amount;

        public Builder() {
        }

        public AccountPojo build() {
            return new AccountPojo(this);
        }

        public Builder accountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder accountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }
    }
}
