package com.customers.customers.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.customers.customers.Entities.User;
import com.customers.customers.Service.AuthService;
import com.customers.customers.Utils.JwtUtil;
import com.customers.customers.dto.RequestLogin;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @PostMapping("/auth/login")
    public String login(@RequestBody RequestLogin request) {
        User user = authService.login(request.getEmail(), request.getPassword()); 
        String token = JwtUtil.generateToken(user);
        return token;  
    }
}
