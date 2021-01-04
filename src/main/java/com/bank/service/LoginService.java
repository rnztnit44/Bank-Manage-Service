package com.bank.service;

import com.bank.repository.EmployeeRepository;
import com.bank.repository.EmployeeTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
public class LoginService {


    @Autowired
    private EmployeeTokenRepository employeeTokenRepository;

    public UUID generateAuthAdmin(@NotNull int userId) {
        UUID uuid = UUID.randomUUID();
        employeeTokenRepository.updateAuthForAdmin(userId,uuid.toString());
        return uuid;
    }

    public void deleteAuthAdmin(String header) {
        employeeTokenRepository.deleteAuth(header);
    }


    public UUID generateAuthEmployee(int employeeId) {
        UUID uuid = UUID.randomUUID();
        String key = uuid.toString() + employeeId;
        employeeTokenRepository.updateAuthForAdmin(employeeId, key);
        return uuid;
    }
}