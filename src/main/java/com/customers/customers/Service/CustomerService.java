package com.customers.customers.Service;

import java.util.List;

import com.customers.customers.Entities.Customer;

public interface CustomerService {

    Customer getCustomer(Integer id);
    List<Customer> getAllCustomers();
    void removeCustomer(Integer id);
    void addCustomer(Customer customer);
    void updateCustomer(Integer id, Customer updateCustomer);
    List<Customer> searchCustomer(String email, String address);

}