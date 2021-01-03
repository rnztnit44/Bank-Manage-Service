package com.bank.repository;

import com.bank.entity.Address;
import com.bank.entity.CustomerAccountMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerAccoutRepository extends JpaRepository<CustomerAccountMapping, String> {

    @Query(value = "Select account_no from CustomerAccountMapping C where C.customer_id=:customerId", nativeQuery = true)
    List<String> findAllAcountNoByCustomerId(String customerId);
}
