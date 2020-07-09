package com.example.demo.models.repository;

import com.example.demo.models.pojo.UserAndCharity;
import com.example.demo.models.pojo.compKey.UserCharityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAndCharityRepository extends JpaRepository<UserAndCharity, Long> {
    UserAndCharity save(UserAndCharity temp);

    UserAndCharity findById(UserCharityKey temp);


}
