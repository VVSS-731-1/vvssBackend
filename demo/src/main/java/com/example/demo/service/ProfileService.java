package com.example.demo.service;

import com.example.demo.model.Profile;
import com.example.demo.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> findAll()
    {
        return profileRepository.findAll();
    }

}
