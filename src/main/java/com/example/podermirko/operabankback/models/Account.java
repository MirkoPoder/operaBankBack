package com.example.podermirko.operabankback.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class Account {

    private Long id;
    private String firstname;
    private String lastname;
    private String accountNumber;
    private BigDecimal balance;
    private String bank;
    private String username;
    private BigDecimal amount;
}
