package com.els.test.service;

import com.els.test.domain.Salary;
import com.mongodb.util.JSONParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface SalaryService {

    Salary createSalary(Salary salary);

    Page<Salary> getAllSalariesPageable(Pageable pageable);

    List<Salary> getAllSalaries();

    Optional<Salary> updateSalary(Salary salary);

    void deleteSalary(String id);

    List<Salary> distinct(MultipartFile file, String criteria) throws IOException, JSONParseException;
}
