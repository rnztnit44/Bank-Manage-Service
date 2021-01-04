package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int employeeId;

    @Column(name = "address_id")
    private String addressId;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "aadhaar")
    private String aadhaar;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private int age;

    @Column(name = "employee_type")
    private String employeeType;

    @Column(name = "department")
    private String department;

    public Employee(){}
    private Employee(Builder builder) {
        this.employeeId = builder.employeeId;
        this.addressId = builder.addressId;
        this.name = builder.name;
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public static final class Builder {
        private int employeeId;
        private String addressId;
        private String name;
        private String mobileNo;
        private String aadhaar;
        private String email;
        private int age;
        private String employeeType;
        private String department;

        public Builder() {
        }

        public Employee build() {
            return new Employee(this);
        }

        public Builder employeeId(int employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder addressId(String addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
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

        public Builder employeeType(String employeeType) {
            this.employeeType = employeeType;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }
    }
}
