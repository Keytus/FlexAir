package com.controller;

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
    public ResponseEntity<?> isCustomerExist(@RequestParam String login){
        if (customerService.isCustomerExist(login)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestParam String login, @RequestParam String password){
        if (customerService.isCustomerExist(login)){
            if (customerService.checkPassword(login, password)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/sing_up")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
