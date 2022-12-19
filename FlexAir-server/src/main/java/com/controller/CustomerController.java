package com.controller;

import com.model.Message;
import com.model.entity.Customer;
import com.model.entity.PassportData;
import com.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }
    @GetMapping("/{id}")
    public Customer getCustomerByID(@PathVariable Integer id){
        return customerService.getCustomerByID(id);
    }
    @PutMapping("/{id}")
    public Customer updateCustomerByID(@PathVariable Integer id, @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Message> deleteCustomerByID(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(new Message("success", id),HttpStatus.OK);
    }
    @GetMapping("/{id}/is_exist")
    public ResponseEntity<Message> isCustomerExistByID(@PathVariable Integer id){
        if (customerService.isCustomerExistByID(id)){
            return new ResponseEntity<>(new Message("success", null),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Message("fail", null),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/is_exist")
    public ResponseEntity<Message> isCustomerExistByLogin(@RequestParam String login){
        if (customerService.isCustomerExistByLogin(login)){
            return new ResponseEntity<>(new Message("success", null),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(new Message("fail", null),HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/login")
    public ResponseEntity<Message> loginCustomer(@RequestParam String login, @RequestParam String password){
        if (customerService.isCustomerExistByLogin(login)){
            if (customerService.checkPassword(login, password)){
                return new ResponseEntity<>(new Message("success", customerService.getCustomerIDByLogin(login)), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new Message("fail", null),HttpStatus.NOT_FOUND);
            }
        }
        else {
            return new ResponseEntity<>(new Message("fail", null),HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/sing_up")
    public ResponseEntity<Message> createCustomer(@RequestBody Customer customer){
        Customer resultCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(new Message("success", resultCustomer.getCustomerID()), HttpStatus.OK);
    }
    @GetMapping("/{id}/passportData")
    public PassportData getPassportDataByCustomerID(@PathVariable Integer id){
        return customerService.getPassportDataByCustomerID(id);
    }

    @PutMapping("/{id}/passportData")
    public ResponseEntity<Message> setPassportDataByCustomerID(@PathVariable Integer id, @RequestBody PassportData passportData){
        Customer customer = customerService.setPassportDataByCustomerID(id, passportData);
        return new ResponseEntity<>(new Message("success", customer.getCustomerID()), HttpStatus.OK);
    }
    @GetMapping("/reset_pas")
    public ResponseEntity<Message> resetPassword(@RequestParam String email){
        Customer customer = customerService.resetPassword(email);
        return new ResponseEntity<>(new Message("success", customer.getCustomerID()), HttpStatus.OK);
    }
}
