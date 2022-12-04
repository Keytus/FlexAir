package com.service;

import com.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    public boolean isCustomerExist(String login);
    public boolean checkPassword(String login, String password);
    public List<Customer> getCustomers();
    public Customer createCustomer(Customer customer);
}
