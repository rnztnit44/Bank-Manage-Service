package com.bank.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer_account")
@Data
public class CustomerAccountMapping {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private String customerId;

    @Column(name = "account_no")
    private String accountNo;

    private CustomerAccountMapping(Builder builder) {
        this.customerId = builder.customerId;
        this.accountNo = builder.accountNo;
    }

    public static Builder newCustomerAccountMapping() {
        return new Builder();
    }


    public static final class Builder {
        private String customerId;
        private String accountNo;

        private Builder() {
        }

        public CustomerAccountMapping build() {
            return new CustomerAccountMapping(this);
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder accountNo(String accountNo) {
            this.accountNo = accountNo;
            return this;
        }
    }
}
