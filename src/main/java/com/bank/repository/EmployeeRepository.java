package com.bank.repository;

import com.bank.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
//
//    @Query(value = "Select * from Employee E where E.employee_id=:employeeId", nativeQuery = true)
//    Employee findByEmployeeId(String employeeId);
//
//    @Query(value = "Update customer C where C.customer_id=:cId", nativeQuery = true)
//    void updateCustomer(String cId);

//    @Query(value = "Update customer C SET where C.customer_id=:cId", nativeQuery = true)
//    void updateAuthForAdmin(int userId, String header);
//
//
//    @Query(value = "Delete from customer C where C.customer_id=:cId", nativeQuery = true)
//    void deleteAuth(String header);


//    @Query(value = "Update customer C where C.customer_id=:cId", nativeQuery = true)
//    String getHeader(String header);
}
