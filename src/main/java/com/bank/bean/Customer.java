package com.bank.bean;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    int cId;
    String name;
    Address address;
    String mobileNo;
    String email;
    List<Account> accountList;

}
