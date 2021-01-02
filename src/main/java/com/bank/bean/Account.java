package com.bank.bean;

import com.bank.enums.AccountType;
import lombok.Data;

@Data
public class Account {
    AccountType accountType;
    int amount;
}
