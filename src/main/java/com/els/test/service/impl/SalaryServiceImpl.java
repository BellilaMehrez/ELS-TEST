package com.els.test.service.impl;

import com.els.test.domain.Salary;
import com.els.test.repository.SalaryRepository;
import com.els.test.service.SalaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.util.JSONParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing users.
 */

@Service
public class SalaryServiceImpl implements SalaryService {

    private final Logger log = LoggerFactory.getLogger(SalaryServiceImpl.class);

    @Autowired
    private SalaryRepository salaryRepository;

    @Override
    public Salary createSalary(Salary salaryToSave) {
        Salary salary = new Salary();
        salary.setFirstName(salaryToSave.getFirstName());
        salary.setLastName(salaryToSave.getLastName());
        salary.setEmail(salaryToSave.getEmail().toLowerCase());
        salary.setPhoneNumber(salaryToSave.getPhoneNumber());
        salary.setSalary(salaryToSave.getSalary());
        salaryRepository.save(salary);
        log.debug("Created Information for Salary: {}", salary);
        return salary;
    }

    @Override
    public Page<Salary> getAllSalariesPageable(Pageable pageable) {
        return salaryRepository.findAll(pageable);
    }

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepository.findAll();
    }

    @Override
    public Optional<Salary> updateSalary(Salary salaryToUpdate) {
        return Optional.of(salaryRepository
                .findById(salaryToUpdate.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(salary -> {
                    salary.setFirstName(salaryToUpdate.getFirstName());
                    salary.setLastName(salaryToUpdate.getLastName());
                    salary.setEmail(salaryToUpdate.getEmail().toLowerCase());
                    salary.setPhoneNumber(salaryToUpdate.getPhoneNumber());
                    salary.setSalary(salaryToUpdate.getSalary());
                    salaryRepository.save(salary);
                    log.debug("Changed Information for Salary: {}", salary);
                    return salary;
                });
    }

    @Override
    public void deleteSalary(String id) {
        salaryRepository.findOneById(id).ifPresent(salary -> {
            salaryRepository.delete(salary);
            log.debug("Deleted Salary: {}", salary);
        });
    }

    @Override
    public List<Salary> distinct(MultipartFile file, String criteria) throws IOException, JSONParseException {
        byte[] bytes = file.getBytes();
        String jsonString = new String(bytes);
        List<Salary> salaries = jsonStringToList(jsonString);
        Set<String> set = new HashSet<>(salaries.size());
        switch (criteria) {
            case "FIRST_NAME":
                return salaries.stream().filter(p -> set.add(p.getFirstName())).collect(Collectors.toList());
            case "LAST_NAME":
                return salaries.stream().filter(p -> set.add(p.getLastName())).collect(Collectors.toList());
            case "EMAIL":
                return salaries.stream().filter(p -> set.add(p.getEmail())).collect(Collectors.toList());
            case "PHONE_NUMBER":
                return salaries.stream().filter(p -> set.add(p.getPhoneNumber())).collect(Collectors.toList());
            case "SALARY":
                return salaries.stream().filter(p -> set.add(p.getSalary() + "")).collect(Collectors.toList());
        }
        return salaries;
    }

    private List<Salary> jsonStringToList(String jsonString) throws IOException, JSONParseException {
        ObjectMapper mapper = new ObjectMapper();
        List<Salary> Salaries = Arrays.asList(mapper.readValue(jsonString, Salary[].class));
        return Salaries;
    }
}
