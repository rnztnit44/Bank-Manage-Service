package com.bank.bean;

import com.bank.enums.AccountType;

public class AccountRequest {
    private AccountType accountType;
    private int amount;
    private String accountNo;
    private String name;
    private AddressPojo addressPojo;
    private String mobileNo;
    private String email;

    private AccountRequest(Builder builder) {
        this.accountType = builder.accountType;
        this.amount = builder.amount;
        this.accountNo = builder.accountNo;
        this.name = builder.name;
        this.addressPojo = builder.addressPojo;
        this.mobileNo = builder.mobileNo;
        this.email = builder.email;
    }

    public static Builder newAccountRequest() {
        return new Builder();
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressPojo getAddressPojo() {
        return addressPojo;
    }

    public void setAddressPojo(AddressPojo addressPojo) {
        this.addressPojo = addressPojo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static final class Builder {
        private AccountType accountType;
        private int amount;
        private String accountNo;
        private String name;
        private AddressPojo addressPojo;
        private String mobileNo;
        private String email;

        private Builder() {
        }

        public AccountRequest build() {
            return new AccountRequest(this);
        }

        public Builder accountType(AccountType accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder accountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder addressPojo(AddressPojo addressPojo) {
            this.addressPojo = addressPojo;
            return this;
        }

        public Builder mobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
    }
}
