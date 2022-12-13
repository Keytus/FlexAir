package com.service;

import com.model.entity.Customer;
import com.repository.CustomerRepository;
import com.repository.PassportDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private PassportDataRepository passportDataRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public boolean isCustomerExist(String login) {
        List<Customer> result = customerRepository.findByLogin(login);
        return !result.isEmpty();
    }

    @Override
    public boolean checkPassword(String login, String password) {
        Customer customer = customerRepository.findByLogin(login).get(0);
        return customer.getCustomerPassword().equals(password);
    }

    @Override
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(Customer customer){

        if (customer.getPassportData() != null){
            passportDataRepository.save(customer.getPassportData());
        }

        return customerRepository.save(customer);
    }
    @Override
    public Customer getCustomerByID(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));
        return customer;
    }
    @Override
    public Integer getCustomerIDByLogin(String login){
        return customerRepository.findByLogin(login).get(0).getCustomerID();
    }
}
