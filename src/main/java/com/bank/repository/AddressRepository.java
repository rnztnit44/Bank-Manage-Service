package com.bank.repository;

import com.bank.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, String> {

    @Query(value = "Select * from Address A where A.address_id=:addressId", nativeQuery = true)
    Address findByAddressId(int addressId);
}
