package com.bank.bean;

import com.bank.enums.AccountType;
import lombok.Data;

//@Data
public class AccountPojo {
    AccountType accountType;
    String accountNo;
    int amount;

    private AccountPojo(Builder builder) {
        this.accountType = builder.accountType;
        this.accountNo = builder.accountNo;
        this.amount = builder.amount;
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
