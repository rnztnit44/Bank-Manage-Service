package com.bank.repository;

import com.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "Select * from account A where A.account_no=:accountNo", nativeQuery = true)
    Account findByAccountNo(String accountNo);
}
