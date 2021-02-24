package com.example.podermirko.operabankback.controllers;

import com.example.podermirko.operabankback.models.User;
import com.example.podermirko.operabankback.repository.AccountRepository;
import com.example.podermirko.operabankback.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private final AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostMapping("createUser")
    public void createUser(@RequestBody User request) {
        accountService.createUser(request.getUsername(), passwordEncoder.encode(request.getPassword()));
    }

    @GetMapping("users")
    public List<User> employees() {
        return accountRepository.getAllUsers();
    }
}
