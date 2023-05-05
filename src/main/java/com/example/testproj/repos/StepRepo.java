package com.example.testproj.repos;

import com.example.testproj.models.Step;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepo extends CrudRepository<Step, Long> {
}
