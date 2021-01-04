package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "customer_account")
public class CustomerAccountMapping {

    @Id
    @Column(name = "customer_account_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int customerAccountId;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "account_no")
    private String accountNo;

    public CustomerAccountMapping(){

    }

    private CustomerAccountMapping(Builder builder) {
        this.customerAccountId = builder.customerAccountId;
        this.customerId = builder.customerId;
        this.accountNo = builder.accountNo;
    }

    public static Builder newCustomerAccountMapping() {
        return new Builder();
    }

    public int getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(int customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }


    public static final class Builder {
        private int customerAccountId;
        private int customerId;
        private String accountNo;

        public Builder() {
        }

        public CustomerAccountMapping build() {
            return new CustomerAccountMapping(this);
        }

        public Builder customerAccountId(int customerAccountId) {
            this.customerAccountId = customerAccountId;
            return this;
        }

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder accountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }
    }
}
