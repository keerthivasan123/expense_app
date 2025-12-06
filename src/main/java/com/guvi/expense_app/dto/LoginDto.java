package com.guvi.expense_app.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {
    private String username;
    private String password;
}
