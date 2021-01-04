package com.bank.repository;

import com.bank.entity.CustomerAccountMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerAccoutRepository extends JpaRepository<CustomerAccountMapping, Integer> {

    @Query(value = "Select * from customer_account C where C.customer_id=:customerId", nativeQuery = true)
    List<CustomerAccountMapping> findByCustomerId(int customerId);

    @Query(value = "Select * from customer_account C where C.account_no=:accountNo", nativeQuery = true)
    CustomerAccountMapping findByAccountId(String accountNo);
}
