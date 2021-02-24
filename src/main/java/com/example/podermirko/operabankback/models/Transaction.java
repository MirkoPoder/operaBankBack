package com.example.podermirko.operabankback.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class Transaction {

    private Long id;
    private String accountNumberFrom;
    private String accountNumberTo;
    private BigDecimal amount;
    private String description;
}
