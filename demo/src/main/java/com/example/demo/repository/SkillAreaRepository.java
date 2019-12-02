package com.example.demo.repository;

import com.example.demo.model.SkillArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillAreaRepository extends JpaRepository<SkillArea, Integer> {
}
