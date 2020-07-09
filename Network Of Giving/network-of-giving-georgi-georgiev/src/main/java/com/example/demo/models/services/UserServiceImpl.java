package com.example.demo.models.services;


import com.example.demo.controllers.exceptions.UsernameTakenException;
import com.example.demo.models.pojo.Charity;
import com.example.demo.models.pojo.User;
import com.example.demo.models.pojo.UserAndCharity;
import com.example.demo.models.pojo.compKey.UserCharityKey;
import com.example.demo.models.repository.CharityRepository;
import com.example.demo.models.repository.UserAndCharityRepository;
import com.example.demo.models.repository.UserRepository;
import lombok.Builder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Builder
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserAndCharityRepository userAndCharityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CharityRepository charityRepository;


    @Override
    public User register(User user) throws UsernameTakenException {
        if(userRepository.countUsersByUsernameEquals(user.getUsername())>=1){
            throw new UsernameTakenException
                    ("This username already exists, please pick another one which is not.");
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getUserByID(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public int countUsersByUsername(String username) {
        return userRepository.countUsersByUsernameEquals(username);
    }

    @SneakyThrows
    @Override
    public Charity create(Charity charity, User user) {
        for(Charity temp : charityRepository.findAll()){
            if(charity.getCharity_description().equals(temp.getCharity_description())){
                throw new Exception("Charity already exists");
            }
        }
        charity.setCharity_creator(user.getName());
        charityRepository.save(charity);
        UserCharityKey key = new UserCharityKey(user.getUser_id(),charity.getCharity_id());
        UserAndCharity finalOne = new UserAndCharity(key,user,charity);
        userAndCharityRepository.save(finalOne);
        return charity;
    }

    @SneakyThrows
    @Override
    public Charity volunteerFor(Long id, User user) {
        Set<UserAndCharity> temp ;
        if(charityRepository.getOne(id).getCharity_creator().equals(user.getName())){
            throw new Exception("You are the creator, you can't volunteer for the charity");
        }
        if(charityRepository.getOne(id).getCharity_participants_needed() == 0){
            throw new Exception("The places for participants are filled. Thank You!");
        }
        if (!charityRepository.existsById(id)) {
            throw new Exception("Charity with this id doesn't exist");
        }


        UserCharityKey result = new UserCharityKey(user.getUser_id(), id);
        if(userAndCharityRepository.findById(result)!=null){
            return new Charity();
        }
        UserAndCharity finalOne = new UserAndCharity(result, user, charityRepository.getOne(id));
        userAndCharityRepository.save(finalOne);
        temp = user.getAllCharitiesOfTheUser();
        temp.add(finalOne);
        user.setAllCharitiesOfTheUser(temp);
        System.out.println(user.getAllCharitiesOfTheUser().size());
        charityRepository.getOne(id).setCharity_participants_needed(
                charityRepository.getOne(id).getCharity_participants_needed() - 1);
        charityRepository.save(charityRepository.getOne(id));
        System.out.println(charityRepository.getOne(id).getCharity_participants_needed());


        return charityRepository.getOne(id);
    }

    @Override
    public int getDonations(User user) {
        User temp = userRepository.getOne(user.getUser_id());
        user.setDonations(temp.getDonations());
        return user.getDonations();
    }

    @Override
    public List<Charity> getAllCharities(User user) {
        List<Charity> result = new ArrayList<>();
        Long count = 1L;
        while (count <= charityRepository.findAll().size()) {
            UserCharityKey temp = new UserCharityKey(user.getUser_id(), count);
            if (userAndCharityRepository.findById(temp) != null && !(user.getName().equals(charityRepository.getOne(count).getCharity_creator()))) {
                result.add(charityRepository.getOne(count));
            }
            count++;
        }

        return result;
    }


    @SneakyThrows
    @Override
    public Charity donate(Long id, int amount, User user) {
        Optional<Charity> temp = charityRepository.findById(id);
        Optional<User> tempUser = userRepository.findById(user.getUser_id());
        if(temp.isPresent() && tempUser.isPresent()) {
            Charity result = temp.get();
            User resultUser = tempUser.get();
            if (amount > result.getCharity_budget() || amount < 0) {
                throw new Exception("The donation amount is invalid");
            }
            if(result.getAmountCollected()>=result.getCharity_budget()){
                throw new Exception("The money are collected");
            }
            Charity finalCharity = Charity.builder()
                    .charity_id(result.getCharity_id())
                    .charity_description(result.getCharity_description())
                    .charity_budget(result.getCharity_budget())
                    .charity_creator(result.getCharity_creator())
                    .amountCollected(result.getAmountCollected()+amount)
                    .charity_participants_needed(result.getCharity_participants_needed())
                    .charity_name(result.getCharity_name())
                    .build();
            User finalUser = User.builder()
                    .user_id(resultUser.getUser_id())
                    .username(resultUser.getUsername())
                    .password(resultUser.getPassword())
                    .name(resultUser.getName())
                    .age(resultUser.getAge())
                    .gender(resultUser.getGender())
                    .town(resultUser.getTown())
                    .donations(resultUser.getDonations()+amount)
                    .money(resultUser.getMoney()-amount)
                    .build();
            userRepository.save(finalUser);
            charityRepository.save(finalCharity);
            return charityRepository.getOne(id);
        }
        else
        {
            throw new Exception("Not found") ;
        }
    }

    @SneakyThrows
    @Override
    public List<Charity> delete(Long id, User user) {
        Charity charity = charityRepository.getOne(id);
        if(charity != null && charity.getCharity_creator().equals(user.getName())){
            UserCharityKey temp = new UserCharityKey(user.getUser_id(),charity.getCharity_id());
            userAndCharityRepository.delete(userAndCharityRepository.findById(temp));
            charityRepository.delete(charity);
        }
        return charityRepository.findAll();
    }

}



