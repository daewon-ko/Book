package com.example.ex1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sample")
public class SampleController {

    @GetMapping("/hello")
    public String[] hello() {
        return new String[]{"Hello", "World"};
    }
}