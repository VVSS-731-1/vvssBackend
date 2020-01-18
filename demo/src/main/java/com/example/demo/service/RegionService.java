package com.example.demo.service;

import com.example.demo.model.Region;
import com.example.demo.service.dto.DtoMapping;
import com.example.demo.service.dto.RegionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.example.demo.repository.RegionRepository;

import java.util.List;

@Service
@Component
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private DtoMapping dtoMapping;

    public List<Region> getAllRegions()
    {
        return (List<Region>) regionRepository.findAll();
    }

    //TODO admin only
    public RegionDTO save(RegionDTO regionDTO)
    {
       Region region = dtoMapping.getRegionFromDTO(regionDTO);
       region = regionRepository.save(region);

       return dtoMapping.getDTOFromRegion(region);
    }

    //TODO admin only
    public void deactivateRegion(RegionDTO regionDTO) {
        Region region = regionRepository.getOne(regionDTO.getId());

        if(region != null) {
            region.setStatus(false);
            regionRepository.save(region);
        }
    }

}
