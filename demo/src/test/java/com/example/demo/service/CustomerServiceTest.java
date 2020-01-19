package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.service.dto.DtoMapping;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Autowired
    private DtoMapping dtoMapping;

    @Test
    void save() {

        Customer customer = new Customer();
        customer.setName("Customer1");
        customer.setStatus(false);


        customerService.save(dtoMapping.customerToDto(customer));
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer() {{
            setId(1);
            setName("Diamante SRI");
            setStatus(true);
        }});
        customers.add(customer);

//        List<Customer> resultFromDB = customerService.findAll();
//
//        assertThat(resultFromDB.size()).isEqualTo(8);

    }
}