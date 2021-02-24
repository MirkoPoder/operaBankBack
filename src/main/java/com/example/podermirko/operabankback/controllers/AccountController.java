package com.example.podermirko.operabankback.controllers;

import com.example.podermirko.operabankback.models.Account;
import com.example.podermirko.operabankback.models.Transaction;
import com.example.podermirko.operabankback.repository.AccountRepository;
import com.example.podermirko.operabankback.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("accounts")
    public List<Account> allAccounts() {
        return accountRepository.getAllAccounts();
    }

    @GetMapping("accounts/{username}/{accountNumber}")
    public Account accountByAccountNumber(@PathVariable String accountNumber, @PathVariable String username) {
        return accountRepository.getAccountByAccountNumber(accountNumber, username);
    }

    @GetMapping("accounts/{username}")
    public List<Account> accountsByUsername(@PathVariable String username) {
        return accountRepository.getAccountsByUsername(username);
    }

    @PostMapping("createAccount")
    public void createAccount(@RequestBody Account request) {
        accountService.createAccount(request.getFirstname(), request.getLastname(), request.getAccountNumber(), request.getBalance(), request.getBank(), request.getUsername());
    }

    @PutMapping("deposit")
    public void depositMoney(@RequestBody Account request) {
        accountService.depositMoney(request.getAccountNumber(), request.getAmount());
    }

    @PutMapping("withdraw")
    public void withdrawMoney(@RequestBody Account request) {
        accountService.withdrawMoney(request.getAccountNumber(), request.getAmount());
    }

    @GetMapping("transactions/{accountNumber}")
    public List<Transaction> transactionsByAccountNumber(@PathVariable String accountNumber) {
        return accountRepository.getTransactionsByAccountNumber(accountNumber);
    }

    @PutMapping("transfer")
    public void transferMoney(@RequestBody Transaction request) {
        accountService.transferMoney(
                request.getAccountNumberFrom(),
                request.getAccountNumberTo(),
                request.getAmount(),
                request.getDescription()
        );
    }
}
