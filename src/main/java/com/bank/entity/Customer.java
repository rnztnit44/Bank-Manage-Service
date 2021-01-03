package com.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
//    private int cId;
//    private String name;
//    private Address address;
//    private String mobileNo;
//    private String aadhaar;
//    private String email;
//    private List<AccountPojo> accountPojoList;

    @Id
    @Column(name = "customer_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private String customerId;

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

    private Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.addressId = builder.addressId;
        this.name = builder.name;
        this.mobileNo = builder.mobileNo;
        this.aadhaar = builder.aadhaar;
        this.email = builder.email;
    }

    public static Builder newCustomer() {
        return new Builder();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public static final class Builder {
        private String customerId;
        private String addressId;
        private String name;
        private String mobileNo;
        private String aadhaar;
        private String email;

        public Builder() {
        }

        public Customer build() {
            return new Customer(this);
        }

        public Builder customerId(String customerId) {
            this.customerId = customerId;
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
    }
}
