package com.bank.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee_token")
public class EmployeeTokenMapping {

    @Id
    @Column(name = "token")
    private String token;

    @Column(name = "employee_id")
    private int employeeId;

    public EmployeeTokenMapping(){}

    private EmployeeTokenMapping(Builder builder) {
        this.employeeId = builder.employeeId;
        this.token = builder.token;
    }

    public static Builder newEmployeeTokenMapping() {
        return new Builder();
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static final class Builder {
        private int employeeId;
        private String token;

        public Builder() {
        }

        public EmployeeTokenMapping build() {
            return new EmployeeTokenMapping(this);
        }

        public Builder employeeId(int employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }
    }
}
