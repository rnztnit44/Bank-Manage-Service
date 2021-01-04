package com.bank.service;

import com.bank.constant.BankConstant;
import com.bank.constant.ExceptionConstant;
import com.bank.entity.Employee;
import com.bank.entity.EmployeeTokenMapping;
import com.bank.exception.BankException;
import com.bank.repository.EmployeeRepository;
import com.bank.repository.EmployeeTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {


    @Autowired
    private EmployeeTokenRepository employeeTokenRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public UUID generateAuthAdmin(int userId) throws BankException {
        Employee employee = employeeRepository.findByEmployeeId(userId);
        if (employee == null)
            throw new BankException(ExceptionConstant.EMPLOYEE_NOT_EXIST);
        String employeeType = employee.getEmployeeType();
        if (!BankConstant.ADMIN.equals(employeeType)) {
            throw new BankException(ExceptionConstant.EMPLOYEE_NOT_ADMIN);
        }
        UUID uuid = UUID.randomUUID();
        EmployeeTokenMapping employeeTokenMapping = new EmployeeTokenMapping.Builder().
                token(uuid.toString()).employeeId(userId).build();
        employeeTokenRepository.save(employeeTokenMapping);
        return uuid;
    }

    public String deleteAuthAdmin(String token) {
        employeeTokenRepository.deleteById(token);
        return BankConstant.LOGOUT_SUCCESS;
    }


    public UUID generateAuthEmployee(int employeeId) throws BankException {
        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if (employee == null)
            throw new BankException(ExceptionConstant.EMPLOYEE_NOT_EXIST);
        String employeeType = employee.getEmployeeType();
        if (BankConstant.ADMIN.equals(employeeType)) {
            throw new BankException(ExceptionConstant.EMPLOYEE_ADMIN);
        }
        UUID uuid = UUID.randomUUID();
        EmployeeTokenMapping employeeTokenMapping = new EmployeeTokenMapping.Builder().
                token(uuid.toString()+employeeId).employeeId(employeeId).build();
        employeeTokenRepository.save(employeeTokenMapping);
        return uuid;

//        UUID uuid = UUID.randomUUID();
//        String key = uuid.toString() + employeeId;
//        employeeTokenRepository.updateAuthForAdmin(employeeId, key);
//        return uuid;
    }
}