package com.restaurant.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/about")
public class AboutController {

    @GetMapping
    public String getRestaurantInfo() {
        return "Здесь могла бы быть информация о вашем ресторане.";
    }
}