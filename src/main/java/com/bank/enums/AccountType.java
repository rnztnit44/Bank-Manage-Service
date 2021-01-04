package com.bank.enums;

public enum AccountType {
    SAVING("saving"),
    SALARY("salary"),
    LOAN("loan"),
    CURRENT("current");

    private final String name;
    AccountType(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "name='" + name + '\'' +
                '}';
    }

}
