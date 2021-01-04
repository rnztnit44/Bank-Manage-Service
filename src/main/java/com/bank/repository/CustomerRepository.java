package com.bank.repository;

import com.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "Select * from customer C where C.customer_id=:cId", nativeQuery = true)
    Customer findByCustomerId(int cId);

    @Query(value = "Update customer C SET C.email = :email where C.customer_id=:cId", nativeQuery = true)
    void setEmailId(int cId, String email);

    @Query(value = "Update customer C SET C.mobile = :mobile where C.customer_id=:cId", nativeQuery = true)
    void setMobileNo(int cId, String mobile);

    @Query(value = "Update customer C SET C.aadhaar = :aadhaar where C.customer_id=:cId", nativeQuery = true)
    void setAadhaar(int cId, String aadhaar);

}
