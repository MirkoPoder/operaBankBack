package com.example.podermirko.operabankback.service;

import com.example.podermirko.operabankback.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void createUser(String username, String password) {
        accountRepository.createUser(username, password);
    }

    public void createAccount(String firstname, String lastname, String accountNumber, BigDecimal balance, String bank, String username) {
        accountRepository.createAccount(firstname, lastname, accountNumber, balance, bank, username);
    }

    public void depositMoney(String accountNumber, BigDecimal amount) {
        accountRepository.updateBalanceAfterDeposit(accountNumber, amount);
    }

    public void withdrawMoney(String accountNumber, BigDecimal amount) {
        BigDecimal balance = accountRepository.getBalance(accountNumber);
        if (balance.compareTo(amount) >= 0) {
            accountRepository.updateBalanceAfterWithdrawal(accountNumber, amount);
        }
    }

    public void transferMoney(String accountNumberFrom, String accountNumberTo, BigDecimal amount, String description) {
        BigDecimal fromAccountBalance = accountRepository.getBalance(accountNumberFrom);
        if (fromAccountBalance.compareTo(amount) >= 0) {
            BigDecimal toAccountBalance = accountRepository.getBalance(accountNumberTo);
            fromAccountBalance = fromAccountBalance.subtract(amount);
            toAccountBalance = toAccountBalance.add(amount);
            accountRepository.updateSendersBalance(accountNumberFrom, fromAccountBalance);
            accountRepository.updateReceiversBalance(accountNumberTo, toAccountBalance);
            accountRepository.updateTransactions(accountNumberFrom, accountNumberTo, amount, description);
        }
    }
}
