package com.bank.service;

import com.bank.bean.AccountPojo;
import com.bank.bean.AddressPojo;
import com.bank.bean.CustomerPojo;
import com.bank.constant.BankConstant;
import com.bank.constant.ExceptionConstant;
import com.bank.entity.Account;
import com.bank.entity.Address;
import com.bank.entity.Customer;
import com.bank.entity.CustomerAccountMapping;
import com.bank.enums.AccountType;
import com.bank.enums.TransactionType;
import com.bank.exception.BankException;
import com.bank.repository.AccountRepository;
import com.bank.repository.AddressRepository;
import com.bank.repository.CustomerAccoutRepository;
import com.bank.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

@Service
public class EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerAccoutRepository customerAccoutRepository;

    public String addCustomer(CustomerPojo customerPojo) throws BankException {
        Customer customer = customerRepository.findByAadhaarId(customerPojo.getAadhaar());
        if (customer != null) {
            LOG.info("customer with aadhaar Id {} already exist ", customerPojo.getAadhaar());
            throw new BankException(ExceptionConstant.CUSTOMER_ALREADY_EXIST);
        }
        AddressPojo addressPojo = customerPojo.getAddressPojo();
        Address address = new Address.Builder().location(addressPojo.getLocation())
                .city(addressPojo.getCity()).country(addressPojo.getCountry())
                .pinCode(addressPojo.getPinCode()).state(addressPojo.getState()).build();
        addressRepository.save(address);
        customer = new Customer.Builder().aadhaar(customerPojo.getAadhaar())
                .email(customerPojo.getEmail()).mobileNo(customerPojo.getMobileNo())
                .name(customerPojo.getName()).addressId(address.getAddressId()).build();
        customerRepository.save(customer);
        LOG.info("customer with aadhaar :{} got created successfully ", customerPojo.getAadhaar());
        return BankConstant.CUSTOMER_ADD_SUCCESS;
    }

    public String createAccount(AccountPojo accountPojo) throws BankException {
        Account account = accountRepository.findByAccountNo(accountPojo.getAccountNo());
        if (account != null) {
            LOG.info("account already exist :{}", accountPojo.getAccountNo());
            throw new BankException(ExceptionConstant.ACCOUNT_ALREADY_EXIST);
        }
        account = new Account.Builder().accountNo(accountPojo.getAccountNo()).
                accountType(accountPojo.getAccountType().getName()).amount(accountPojo.getAmount())
                .build();
        accountRepository.save(account);
        LOG.info("new account with accountNo :{} got created successfully ", accountPojo.getAccountNo());
        return BankConstant.ACCOUNT_CREATION_SUCCESS;
    }

    public String linkCustomerWithAccounts( int cId, List<String> accountNos) throws BankException {
        boolean invalidAccount = false;
        Customer customer = customerRepository.findByCustomerId(cId);
        if (customer == null) {
            LOG.info("customer not exist :{}", cId);
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_EXIST);
        }
        for (String accountNo : accountNos) {
            Account account = accountRepository.findByAccountNo(accountNo);
            if (account == null) {
                LOG.info("account not exist :{}", accountNo);
                invalidAccount = true;
            } else {
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
        if (invalidAccount)
            throw new BankException(ExceptionConstant.ACCOUNT_NOT_EXIST_FOR_LINK);
        return BankConstant.ACCOUNT_LINK_SUCCESS;
    }


    public String updateKycForCustomer(int cId, String aadhaar, String mobile, String email) throws BankException {
        Customer customer = customerRepository.findByCustomerId(cId);
        if (customer == null) {
            LOG.info("customer not exist :{}", cId);
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_EXIST);
        }
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
        if (customer == null) {
            LOG.info("customer not exist :{}", cId);
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_EXIST);
        }
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
        if (customer == null) {
            LOG.info("customer not exist :{}", cId);
            throw new BankException(ExceptionConstant.CUSTOMER_NOT_EXIST);
        }
        customerRepository.deleteById(cId);
        LOG.info("customer got deleted successfully :{}", cId);
        return BankConstant.DELETE_SUCCESS;
    }

    public int accountBalanceForAccount(String accountNo) throws BankException {
        Account account = accountRepository.findByAccountNo(accountNo);
        if (account == null) {
            LOG.info("account not exist :{}", accountNo);
            throw new BankException(ExceptionConstant.ACCOUNT_NOT_EXIST);
        }
        return account.getAmount();
    }
    public String transferMoney(String sourceAccountNo, String targetAccountNo, int amount) throws BankException {
        Account sourceAccount = accountRepository.findByAccountNo(sourceAccountNo);
        if (sourceAccount == null) {
            LOG.info("sourceAccount not exist :{}", sourceAccount);
            throw new BankException(ExceptionConstant.SOURCE_ACCOUNT_NOT_EXIST);
        }
        int netAmount = sourceAccount.getAmount();
        if (netAmount < amount) {
            LOG.info("required Amount :{} is lesser than amount :{}", netAmount,amount);
            throw new BankException(ExceptionConstant.NOT_SUFFICIENT_AMOUNT);
        }
        Account targetAccount = accountRepository.findByAccountNo(targetAccountNo);
        if (targetAccount == null) {
            LOG.info("targetAccount not exist :{}", targetAccount);
            throw new BankException(ExceptionConstant.TARGET_ACCOUNT_NOT_EXIST);
        }
        sourceAccount.setAmount(netAmount-amount);
        sourceAccount.setTransactionType(TransactionType.DEBIT.name());
        sourceAccount.setTransactionDate(new Date());
        targetAccount.setAmount(targetAccount.getAmount()+amount);
        targetAccount.setTransactionDate(new Date());
        targetAccount.setTransactionType(TransactionType.CREDIT.name());
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);
        return BankConstant.TRANSFER_MONEY_SUCCESS;
    }

    public ByteArrayInputStream accountStatement(String accountNo, Date startTime, Date endTime) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(60);
            table.setWidths(new int[]{1, 3, 3});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Population", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);


            List<Customer> customers = new ArrayList<>();
            Customer customer = new Customer.Builder().aadhaar("1").build();
            customers.add(customer);
            for (Customer city : customers) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(city.getAadhaar().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(city.getName()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(city.getAadhaar())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        }  catch (DocumentException e) {
            LOG.error("Error occurred: {}", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public float calculateInterest(String accountNo, float interestRate) throws BankException {
        Account account = accountRepository.findByAccountNo(accountNo);
        if (account == null) {
            throw new BankException(ExceptionConstant.ACCOUNT_NOT_EXIST);
        }
        int amount = account.getAmount();
        float interest = (amount  * interestRate * 1 )/ 100;
        return interest;
    }


}
