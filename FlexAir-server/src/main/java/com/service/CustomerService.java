package com.service;

import com.model.entity.Customer;
import com.model.entity.PassportData;

import java.util.List;

public interface CustomerService {
    public boolean isCustomerExist(String login);
    public boolean isCustomerExistByID(Integer id);
    public boolean checkPassword(String login, String password);
    public List<Customer> getCustomers();
    public Customer createCustomer(Customer customer);
    public Customer getCustomerByID(Integer id);
    public Integer getCustomerIDByLogin(String login);
    public PassportData getPassportDataByCustomerID(Integer id);
    public Customer setPassportDataByCustomerID(Integer id, PassportData passportData);
    public Customer resetPassword(String email);
}
