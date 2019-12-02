package com.example.demo.repository;

import com.example.demo.model.ConsultingLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultingLevelRepository extends JpaRepository<ConsultingLevel, Integer> {
}
