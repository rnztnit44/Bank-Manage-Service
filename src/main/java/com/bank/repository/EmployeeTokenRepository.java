package com.bank.repository;

import com.bank.entity.Employee;
import com.bank.entity.EmployeeTokenMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeTokenRepository extends JpaRepository<EmployeeTokenMapping, String> {
//
//    @Query(value = "Select * from Employee E where E.employee_id=:employeeId", nativeQuery = true)
//    Employee findByEmployeeId(String employeeId);
//
//    @Query(value = "Update customer C where C.customer_id=:cId", nativeQuery = true)
//    void updateCustomer(String cId);

    @Query(value = "Update EmployeeTokenMapping E SET E.token = :header where E.user_id=:userId", nativeQuery = true)
    void updateAuthForAdmin(int userId, String header);


    @Query(value = "Delete from EmployeeTokenMapping E where E.token=:header", nativeQuery = true)
    void deleteAuth(String header);
}