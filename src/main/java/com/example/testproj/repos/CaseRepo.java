package com.example.testproj.repos;

import com.example.testproj.models.Case;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface CaseRepo extends CrudRepository<Case, Long> {

}
