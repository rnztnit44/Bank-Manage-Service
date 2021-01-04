package com.bank.repository;

import com.bank.entity.EmployeeTokenMapping;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeTokenRepository extends CrudRepository<EmployeeTokenMapping, String> {

    @Query(value = "Update EmployeeTokenMapping E SET E.token = :header where E.user_id=:userId", nativeQuery = true)
    void updateAuthForAdmin(int userId, String header);

}