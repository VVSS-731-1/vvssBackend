package com.example.demo.service.services;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.dto.CustomerDto;
import com.example.demo.service.dto.DtoMapping;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private DtoMapping dtoMapping;

    private Customer customer;
    private CustomerDto customerDto;

    @Test
    void findAllCustomers() {
        when(customerRepository.findAll()).thenReturn(getCustomerList());

        List<Customer> customers = this.customerService.findAllCustomers();
        Assert.assertEquals(getCustomerList(), customers);
        Assert.assertEquals(customers.size(), 2);
        Assert.assertEquals(customers.get(0), getCustomer());
    }

    @Test
    void findById() {
        customer = getCustomer();
        customerDto = getCustomerDTO();

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(dtoMapping.customerToDto(any())).thenReturn(customerDto);

        CustomerDto returnedCust = this.customerService.findById(1);
        Assert.assertEquals(returnedCust.getId(), customerDto.getId());
        Assert.assertEquals(returnedCust.getName(), customerDto.getName());
        Assert.assertEquals(returnedCust.getStatus(), customerDto.getStatus());
    }

    @Test
    void findByName() {
    }

    @Test
    void save() {
    }

    @Test
    void deactivateCustomer() {
    }

    private Customer getCustomer() {
        this.customer = new Customer();
        this.customer.setId(1);
        this.customer.setName("Pop AnaMaria");
        this.customer.setStatus(true);

        return this.customer;
    }

    private CustomerDto getCustomerDTO() {
        this.customerDto = new CustomerDto();
        this.customerDto.setId(1);
        this.customerDto.setName("Pop AnaMaria");
        this.customerDto.setStatus(true);

        return this.customerDto;
    }

    private List<Customer> getCustomerList() {
        List<Customer> list = new ArrayList<>();
        list.add(getCustomer());
        Customer customer = getCustomer();
        customer.setId(2);
        list.add(customer);

        return list;
    }
}