package com.infidem.security.inputvalidation.controller;

import com.infidem.security.inputvalidation.model.InputValidationModel;
import com.infidem.security.inputvalidation.service.InputValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InputValidationController {

    @Autowired
    private InputValidationServiceImpl inputValidationService;

    @GetMapping("/health")
    public String index() {
        return "Up!";
    }

    @PostMapping(value = "/validate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> validate(@RequestBody InputValidationModel requestInputValidationModel) throws Exception{

        if(requestInputValidationModel == null) {
            return ResponseEntity.noContent().build();
        }

        if(inputValidationService.validateInput(requestInputValidationModel)) {
            return new ResponseEntity("Safe", HttpStatus.OK);
        } else {
            return new ResponseEntity("Not safe",HttpStatus.NOT_FOUND);
        }
    }
}