package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.service.dto.CustomerDto;
import com.example.demo.service.dto.UserDTO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public ResponseEntity<String> getAll() {
        Gson gson = new Gson();
        String response = gson.toJson(userService.findAll());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        Gson gson = new Gson();
        if (userService.findById(id) != null) {
            String response = gson.toJson(userService.findById(id));
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("No User with given id");
    }

    @GetMapping("/get/{userName}")
    public ResponseEntity<String> getByName(@PathVariable("userName") String userName) {
        Gson gson = new Gson();
        UserDTO user = userService.findByName(userName);
        if (user != null) {
            String response = gson.toJson(user);
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.badRequest().body("No User with given id");
    }

    @PostMapping("/add") //optional consumes = "application/json" produces = "application/json"
    public ResponseEntity<String> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO response = userService.save(userDTO);
        if (response != null) {
            return ResponseEntity.ok().body("User saved!");
        }
        return ResponseEntity.badRequest().body("User save failed!");
    }

    @PostMapping("/update") //optional consumes = "application/json" produces = "application/json"
    public ResponseEntity<String> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO response = userService.save(userDTO);
        if (response != null) {
            return ResponseEntity.ok().body("User saved!");
        }
        return ResponseEntity.badRequest().body("User save failed!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String userName, @RequestParam String password) {
        UserDTO response = userService.login(userName, password);
        if (response != null) {
            return ResponseEntity.ok().body("User login successful!");
        }
        return ResponseEntity.badRequest().body("User login failed!");
    }
}
