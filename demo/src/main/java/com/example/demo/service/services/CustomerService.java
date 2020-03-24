package com.example.demo.service.services;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.dto.CustomerDto;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.IndustryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DtoMapping dtoMapping;

    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream().map(dtoMapping::customerToDto).collect(Collectors.toList());
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public CustomerDto findById(Integer id){
        if (customerRepository.findById(id).isPresent())
            return dtoMapping.customerToDto(customerRepository.findById(id).get());
        return null;
    }

    public CustomerDto findByName(String name){
        Optional<CustomerDto> optInd = findAll()
                .stream()
                .filter(u -> u.getName().equals(name))
                .findFirst();
        return optInd.get();
    }

    //todo admin only
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = dtoMapping.dtoToCustomer(customerDto);
        customer = customerRepository.save(customer);
        return dtoMapping.customerToDto(customer);
    }

    //todo admin only
    public void deactivateCustomer(CustomerDto customerDto) {
        customerRepository.findById(customerDto.getId()).ifPresent(customer -> {
            customer.setStatus(false);
            customerRepository.save(customer);
        });
    }

}
