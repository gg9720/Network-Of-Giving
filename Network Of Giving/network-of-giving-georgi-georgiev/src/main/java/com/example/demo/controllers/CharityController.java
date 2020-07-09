package com.example.demo.controllers;

import com.example.demo.models.pojo.Charity;
import com.example.demo.models.services.CharityService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CharityController {
    @Autowired
    CharityService charityService;

    @CrossOrigin("http://localhost:4200")
    @SneakyThrows
    @GetMapping("/charities")
    public List<Charity> get() {
        return charityService.getAllCharities();
    }

    @SneakyThrows
    @GetMapping("/charities/{id}")
    public Charity getCharityId(@PathVariable("id") Long id) {
        return charityService.getCharityById(id);
    }




}
