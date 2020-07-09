package com.example.demo.models.services;


import com.example.demo.controllers.exceptions.UsernameTakenException;
import com.example.demo.models.pojo.Charity;
import com.example.demo.models.pojo.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user) throws UsernameTakenException;

    List<User> getAllUsers();

    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByID(Long id);
    int countUsersByUsername(String username);

    Charity create(Charity charity, User user);

    Charity volunteerFor(Long id, User user);

    List<Charity> getAllCharities(User user);

    List<Charity> delete(Long id, User user);

    Charity donate(Long id,int amount, User user);

    int getDonations(User user);
}
