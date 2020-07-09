package com.example.demo.models.services;

import com.example.demo.models.pojo.Charity;

import java.util.List;

public interface CharityService {
    List<Charity> getAllCharities();
    Charity getCharityById(Long id);
}
