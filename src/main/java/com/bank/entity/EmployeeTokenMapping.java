package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee_token")
public class EmployeeTokenMapping {

    @Id
    @Column(name = "user_id")
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int userid;

    @Column(name = "token")
    private String token;

    public EmployeeTokenMapping(){}
    private EmployeeTokenMapping(Builder builder) {
        this.userid = builder.userid;
        this.token = builder.token;
    }

    public static Builder newEmployeeTokenMapping() {
        return new Builder();
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public static final class Builder {
        private int userid;
        private String token;

        private Builder() {
        }

        public EmployeeTokenMapping build() {
            return new EmployeeTokenMapping(this);
        }

        public Builder userid(int userid) {
            this.userid = userid;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }
    }
}
