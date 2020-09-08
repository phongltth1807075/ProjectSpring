package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.dto.AccountDTO;
import project.model.Accounts;
import project.model.Roles;
import project.model.rest.RESTPagination;
import project.model.rest.RESTResponse;
import project.model.specification.AccountSpecification;
import project.model.specification.ProductSpecification;
import project.model.specification.SearchCriteria;
import project.service.AccountService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> getList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit,
            @RequestParam(value = "role", required = false) Optional<Integer> role,
            @RequestParam(value = "gender", required = false) Optional<Integer> gender
    ) {
        Specification specification = Specification.where(null);
        if (keyword != null && keyword.length() > 0) {
            specification = specification
                    .and(new ProductSpecification(new SearchCriteria("accountName", "=", keyword)))
                    .and(new ProductSpecification(new SearchCriteria("phoneNumber", "=", keyword)))
                    .or(new ProductSpecification(new SearchCriteria("email", "=", keyword)));
        }
        if (role.isPresent()) {
            specification = specification.and(new AccountSpecification(new SearchCriteria("roleId", "=", role.get())));
        }
        if (gender.isPresent()) {
            specification = specification.and(new AccountSpecification(new SearchCriteria("gender", "=", gender.get())));
        }
        Page<Accounts> AccountPage = accountService.getList(specification, page, limit);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(AccountPage.getContent().stream().map(x -> new AccountDTO(x)).collect(Collectors.toList()))
                .setPagination(new RESTPagination(page, limit, AccountPage.getTotalPages(), AccountPage.getTotalElements()))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody Accounts accounts) {
        Accounts saveAccount = accountService.create(accounts);
        if (saveAccount != null) {
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("Action Success")
                    .addData(saveAccount)
                    .build(),
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Optional<Accounts> exitAccount = accountService.getById(id);
        if (exitAccount.isPresent()) {
            accountService.delete(exitAccount.get());
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Simple Success")
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }


    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<Accounts> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        Accounts accounts = accountService.login(email, password);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id) {
        Optional<Accounts> accounts = accountService.getById(id);
        if (accounts.isPresent()) {
            Accounts accounts1 = accounts.get();
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Success")
                    .addData(accounts1)
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Accounts accounts) {
        Optional<Accounts> accountsUpdate = accountService.getById(id);
        if (accountsUpdate.isPresent()) {
            Accounts newAccount = accountsUpdate.get();
            newAccount.setAccountName(accounts.getAccountName());
            newAccount.setAddress(accounts.getAddress());
            newAccount.setBirthday(accounts.getBirthday());
            newAccount.setEmail(accounts.getEmail());
            newAccount.setGender(accounts.getGender());
            newAccount.setPassword(accounts.getPassword());
            newAccount.setPhoneNumber(accounts.getPhoneNumber());
            newAccount.setStatus(accounts.getStatus());
            accountService.update(newAccount);
            return new ResponseEntity<>(new RESTResponse.Success()
                    .setStatus(HttpStatus.OK.value())
                    .setMessage("Success")
                    .addData(accountService.update(newAccount))
                    .build(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new RESTResponse.SimpleError()
                .setCode(HttpStatus.NOT_FOUND.value())
                .setMessage("Not found")
                .build(),
                HttpStatus.NOT_FOUND);
    }
}
