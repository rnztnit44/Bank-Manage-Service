package com.bank.bean;

public class AddressPojo {
    String location;
    String city;
    String state;
    String country;
    int pinCode;

    public AddressPojo(){}

    private AddressPojo(Builder builder) {
        this.location = builder.location;
        this.city = builder.city;
        this.state = builder.state;
        this.country = builder.country;
        this.pinCode = builder.pinCode;
    }

    public static Builder newAddressPojo() {
        return new Builder();
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
        private String location;
        private String city;
        private String state;
        private String country;
        private int pinCode;

        private Builder() {
        }

        public AddressPojo build() {
            return new AddressPojo(this);
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
