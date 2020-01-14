package com.example.demo.service;

import com.example.demo.model.Region;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.RegionDTO;
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

    //TODO admin only
    public RegionDTO save(RegionDTO regionDTO)
    {
       Region region = DtoMapping.getRegionFromDTO(regionDTO);
       region = regionRepository.save(region);

       return DtoMapping.getDTOFromRegion(region);
    }

    //TODO admin only
    public RegionDTO deactivateRegion(RegionDTO regionDTO) {
        Region region = regionRepository.getOne(regionDTO.getId());

        if(region != null) {
            region.setStatus(false);
            return DtoMapping.getDTOFromRegion(regionRepository.save(region));
        }

        return null;
    }

}
