package com.quan.coursepeerreview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quan.coursepeerreview.Entity.Account;
import com.quan.coursepeerreview.Entity.Role;
import com.quan.coursepeerreview.Entity.Request.AccountRequest;
import com.quan.coursepeerreview.Entity.Request.ChangePassword;
import com.quan.coursepeerreview.Entity.Request.StudentRegister;
import com.quan.coursepeerreview.Entity.Request.TeacherRegister;
import com.quan.coursepeerreview.Repository.AccountRepos;
import com.quan.coursepeerreview.Service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    /**
     * using constructor dependency injection
     * Compile-Time Safety :because avoid of nullPointerException, if dependency isn't available spring boot raise error at application startup
     * Testability: good practice, we can easily provide a mock
     */
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // remove "all" because better RESTful API design principles
    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
    }


    // 1 - remove "role" because better RESTful API design principles
    // 2 - i prefer use string in pathVariable because in this case spring try convert string value in url to enum value
    //if string doesn't match might run into runtime exception
    @GetMapping("/{role}")
    public ResponseEntity<List<Account>> getAccountsByRole(@PathVariable Role role) {
        return new ResponseEntity<>(accountService.getAccountsByRole(role), HttpStatus.OK);
    }


    // remove "id" because better RESTful API design principles
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
    }

    // 1 - first word of method is capitalized we use CamelCase for define method and name of method isn't clear
    // 2- validation for StudentRegister(DTO)
    @PostMapping("/register-student")
    public ResponseEntity<HttpStatus> registerStudent(@RequestBody StudentRegister studentRegister) {
        accountService.saveAccount(studentRegister);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/register-teacher")
    public ResponseEntity<HttpStatus> registerTeacher(@RequestBody TeacherRegister teacherRegister) {
        accountService.saveTeacher(teacherRegister);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/change-password")
    public ResponseEntity<Account> changePassword(@RequestBody ChangePassword changePassword) {
        return new ResponseEntity<>(accountService.updateAccount( changePassword), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
