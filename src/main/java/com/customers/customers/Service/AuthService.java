package com.customers.customers.Service;

import com.customers.customers.Entities.User;

public interface AuthService {
    User login(String email, String password);
}
