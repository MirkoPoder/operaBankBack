package com.example.podermirko.operabankback.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    private Long id;
    private String username;
    private String password;
}
