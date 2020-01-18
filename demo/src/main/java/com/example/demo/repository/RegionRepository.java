package com.example.demo.repository;

import com.example.demo.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface RegionRepository extends JpaRepository<Region, Integer> {
}
