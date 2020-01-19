package com.example.demo.controller;

import com.example.demo.service.CustomerService;
import com.example.demo.service.dto.CustomerDto;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getall")
    public ResponseEntity<String> getAll() {
        Gson gson = new Gson();
        String response = gson.toJson(customerService.findAll());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Gson gson = new Gson();
        if (customerService.findById(id) != null) {
            String response = gson.toJson(customerService.findById(id));
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("No Customer with given id");
    }

    @PostMapping("/add") //optional consumes = "application/json" produces = "application/json"
    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto response = customerService.save(customerDto);
        if (response != null) {
            return ResponseEntity.ok().body("Customer saved!");
        }
        return ResponseEntity.badRequest().body("Customer save failed!");
    }

    @PostMapping("/deactivate")
    public ResponseEntity<String> deactivateCustomer(@RequestBody CustomerDto customerDto) {
        customerService.deactivateCustomer(customerDto);
        return ResponseEntity.ok().body("Customer deactivated!");
    }


}
