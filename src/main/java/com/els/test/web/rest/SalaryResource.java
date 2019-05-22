package com.els.test.web.rest;

import com.els.test.domain.Salary;
import com.els.test.service.SalaryService;
import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SalaryResource {

    private final Logger log = LoggerFactory.getLogger(SalaryResource.class);

    @Autowired
    private SalaryService salaryService;

    @PostMapping("/distinct")
    public ResponseEntity<?> distinct(@RequestParam("file") MultipartFile file, @RequestParam("criteria") String criteria) {
        try{
            List result = salaryService.distinct(file, criteria);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (JsonParseException e){
            e.getStackTrace();
            return new ResponseEntity<>("JsonParseException", HttpStatus.BAD_REQUEST);
        } catch (IOException e){
            e.getStackTrace();
            return new ResponseEntity<>("IOException", HttpStatus.BAD_REQUEST);
        }
    }
}
