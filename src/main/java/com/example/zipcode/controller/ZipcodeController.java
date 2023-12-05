package com.example.zipcode.controller;


import com.example.zipcode.service.ZipcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ZipcodeController {

    private final ZipcodeService zipcodeService;

    @GetMapping("/zipcode/{zipcode}")
    public ResponseEntity<String> getData(@PathVariable String zipcode) {
        return new ResponseEntity<>(zipcodeService.getData(zipcode), HttpStatus.OK);
    }
}
