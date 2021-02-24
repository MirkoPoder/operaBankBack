package com.example.podermirko.operabankback.repository;

import com.example.podermirko.operabankback.models.Account;
import com.example.podermirko.operabankback.models.Transaction;
import com.example.podermirko.operabankback.models.User;
import com.example.podermirko.operabankback.models.mappers.AccountRowMapper;
import com.example.podermirko.operabankback.models.mappers.TransactionRowMapper;
import com.example.podermirko.operabankback.models.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Account> getAllAccounts() {
        String sql = "Select * from account";
        Map<String, Object> paramMap = new HashMap<>();
        List<Account> accounts = jdbcTemplate.query(sql, paramMap, new AccountRowMapper());
        return accounts;
    }

    public List<Account> getAccountsByUsername(String username) {
        Integer id = getUserIdByUsername(username);
        String sql = "Select * from account where user_id= :id";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        List<Account> accounts = jdbcTemplate.query(sql, paramMap, new AccountRowMapper());
        return accounts;
    }

    public Account getAccountByAccountNumber(String accountNumber, String username) {
        Integer user_id = getUserIdByUsername(username);
        String sql = "Select * from account where user_id= :user_id and account_number= :accountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", accountNumber);
        paramMap.put("user_id", user_id);
        Account accounts = jdbcTemplate.queryForObject(sql, paramMap, new AccountRowMapper());
        return accounts;
    }

    public List<User> getAllUsers() {
        String sql = "Select * from users";
        Map<String, Object> paramMap = new HashMap<>();
        List<User> user = jdbcTemplate.query(sql, paramMap, new UserRowMapper());
        return user;
    }

    public void createUser(String username, String password) {
        String sql = "Insert into users (username, password) values (:username, :password)";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", username);
        paramMap.put("password", password);
        jdbcTemplate.update(sql, paramMap);
    }

    public void createAccount(String firstname, String lastname, String accountNumber, BigDecimal balance, String bank, String username) {
        Integer user_id = getUserIdByUsername(username);
        String sql = "Insert into account (firstname, lastname, account_number, balance, bank, user_id) values (:firstname, :lastname, :accountNumber, :balance, :bank, :user_id)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("firstname", firstname);
        paramMap.put("lastname", lastname);
        paramMap.put("accountNumber", accountNumber);
        paramMap.put("balance", balance);
        paramMap.put("bank", bank);
        paramMap.put("user_id", user_id);
        jdbcTemplate.update(sql, paramMap);
    }

    public String getUserPassword(String username) {
        String sql = "Select password from users where username= :username";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", username);
        return jdbcTemplate.queryForObject(sql, paramMap, String.class);
    }

    public Integer getUserIdByUsername(String username) {
        String sql = "Select id from users where username= :username";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        return jdbcTemplate.queryForObject(sql, paramMap, Integer.class);
    }

    public void updateBalanceAfterDeposit(String accountNumber, BigDecimal amount) {
        String descriptionDeposit = "Money deposit";
        String sql = "Update account set balance = balance + :amount where account_number= :accountNumber";
        String sql1 = "insert into transactions (toaccount_number, amount, description) values (:accountNumber, " + ":amount, :descriptionDeposit)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", accountNumber);
        paramMap.put("amount", amount);
        paramMap.put("descriptionDeposit", descriptionDeposit);
        jdbcTemplate.update(sql, paramMap);
        jdbcTemplate.update(sql1, paramMap);
    }

    public void updateBalanceAfterWithdrawal(String accountNumber, BigDecimal amount) {
        String descriptionWithdraw = "Money withdrawal from ATM";
        String sql = "Update account set balance = balance - :amount where account_number= :accountNumber";
        String sql1 = "insert into transactions (fromaccount_number, amount, description) values (:accountNumber, :amount * -1, :descriptionWithdraw)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", accountNumber);
        paramMap.put("amount", amount);
        paramMap.put("descriptionWithdraw", descriptionWithdraw);
        jdbcTemplate.update(sql, paramMap);
        jdbcTemplate.update(sql1, paramMap);
    }

    public BigDecimal getBalance(String accountNumber) {
        String sql = "select balance from account where account_number= :accountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", accountNumber);
        return jdbcTemplate.queryForObject(sql, paramMap, BigDecimal.class);
    }

    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        String sql = "select * from transactions where fromaccount_number= :accountNumber or toaccount_number= :accountNumber";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumber", accountNumber);
        List<Transaction> transactions = jdbcTemplate.query(sql, paramMap, new TransactionRowMapper());
        return transactions;
    }

    public void updateSendersBalance(String accountNumberFrom, BigDecimal fromAccountBalance) {
        String sql = "update account set balance= :fromAccountBalance where account_number= :accountNumberFrom";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("fromAccountBalance", fromAccountBalance);
        paramMap.put("accountNumberFrom", accountNumberFrom);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateReceiversBalance(String accountNumberTo, BigDecimal toAccountBalance) {
        String sql = "update account set balance= :toAccountBalance where account_number= :accountNumberTo";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("toAccountBalance", toAccountBalance);
        paramMap.put("accountNumberTo", accountNumberTo);
        jdbcTemplate.update(sql, paramMap);
    }

    public void updateTransactions(String accountNumberFrom, String accountNumberTo, BigDecimal amount, String description) {
        String sql = "insert into transactions (fromaccount_number, toaccount_number, amount, description) values (:accountNumberFrom, :accountNumberTo, :amount, :description)";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accountNumberFrom", accountNumberFrom);
        paramMap.put("accountNumberTo", accountNumberTo);
        paramMap.put("amount", amount);
        paramMap.put("description", description);
        jdbcTemplate.update(sql, paramMap);
    }
}
