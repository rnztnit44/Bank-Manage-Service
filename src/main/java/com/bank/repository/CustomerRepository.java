package com.bank.repository;

import com.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = "Select * from customer C where C.customer_id=:cId", nativeQuery = true)
    Customer findByCustomerId(String cId);

    @Query(value = "Update customer C where C.customer_id=:cId", nativeQuery = true)
    void updateCustomer(Customer customer);
}
