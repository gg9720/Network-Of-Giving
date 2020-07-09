package com.example.demo.models.repository;

import com.example.demo.models.pojo.Charity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharityRepository extends JpaRepository<Charity, Long> {
    Charity save(Charity temp);
}
