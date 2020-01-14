package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.dto.CustomerDTO;
import com.example.demo.service.dto.DtoMapping;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * Test class for CustomerService.
 *
 * @author Miruna Dinu
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @Test
    void save_returnsCustomer_withNotNullCustomer() {
        CustomerDTO customerDto = DtoMapping.customerToDto(getCustomer());
        customer = getCustomer();

        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerDTO customerTest = customerService.save(customerDto);

        Assert.assertEquals(customerDto, customerTest);
    }

    @Test
    void deactivateCustomer_returnsCustomerWithChangedStatus_withNotNullCustomer() {
        CustomerDTO customerDto = DtoMapping.customerToDto(getCustomer());
        customer = getCustomer();

        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerRepository.findById(customerDto.getId())).thenReturn(java.util.Optional.ofNullable(customer));

        CustomerDTO customerTest = customerService.deactivateCustomer(customerDto);

        Assert.assertEquals(customerDto, customerTest);
        Assert.assertFalse(customerDto.getStatus());
    }

    @Test
    void deactivateConsultingLevel_noChanges_withNotExistingCustomer() {
        CustomerDTO customerDto = DtoMapping.customerToDto(getCustomer());

        when(customerRepository.findById(customerDto.getId())).thenReturn(Optional.empty());

        CustomerDTO customerTest = customerService.deactivateCustomer(customerDto);

        Assert.assertEquals(customerDto, customerTest);
        Assert.assertTrue(customerDto.getStatus());
    }

    private Customer getCustomer() {
        customer = new Customer();
        customer.setId(1);
        customer.setName("Ana");
        customer.setStatus(true);

        return customer;
    }
}