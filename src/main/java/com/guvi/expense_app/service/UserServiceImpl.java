package com.guvi.expense_app.service;

import com.guvi.expense_app.dto.LoginDto;
import com.guvi.expense_app.dto.TokenDto;
import com.guvi.expense_app.dto.UserDto;
import com.guvi.expense_app.model.User;
import com.guvi.expense_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public User registerUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getUsername());
        user.setHashPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }

    @Override
    public TokenDto loginUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        return new TokenDto(token);
    }
}
