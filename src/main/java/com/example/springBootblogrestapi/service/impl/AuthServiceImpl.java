package com.example.springBootblogrestapi.service.impl;

import com.example.springBootblogrestapi.entity.Role;
import com.example.springBootblogrestapi.entity.User;
import com.example.springBootblogrestapi.exception.BlogAPIException;
import com.example.springBootblogrestapi.payload.LoginDto;
import com.example.springBootblogrestapi.payload.RegisterDto;
import com.example.springBootblogrestapi.repositary.RoleRepository;
import com.example.springBootblogrestapi.repositary.UserRepository;
import com.example.springBootblogrestapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {



    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager ,
                           UserRepository userRepository ,
                           RoleRepository roleRepository ,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {

       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail() , loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
       return "user logged in successfully !";
    }

    @Override
    public String register(RegisterDto registerDto) {

        //check for users exisists in database

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST , "Email already exsists");
        }
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST , "user name already exsists");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return "user registered successfully !";
    }
}
