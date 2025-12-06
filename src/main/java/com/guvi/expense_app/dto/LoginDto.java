package com.guvi.expense_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
public class LoginDto {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
