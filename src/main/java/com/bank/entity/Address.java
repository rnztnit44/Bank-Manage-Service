package com.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

//    String location;
//    String city;
//    String state;
//    String country;
//    int pinCode;
    @Id
    @Column(name = "address_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private String addressId;

    @Column(name = "location")
    private String location;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "pin_code")
    private int pinCode;

    private Address(Builder builder) {
        this.addressId = builder.addressId;
        this.location = builder.location;
        this.city = builder.city;
        this.state = builder.state;
        this.country = builder.country;
        this.pinCode = builder.pinCode;
    }

    public static Builder newAddress() {
        return new Builder();
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public static final class Builder {
        private String addressId;
        private String location;
        private String city;
        private String state;
        private String country;
        private int pinCode;

        public Builder() {
        }

        public Address build() {
            return new Address(this);
        }

        public Builder addressId(String addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder location(String location) {
            this.location = location;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder pinCode(int pinCode) {
            this.pinCode = pinCode;
            return this;
        }
    }
}
