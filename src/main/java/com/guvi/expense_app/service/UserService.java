package com.guvi.expense_app.service;

import com.guvi.expense_app.dto.LoginDto;
import com.guvi.expense_app.dto.TokenDto;
import com.guvi.expense_app.dto.UserDto;
import com.guvi.expense_app.model.User;

public interface UserService {
    User registerUser(UserDto userDto);
    TokenDto loginUser(LoginDto loginDto);
}
