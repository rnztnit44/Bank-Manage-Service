package com.bank.bean;

import com.bank.enums.EmployeeType;

public class EmployeePojo {
    private String name;
    private AddressPojo addressPojo;
    private String mobileNo;
    private String aadhaar;
    private String email;
    private int age;
    private EmployeeType employeeType;
    private String department ;

    public EmployeePojo(){}
    private EmployeePojo(Builder builder) {
        this.name = builder.name;
        this.addressPojo = builder.addressPojo;
        this.mobileNo = builder.mobileNo;
        this.aadhaar = builder.aadhaar;
        this.email = builder.email;
        this.age = builder.age;
        this.employeeType = builder.employeeType;
        this.department = builder.department;
    }

    public static Builder newEmployee() {
        return new Builder();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public static final class Builder {
        private String name;
        private AddressPojo addressPojo;
        private String mobileNo;
        private String aadhaar;
        private String email;
        private int age;
        private EmployeeType employeeType;
        private String department;

        private Builder() {
        }

        public EmployeePojo build() {
            return new EmployeePojo(this);
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

        public Builder aadhaar(String aadhaar) {
            this.aadhaar = aadhaar;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder employeeType(EmployeeType employeeType) {
            this.employeeType = employeeType;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }
    }
}
