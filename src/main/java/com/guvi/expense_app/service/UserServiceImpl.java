package com.guvi.expense_app.service;

import com.guvi.expense_app.dto.LoginDto;
import com.guvi.expense_app.dto.TokenDto;
import com.guvi.expense_app.dto.UserDto;
import com.guvi.expense_app.exception.UserAlreadyExistsException;
import com.guvi.expense_app.model.User;
import com.guvi.expense_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

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
        if (userRepository.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username '" + userDto.getUsername() + "' is already taken.");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setHashPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("USER");
        return userRepository.save(user);
    }

    @Override
    public TokenDto loginUser(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password."));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getHashPassword())) {
            throw new BadCredentialsException("Invalid username or password.");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String token = jwtService.generateToken(userDetails);

        return new TokenDto(token);
    }
}
