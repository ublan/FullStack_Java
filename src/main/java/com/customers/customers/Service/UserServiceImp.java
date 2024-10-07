package com.customers.customers.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customers.customers.Entities.User;
import com.customers.customers.Repository.UserRepository;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    public static final String SECRET_KEY = "asdqweqgh345v";

    @Autowired
    private UserRepository repository;

        public User getUser(Integer id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        Iterable<User> users = repository.findAll();
        for (User user:users) {
            list.add(user);
        }
        return list;
    }

    public void removeUser(Integer id) {
        repository.deleteById(id);
    }

    public void addUser(User user) { //Hash password + SECRET_KEY con sha256 libreria guava de google

            String hashPassword = Hashing.sha256()
            .hashString(user.getPassword() + SECRET_KEY, StandardCharsets.UTF_8).toString();

            user.setPassword(hashPassword);
            repository.save(user);
    }

    public void updateUser(Integer id, User updateUser) {
        updateUser.setId(id);
        repository.save(updateUser);
    }


    public List<User> searchUser(String email, String address) {
        return repository.findByEmailOrAddress(email, address);
    }

}