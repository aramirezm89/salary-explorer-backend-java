package com.salaryexplorer.api.controller;


import com.salaryexplorer.service.AuthorizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "authorization")
@AllArgsConstructor
public class AuthorizationController {

    private final AuthorizationService authorizationService;
    @GetMapping
    public ResponseEntity<?>ValidateToken(@RequestHeader("x-token") String token){
       try{
           var response = authorizationService.loginGoogle(token);
           return  ResponseEntity.ok(response);

       }catch (RuntimeException e){
           // Manejar la excepción lanzada por loginGoogle
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + e.getMessage());
       }

    }
}
