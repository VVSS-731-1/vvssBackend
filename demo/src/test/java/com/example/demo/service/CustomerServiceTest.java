package com.example.demo.service;

import com.example.demo.model.Customer;
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
    private CustomerService customerService;

    @Test
    void save() {

        Customer customer = new Customer(){{
            setName("Customer1");
            setStatus(false);
        }};

        customerService.save(customer);
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(){{
            setId(1);
            setName("MHP Consulting Romania");
            setStatus(true);
        }});
        customers.add(customer);

        List<Customer> resultFromDB = customerService.findAll();

        assertThat(resultFromDB.size()).isEqualTo(2);

        assertThat(resultFromDB.get(1))
                .isEqualToIgnoringGivenFields(customer, "id");

    }
}