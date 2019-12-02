package com.example.demo.service;

import com.example.demo.model.Profile;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @Test
    void findAll() {

        List<Profile> profiles = profileService.findAll();

        assertEquals(profiles.size(), 3);

    }
}