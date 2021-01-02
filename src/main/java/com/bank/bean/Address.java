package com.bank.bean;

import lombok.Data;

@Data
public class Address {
    String village;
    String city;
    String state;
    String country;
    int pinCode;
}
