package com.controller;

import com.model.Message;
import com.model.entity.Customer;
import com.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        super();
        this.customerService = customerService;
    }
    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    @GetMapping("/is_exist")
    public ResponseEntity<Message> isCustomerExist(@RequestParam String login){
        if (customerService.isCustomerExist(login)){
            return new ResponseEntity<>(new Message("success"),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Message("fail"),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/login")
    public ResponseEntity<Message> loginCustomer(@RequestParam String login, @RequestParam String password){
        if (customerService.isCustomerExist(login)){
            if (customerService.checkPassword(login, password)){
                return new ResponseEntity<>(new Message("success"), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new Message("fail"),HttpStatus.NOT_FOUND);
            }
        }
        else {
            return new ResponseEntity<>(new Message("fail"),HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/sing_up")
    public ResponseEntity<Message> createCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
        return new ResponseEntity<>(new Message("success"), HttpStatus.OK);
    }
}
