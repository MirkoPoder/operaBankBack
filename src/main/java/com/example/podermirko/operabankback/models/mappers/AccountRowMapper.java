package com.example.podermirko.operabankback.models.mappers;

import com.example.podermirko.operabankback.models.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getLong("id"));
        account.setFirstname(resultSet.getString("firstname"));
        account.setLastname(resultSet.getString("lastname"));
        account.setAccountNumber(resultSet.getString("account_number"));
        account.setBalance(resultSet.getBigDecimal("balance"));
        account.setBank((resultSet.getString("bank")));
        account.setUsername(resultSet.getString("user_id"));
        return account;
    }
}
