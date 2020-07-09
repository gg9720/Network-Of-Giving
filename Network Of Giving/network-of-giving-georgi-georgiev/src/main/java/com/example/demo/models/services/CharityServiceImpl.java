package com.example.demo.models.services;

import com.example.demo.models.pojo.Charity;
import com.example.demo.models.repository.CharityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharityServiceImpl implements CharityService {

    @Autowired
    private CharityRepository charityRepository;

    @Override
    public List<Charity> getAllCharities() {
        return charityRepository.findAll();
    }

    @Override
    public Charity getCharityById(Long id) {
        return charityRepository.getOne(id);
    }

}
