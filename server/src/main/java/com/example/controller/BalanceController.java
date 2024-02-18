package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BalanceController {

    @GetMapping("/users")
    public String getUsersPage() {

        return "users";
    }

}
