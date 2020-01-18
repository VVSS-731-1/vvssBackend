package com.example.demo.repository;

import com.example.demo.model.SkillArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface SkillAreaRepository extends JpaRepository<SkillArea, Integer> {
}
