package com.example.demo.service.interfaces;

import com.example.demo.service.dto.ProfileDTO;

public interface IProfileService {

    ProfileDTO saveProfile(ProfileDTO profileDTO);
    ProfileDTO updateProfile(ProfileDTO profileDTO);
    ProfileDTO getProfile(ProfileDTO profileDTO);
    ProfileDTO deleteProfile(ProfileDTO profileDTO);
}
