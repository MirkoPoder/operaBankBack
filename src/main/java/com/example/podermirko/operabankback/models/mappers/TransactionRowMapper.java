package com.example.podermirko.operabankback.models.mappers;

import com.example.podermirko.operabankback.models.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet resultSet, int i) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getLong("id"));
        transaction.setAccountNumberFrom(resultSet.getString("fromaccount_number"));
        transaction.setAccountNumberTo(resultSet.getString("toaccount_number"));
        transaction.setAmount(resultSet.getBigDecimal("amount"));
        transaction.setDescription(resultSet.getString("description"));
        return transaction;
    }
}
