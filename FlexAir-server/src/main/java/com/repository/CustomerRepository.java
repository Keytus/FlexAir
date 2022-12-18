package com.repository;

import com.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByCustomerID(Integer id);
    List<Customer> findByLogin(String login);
    List<Customer> findByEmail(String email);
    List<Customer> findByCustomerType(String customerType);
}
