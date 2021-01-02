package com.bank.repository;

import com.bank.entity.Address;
import com.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, String> {

    @Query(value = "Select * from customer C where C.customer_id=:cId", nativeQuery = true)
    Customer findByCustomerId(String cId);
}
