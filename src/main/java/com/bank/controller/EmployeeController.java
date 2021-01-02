package com.bank.controller;

import com.bank.bean.Customer;
import com.bank.bean.Employee;
import com.bank.constant.ApiConstant;
import com.bank.exception.BankException;
import com.bank.response.BankApiResponse;
import com.bank.service.AdminService;
import com.bank.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/employee/")
public class EmployeeController {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    private EmployeeService employeeService;

    @PostMapping("addCustomer")
    public ResponseEntity<BankApiResponse> addCustomer(@RequestBody @NotNull Customer customer) throws BankException {
        LOG.info("addCustomer Api request params :{}", customer);
        String addSuccess = employeeService.addCustomer(customer);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,addSuccess));
    }

    @GetMapping("addCustomer")
    public ResponseEntity<Customer> GetDetailForCustomer(@RequestParam @NotNull String cId) throws BankException {
        LOG.info("addCustomer Api request params :{}", cId);
        return ResponseEntity.ok().body(employeeService.GetDetailForCustomer(cId));
    }

    @PutMapping("updateKyc")
    public ResponseEntity<BankApiResponse> updateKycForCustomer(@RequestParam Customer customer) throws BankException {
        LOG.info("addCustomer Api request params :{}", customer);
        String updateSuccess = employeeService.updateKycForCustomer(customer);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,updateSuccess));
    }

    @PostMapping("deleteCustomer")
    public ResponseEntity<BankApiResponse> deleteCustomer(@RequestBody @NotNull String cId) throws BankException {
        LOG.info("deleteCustomer Api request params :{}", cId);
        String deleteSuccess = employeeService.deleteCustomer(cId);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,deleteSuccess));
    }

    @PostMapping("accountDetails")
    public ResponseEntity<BankApiResponse> accountDetailsForCustomer(@RequestBody @NotNull String accountNo) throws BankException {
        LOG.info("accountDetailsForCustomer Api request params :{}", accountNo);
        String deleteSuccess = employeeService.accountDetailsForCustomer(accountNo);//.....
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,deleteSuccess));
    }

    @PostMapping("transferMoney")
    public ResponseEntity<BankApiResponse> transferMoney(@RequestParam @NotNull String sourceAccount,@RequestParam @NotNull String targetAccount,@RequestParam @NotNull int amount) throws BankException {
        LOG.info("accountDetailsForCustomer Api request params :{}", sourceAccount);
        String deleteSuccess = employeeService.transferMoney(sourceAccount,sourceAccount,sourceAccount);//.....
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,deleteSuccess));
    }

    @PostMapping("accountStatement")
    public ResponseEntity<BankApiResponse> accountStatement(@RequestParam @NotNull String account,@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")@PathVariable("startTime")@NotNull Date startTime,@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")@PathVariable("endTime")@NotNull Date endTime) throws BankException {
        LOG.info("accountDetailsForCustomer Api request params :{}", account);
        String deleteSuccess = employeeService.accountStatement(account,startTime,endTime);//.....
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,deleteSuccess));
    }

    @PostMapping("accountStatement")
    public ResponseEntity<BankApiResponse> calculateInterest(@RequestParam @NotNull String account, @RequestParam @NotNull float interestRate) throws BankException {
        LOG.info("accountDetailsForCustomer Api request params :{}", account);
        String calculateInterest = employeeService.calculateInterest(account,interestRate);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,calculateInterest));
    }
}
