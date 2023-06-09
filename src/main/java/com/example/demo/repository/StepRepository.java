package com.example.demo.repository;

import com.example.demo.entity.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StepRepository extends JpaRepository<StepEntity, Long> {
    List<StepEntity> findAllByCaseId(Long id);

    Optional<StepEntity> findById(Long id);
}
