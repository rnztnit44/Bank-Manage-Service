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
import com.bank.entity.CustomerAccountMapping;
import com.bank.enums.AccountType;
import com.bank.exception.BankException;
import com.bank.repository.AccountRepository;
import com.bank.repository.AddressRepository;
import com.bank.repository.CustomerAccoutRepository;
import com.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private CustomerAccoutRepository customerAccoutRepository;

    public String addCustomer(CustomerPojo customerPojo) {
        AddressPojo addressPojo = customerPojo.getAddressPojo();
        Address address = new Address.Builder().location(addressPojo.getLocation())
                .city(addressPojo.getCity()).country(addressPojo.getCountry())
                .pinCode(addressPojo.getPinCode()).state(addressPojo.getState()).build();
        addressRepository.save(address);
        Customer customer = new Customer.Builder().aadhaar(customerPojo.getAadhaar())
                .email(customerPojo.getEmail()).mobileNo(customerPojo.getMobileNo())
                .name(customerPojo.getName()).addressId(address.getAddressId()).build();
        customerRepository.save(customer);
        return BankConstant.CUSTOMER_ADD_SUCCESS;
    }

    public String createAccount(AccountRequest accountRequest) {
        Account account = new Account.Builder().accountNo(accountRequest.getAccountNo()).
                accountType(accountRequest.getAccountType().getName()).amount(accountRequest.getAmount())
                .build();
        accountRepository.save(account);
        return BankConstant.ACCOUNT_CREATION_SUCCESS;
    }

    public String linkCustomerWithAccounts( int cId, List<String> accountNos) throws BankException {
        Customer customer = customerRepository.findByCustomerId(cId);
        if (customer == null)
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_EXIST);
        for (String accountNo : accountNos) {
            Account account = accountRepository.findByAccountNo(accountNo);
            if (account != null) {
                CustomerAccountMapping customerAccount = customerAccoutRepository.findByAccountId(accountNo);
                if (customerAccount != null)
                    customerAccount.setCustomerId(cId);
                else
                    customerAccount = new CustomerAccountMapping.Builder().accountNo(accountNo).customerId(cId).build();
                customerAccoutRepository.save(customerAccount);
                account.setCustomerId(cId);
                accountRepository.save(account);
            }
        }
        return BankConstant.ACCOUNT_LINK_SUCCESS;
    }


    public String updateKycForCustomer(int cId, String aadhaar, String mobile, String email) throws BankException {
        Customer customer = customerRepository.findByCustomerId(cId);
        if (customer == null)
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_EXIST);
        if (aadhaar != null)
            customer.setAadhaar(aadhaar);
        if (mobile != null)
            customer.setMobileNo(mobile);
        if (email != null)
            customer.setEmail(email);
        customerRepository.save(customer);
        return BankConstant.KYC_UPDATE_SUCCESS;
    }



    public CustomerPojo GetDetailForCustomer(int cId) throws BankException {
        Customer customer = customerRepository.findByCustomerId(cId);
        if (customer == null)
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_EXIST);
        int addressId = customer.getAddressId();
        Address address = addressRepository.findByAddressId(addressId);
        AddressPojo addressPojo = AddressPojo.newAddressPojo().city(address.getCity()).
                country(address.getCountry()).location(address.getLocation()).pinCode(address.getPinCode())
                .state(address.getState()).build();
        List<CustomerAccountMapping> customerAccounts = customerAccoutRepository.findByCustomerId(cId);
        List<AccountPojo> accountList = new ArrayList<>();
        for (CustomerAccountMapping customerAccountMapping : customerAccounts) {
            Account account = accountRepository.findByAccountNo(customerAccountMapping.getAccountNo());
            AccountPojo accountPojo = new AccountPojo.Builder().accountNo(customerAccountMapping.getAccountNo()).
                    accountType(AccountType.valueOf(account.getAccountType().toUpperCase())).amount(account.getAmount()).build();
            accountList.add(accountPojo);
        }
        CustomerPojo customerPojo = new CustomerPojo.CustomerBuilder().setAddressPojo(addressPojo).setAadhaar(customer.getAadhaar())
        .setEmail(customer.getEmail()).setMobileNo(customer.getMobileNo())
        .setName(customer.getName()).accountList(accountList).build();
        return customerPojo;
    }



    public String deleteCustomer(int cId) throws BankException {
        Customer customer = customerRepository.findByCustomerId(cId);
        if (customer == null)
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_EXIST);
        customerRepository.deleteById(cId);
        return BankConstant.DELETE_SUCCESS;
    }

    public int accountBalanceForAccount(String accountNo) throws BankException {
        Account account = accountRepository.findByAccountNo(accountNo);
        if (account == null) {
            throw new BankException(ExceptionConstant.ACCOUNT_NOT_EXIST);
        }
        return account.getAmount();
    }
    public String transferMoney(String sourceAccountNo, String targetAccountNo, int amount) throws BankException {
        Account sourceAccount = accountRepository.findByAccountNo(sourceAccountNo);
        if (sourceAccount == null) {
            throw new BankException(ExceptionConstant.SOURCE_ACCOUNT_NOT_EXIST);
        }
        int netAmount = sourceAccount.getAmount();
        if (netAmount < amount) {
            throw new BankException(ExceptionConstant.NOT_SUFFICIENT_AMOUNT);
        }
        Account targetAccount = accountRepository.findByAccountNo(targetAccountNo);
        if (targetAccount == null) {
            throw new BankException(ExceptionConstant.TARGET_ACCOUNT_NOT_EXIST);
        }
        sourceAccount.setAmount(netAmount-amount);
        targetAccount.setAmount(targetAccount.getAmount()+amount);
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
        return BankConstant.TRANSFER_MONEY_SUCCESS;
    }

    public String accountStatement(String accountNo, Date startTime, Date endTime) {

        return null;
    }

    public float calculateInterest(String accountNo, float interestRate) throws BankException {
        Account account = accountRepository.findByAccountNo(accountNo);
        if (account == null) {
            throw new BankException(ExceptionConstant.ACCOUNT_NOT_EXIST);
        }
        int amount = account.getAmount();
        float interest = (amount  * interestRate * 1 )% 100;
        return interest;
    }


}
