package com.bank.service;

import com.bank.bean.AccountRequest;
import com.bank.bean.AddressPojo;
import com.bank.bean.CustomerPojo;
import com.bank.constant.BankConstant;
import com.bank.entity.Account;
import com.bank.entity.Address;
import com.bank.entity.Customer;
import com.bank.enums.AccountType;
import com.bank.repository.AccountRepository;
import com.bank.repository.AddressRepository;
import com.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    public String addCustomer(CustomerPojo customerPojo) {
        AddressPojo addressPojo = customerPojo.getAddressPojo();
        Address address = new Address.Builder().location(addressPojo.getLocation())
                .city(addressPojo.getCity()).country(addressPojo.getCountry())
                .pinCode(addressPojo.getPinCode()).state(addressPojo.getState()).build();
        addressRepository.save(address);
        Customer customer = new Customer.Builder().aadhaar(customerPojo.getAadhaar())
                .email(customerPojo.getEmail()).mobileNo(customerPojo.getMobileNo())
                .name(customerPojo.getName()).build();
        customerRepository.save(customer);
        return BankConstant.ADD_SUCCESS;
    }

    public CustomerPojo GetDetailForCustomer(String cId) {
        Customer customer = customerRepository.findByCustomerId(cId);
        CustomerPojo customerPojo = new CustomerPojo.CustomerBuilder().build();
        return null;
    }

    public String updateKycForCustomer(String cId, CustomerPojo customerPojo) {


        return null;
    }

    public String deleteCustomer(String cId) {
        customerRepository.deleteById(cId);
        return BankConstant.DELETE_SUCCESS;
    }

    public String accountBalanceForAccount(String accountNo) {
        Optional<Account> account = accountRepository.findById(accountNo);
     //   account.stream().
        return null;
    }
    public String transferMoney(String sourceAccount, String targetAccount, int amount) {
        return null;
    }

    public String accountStatement(String account, Date startTime, Date endTime) {
        return null;
    }

    public String calculateInterest(String account, float interestRate) {
        return null;
    }

    public String createAccount(AccountRequest accountRequest) {
        return null;
    }

    public String linkCustomerWithAccounts(List<AccountRequest> accountRequestList) {
        return null;
    }



}
