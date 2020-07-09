package com.example.demo.models.repository;

import com.example.demo.models.pojo.Charity;
import com.example.demo.models.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User save(User georgi);

    int countUsersByUsernameEquals(String username);


}
