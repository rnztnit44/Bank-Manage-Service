package com.bank.repository;

import com.bank.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query(value = "Select * from Employee E where E.employee_id=:userId", nativeQuery = true)
    Employee findByEmployeeId(int userId);
}
