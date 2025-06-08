package com.arqui.ageguesser.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AgeGuessController {

    private final RestTemplate restTemplate;

    public AgeGuessController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/age/{name}")
    public AgeDTO getAge(@PathVariable String name) {
        String url = "https://api.agify.io?name=" + name;
        return restTemplate.getForObject(url, AgeDTO.class);
    }
}