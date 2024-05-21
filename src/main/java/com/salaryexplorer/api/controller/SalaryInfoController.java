package com.salaryexplorer.api.controller;


import com.salaryexplorer.api.request.SalaryRequest;
import com.salaryexplorer.service.AuthorizationService;
import com.salaryexplorer.service.SalaryInforService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path = "salary-info")
@AllArgsConstructor
public class SalaryInfoController {

    SalaryInforService salaryInforService;
    AuthorizationService authorizationService;

    @PostMapping()
    public ResponseEntity<?>post(@RequestBody SalaryRequest salaryRequest,@RequestHeader("x-token") String token){
        try {
           this.authorizationService.loginGoogle(token);
            return ResponseEntity.ok(salaryInforService.getSalaryInfo(salaryRequest));
        } catch (RuntimeException e) {
            // Manejar la excepción lanzada por loginGoogle
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + e.getMessage());
        }
    }
}
