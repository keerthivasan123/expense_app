package com.guvi.expense_app.controller;

import com.guvi.expense_app.dto.LoginDto;
import com.guvi.expense_app.dto.TokenDto;
import com.guvi.expense_app.dto.UserDto;
import com.guvi.expense_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public void registerUser(@Valid @RequestBody UserDto userDto) {
        userService.registerUser(userDto);
    }

    @PostMapping("/login")
    public TokenDto loginUser(@Valid @RequestBody LoginDto loginDto) {
        return userService.loginUser(loginDto);
    }
}
