package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void save(Customer customer)
    {
        customerRepository.save(customer);
    }

    public List<Customer> findAll(){
        return (List<Customer>) customerRepository.findAll();
    }

}
