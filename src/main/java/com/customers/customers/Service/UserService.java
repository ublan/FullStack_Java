package com.customers.customers.Service;

import java.util.List;

import com.customers.customers.Entities.User;

public interface UserService {

    User getUser(Integer id);
    List<User> getAllUsers();
    void removeUser(Integer id);
    void addUser(User user);
    void updateUser(Integer id, User updateUser);
    List<User> searchUser(String email, String address);

}
