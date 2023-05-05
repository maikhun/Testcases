package com.example.testproj.repos;

import com.example.testproj.models.Case;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepo extends CrudRepository<Case, Long> {

}
