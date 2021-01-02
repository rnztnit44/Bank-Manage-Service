package com.bank.controller;

import com.bank.bean.Employee;
import com.bank.constant.ApiConstant;
import com.bank.exception.BankException;
import com.bank.response.BankApiResponse;
import com.bank.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/admin/")
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);


    @Autowired
    private AdminService adminService;

    @PostMapping("addEmployees")
    public ResponseEntity<BankApiResponse> addEmployees(@RequestBody @NotNull List<Employee> employeeList) throws BankException {
        LOG.info("addEmployees Api request params :{}", employeeList);
        String addSuccess = adminService.addEmployees(employeeList);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,addSuccess));
    }

    @PutMapping("deleteEmployees")
    public ResponseEntity<BankApiResponse> deleteEmployees(@RequestBody @NotNull List<Employee> employeeList) throws BankException {
        LOG.info("deleteEmployees Api request params :{}", employeeList);
        String deleteSuccess = adminService.deleteEmployees(employeeList);
        return ResponseEntity.ok().body(new BankApiResponse(ApiConstant.SUCCESS_CODE,deleteSuccess));
    }
}
