package com.example.restaurant_management.model;

import jakarta.annotation.security.DenyAll;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String username;
    private String password;
}
