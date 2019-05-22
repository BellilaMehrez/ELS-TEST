package com.els.test.repository;

import com.els.test.domain.Salary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data MongoDB repository for the Salary entity.
 */
@Repository
public interface SalaryRepository extends MongoRepository<Salary, String> {

    Optional<Salary> findOneById(String id);
}
