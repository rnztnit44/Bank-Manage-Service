package com.bank.bean;

import lombok.Data;

import java.util.List;

@Data
public class CustomerPojo {
    private int cId;
    private String name;
    private AddressPojo addressPojo;
    private String mobileNo;
    private String aadhaar;
    private String email;
    private List<AccountPojo> accountPojoList;

    public CustomerPojo(){}
    public CustomerPojo(CustomerBuilder builder) {
        this.cId=builder.cId;
        this.name=builder.name;
        this.addressPojo =builder.addressPojo;
        this.mobileNo=builder.mobileNo;
        this.aadhaar=builder.aadhaar;
        this.email=builder.email;
        this.accountPojoList =builder.accountPojoList;
    }

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
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

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AccountPojo> getAccountPojoList() {
        return accountPojoList;
    }

    public void setAccountPojoList(List<AccountPojo> accountPojoList) {
        this.accountPojoList = accountPojoList;
    }


    //Builder Class
    public static class CustomerBuilder{
        private int cId;
        private String name;
        private AddressPojo addressPojo;
        private String mobileNo;
        private String aadhaar;
        private String email;
        private List<AccountPojo> accountPojoList;


        public CustomerBuilder setCid(int cId) {
            this.cId = cId;
            return this;
        }

        public CustomerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CustomerBuilder setAddressPojo(AddressPojo addressPojo) {
            this.addressPojo = addressPojo;
            return this;
        }

        public CustomerBuilder setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
            return this;
        }

        public CustomerBuilder setAadhaar(String aadhaar) {
            this.aadhaar = aadhaar;
            return this;
        }

        public CustomerBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder accountList(List<AccountPojo> accountPojoList) {
            this.accountPojoList = accountPojoList;
            return this;
        }

        public CustomerPojo build() {
            return new CustomerPojo(this);
        }
    }
}
