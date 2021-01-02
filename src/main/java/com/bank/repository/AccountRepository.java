package com.bank.repository;

import com.bank.entity.Account;
import com.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "Select * from customer C where C.customer_id=:cId", nativeQuery = true)
    Customer findByCustomerId(String cId);
}
