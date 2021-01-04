package com.bank.service;

import com.bank.bean.AddressPojo;
import com.bank.bean.EmployeePojo;
import com.bank.constant.BankConstant;
import com.bank.constant.ExceptionConstant;
import com.bank.entity.Address;
import com.bank.entity.Customer;
import com.bank.entity.Employee;
import com.bank.exception.BankException;
import com.bank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerAccoutRepository CustomerAccoutRepository;

    public String addEmployees(List<EmployeePojo> employeePojoList) throws BankException {
        boolean invalidEmployee = false;
        for (EmployeePojo employeePojo : employeePojoList) {
            if (employeePojo == null) {
                invalidEmployee = true;
            }
            AddressPojo addressPojo = employeePojo.getAddressPojo();
            Address address = new Address.Builder().location(addressPojo.getLocation())
                    .city(addressPojo.getCity()).country(addressPojo.getCountry())
                    .pinCode(addressPojo.getPinCode()).state(addressPojo.getState()).build();
            addressRepository.save(address);
            Employee employee = new Employee.Builder().aadhaar(employeePojo.getAadhaar())
                    .email(employeePojo.getEmail()).mobileNo(employeePojo.getMobileNo())
                    .name(employeePojo.getName()).addressId(address.getAddressId())
                    .employeeType(employeePojo.getEmployeeType().name()).
                    department(employeePojo.getDepartment()).build();
            employeeRepository.save(employee);
        }
        if (invalidEmployee)
            throw new BankException(ExceptionConstant.INVALID_INPUT);
        return BankConstant.ADD_EMPLOYEES_SUCCESS;
    }

    public String deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
        return BankConstant.DELETE_SUCCESS;
    }
}
