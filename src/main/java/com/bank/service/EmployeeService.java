package com.bank.service;

import com.bank.bean.AccountPojo;
import com.bank.bean.AccountRequest;
import com.bank.bean.AddressPojo;
import com.bank.bean.CustomerPojo;
import com.bank.constant.BankConstant;
import com.bank.constant.ExceptionConstant;
import com.bank.entity.Account;
import com.bank.entity.Address;
import com.bank.entity.Customer;
import com.bank.enums.AccountType;
import com.bank.exception.BankException;
import com.bank.repository.AccountRepository;
import com.bank.repository.AddressRepository;
import com.bank.repository.CustomerAccoutRepository;
import com.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class EmployeeService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerAccoutRepository CustomerAccoutRepository;

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

    public String createAccount(AccountRequest accountRequest) {
        AddressPojo addressPojo = accountRequest.getAddressPojo();

        Address address = new Address.Builder().location(addressPojo.getLocation())
                .city(addressPojo.getCity()).country(addressPojo.getCountry())
                .pinCode(addressPojo.getPinCode()).state(addressPojo.getState()).build();
        addressRepository.save(address);
        Customer customer = new Customer.Builder().aadhaar(accountRequest.getAadhaar())
                .email(accountRequest.getEmail()).mobileNo(accountRequest.getMobileNo())
                .name(accountRequest.getName()).build();
        customerRepository.save(customer);

        Account account = new Account.Builder().accountNo(accountRequest.getAccountNo()).
                accountType(accountRequest.getAccountType().getName()).amount(accountRequest.getAmount())
                .customerId("7").build();
        accountRepository.save(account);

        return BankConstant.ACCOUNT_CREATION_SUCCESS;
    }

    public String linkCustomerWithAccounts( String cId, List<AccountRequest> accountRequestList) {

        return null;
    }

    public String updateKycForCustomer(String cId, CustomerPojo customerPojo) {


        return null;
    }

    public CustomerPojo GetDetailForCustomer(String cId) {
        Customer customer = customerRepository.findByCustomerId(cId);
        String addressId = customer.getAddressId();
        Address address = addressRepository.findByAddressId(addressId);
        AddressPojo addressPojo = AddressPojo.newAddressPojo().city(address.getCity()).
                country(address.getCountry()).location(address.getLocation()).pinCode(address.getPinCode())
                .state(address.getState()).build();
        List<String> accountNoList = CustomerAccoutRepository.findAllAcountNoByCustomerId(cId);
        List<AccountPojo> accountList = new ArrayList<>();
        for (String accountNo : accountNoList) {
            Account account = accountRepository.findByAccountNo(accountNo);
            AccountPojo accountPojo = new AccountPojo.Builder().accountNo(accountNo).
                    accountType(AccountType.valueOf(account.getAccountType())).amount(account.getAmount()).build();
            accountList.add(accountPojo);
        }
        CustomerPojo customerPojo = new CustomerPojo.CustomerBuilder().setAddressPojo(addressPojo).setAadhaar(customer.getAadhaar())
        .setCid(customer.getCustomerId()).setEmail(customer.getEmail()).setMobileNo(customer.getMobileNo())
        .setName(customer.getName()).accountList(accountList).build();
        return customerPojo;
    }



    public String deleteCustomer(String cId) {
        customerRepository.deleteById(cId);
        return BankConstant.DELETE_SUCCESS;
    }

    public int accountBalanceForAccount(String accountNo) {
        Account account = accountRepository.findByAccountNo(accountNo);
        return account.getAmount();
    }
    public String transferMoney(String sourceAccountNo, String targetAccountNo, int amount) throws BankException {
        Account sourceAccount = accountRepository.findByAccountNo(sourceAccountNo);
        int netAmount = sourceAccount.getAmount();
        if (netAmount < amount) {
            throw new BankException(ExceptionConstant.NOT_SUFFICIENT_AMOUNT);
        }
        Account targetAccount = accountRepository.findByAccountNo(targetAccountNo);
        sourceAccount.setAmount(netAmount-amount);
        targetAccount.setAmount(targetAccount.getAmount()+amount);
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
        return null;
    }

    public String accountStatement(String accountNo, Date startTime, Date endTime) {

        return null;
    }

    public float calculateInterest(String accountNo, float interestRate) {
        Account account = accountRepository.findByAccountNo(accountNo);
        int amount = account.getAmount();
        float interest = (amount  * interestRate * 1 )% 100;
        return interest;
    }






}
