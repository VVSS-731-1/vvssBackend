package com.example.demo.service;

import com.example.demo.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.RegionRepository;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions()
    {
        return (List<Region>) regionRepository.findAll();
    }

    public void save(Region region)
    {
        regionRepository.save(region);
    }

}
