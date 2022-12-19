package com.service;

import com.model.entity.Customer;
import com.model.entity.PassportData;
import com.repository.CustomerRepository;
import com.repository.PassportDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private PassportDataRepository passportDataRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public boolean isCustomerExistByLogin(String login) {
        List<Customer> result = customerRepository.findByLogin(login);
        return !result.isEmpty();
    }

    @Override
    public boolean isCustomerExistByID(Integer id) {
        List<Customer> result = customerRepository.findByCustomerID(id);
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
    @Override
    public PassportData getPassportDataByCustomerID(Integer id){
        PassportData passportData = getCustomerByID(id).getPassportData();
        if (passportData == null){
            throw new ResourceNotFoundException("PassportData is empty with customerID id : " + id);
        }
        else return passportData;
    }
    @Override
    public Customer setPassportDataByCustomerID(Integer id, PassportData passportData){
        Customer customer = getCustomerByID(id);
        PassportData resultPassportData = customer.getPassportData();
        if (resultPassportData == null){
            resultPassportData = new PassportData();
        }
        resultPassportData.setDateOfIssue(passportData.getDateOfIssue());
        resultPassportData.setDateOfExpery(passportData.getDateOfExpery());
        resultPassportData.setSurname(passportData.getSurname());
        resultPassportData.setGivenName(passportData.getGivenName());
        resultPassportData.setDateOfBirth(passportData.getDateOfBirth());
        resultPassportData.setCountry(passportData.getCountry());
        resultPassportData.setSex(passportData.getSex());
        resultPassportData.setAuthority(resultPassportData.getAuthority());
        resultPassportData.setIdNumber(passportData.getIdNumber());

        passportDataRepository.save(resultPassportData);
        customer.setPassportData(resultPassportData);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Customer resetPassword(String email){
        Customer customer = customerRepository.findByEmail(email).get(0);

        if (customer.getEmail() == null){
            throw new ResourceNotFoundException("Where no customers with email : " + email);
        }

        int leftLimit = 65;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer;
        buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String newPas = buffer.toString();

        customer.setCustomerPassword(newPas);
        customerRepository.save(customer);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(customer.getEmail());
        mailMessage.setSubject("Reset password");
        mailMessage.setText("New password: " + newPas);

        javaMailSender.send(mailMessage);

        return customer;
    }

    @Override
    public void deleteCustomer(Integer id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));
        customerRepository.delete(customer);
    }

    @Override
    public Customer updateCustomer(Integer id, Customer customerData){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id :" + id));
        customer.setLogin(customerData.getLogin());
        customer.setEmail(customerData.getEmail());
        customer.setCustomerType(customerData.getCustomerType());
        customer.setCustomerPassword(customerData.getCustomerPassword());

        return customerRepository.save(customer);
    }
    @Override
    public List<Customer> getEmployees(){
        List<Customer> customers = customerRepository.findAll();
        List<Customer> resultSet = new ArrayList<>();
        for(Customer customer : customers){
            if (!customer.getCustomerType().equals("user")){
                resultSet.add(customer);
            }
        }
        return resultSet;
    }
}
