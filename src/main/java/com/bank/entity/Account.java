package com.bank.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "transaction_type")
    private String transactionType;

    public Account(){}

    private Account(Builder builder) {
        this.accountNo = builder.accountNo;
        this.customerId = builder.customerId;
        this.amount = builder.amount;
        this.accountType = builder.accountType;
        this.transactionDate = builder.transactionDate;
        this.transactionType = builder.transactionType;
    }

    public static Builder newAccount() {
        return new Builder();
    }


    public static final class Builder {
        private String accountNo;
        private int customerId;
        private int amount;
        private String accountType;
        private Date transactionDate;
        private String transactionType;

        public Builder() {
        }

        public Account build() {
            return new Account(this);
        }

        public Builder accountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder amount(int amount) {
            this.amount = amount;
            return this;
        }

        public Builder accountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder transactionDate(Date transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public Builder transactionType(String transactionType) {
            this.transactionType = transactionType;
            return this;
        }
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
