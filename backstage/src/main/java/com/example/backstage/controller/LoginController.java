package com.example.backstage.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("index/")
public class LoginController {

    @PostMapping("login")
    public Map login(){
        String role="";
        return Map.of("role",role);
    }
}
