package com.customers.customers.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customers.customers.Entities.User;
import com.customers.customers.Repository.UserRepository;
import com.google.common.hash.Hashing;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;
    private static final String SECRET_KEY = "3lB4ut4!";

    @Override
    public User login(String email, String password){
        String hashPassword = Hashing.sha256()
                .hashString(password + SECRET_KEY, StandardCharsets.UTF_8)
                .toString();

        List<User> result = userRepository.findByEmailAndPassword(email, hashPassword);
        System.out.println(hashPassword);
        System.out.println(email);

        if(result.isEmpty()){
            return null;
        } else {
            return result.get(0);
        }
    }
}
