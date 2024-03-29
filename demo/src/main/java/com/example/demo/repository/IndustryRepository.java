package com.example.demo.repository;

import com.example.demo.model.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface IndustryRepository extends JpaRepository<Industry, Integer> {
}
