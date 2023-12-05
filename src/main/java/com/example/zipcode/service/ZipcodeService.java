package com.example.zipcode.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ZipcodeService {

    private static final Logger log = LoggerFactory.getLogger(ZipcodeService.class);

    private static final String url = "https://app.zipcodebase.com/api/v1/search";

    @Value(value = "${apiKey}")
    private String apiKey;


    private final RestTemplate restTemplate = new RestTemplate();

    public String getData(String zipcode) {

        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("apikey", apiKey);

            String urlWithParams = url + "?codes=" + zipcode;

            ResponseEntity<String> response = restTemplate.exchange(
                    urlWithParams, HttpMethod.GET,
                    new HttpEntity<>(httpHeaders), String.class
            );

            log.info("Output from ZipcodeAPI: {}", response.getBody());
            return response.getBody();

        } catch (Exception e) {
            log.error("Something went wrong while getting value from ZipcodeAPI", e.getCause());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling endpoint of ZipcodeAPI",
                    e
            );
        }
    }
}

