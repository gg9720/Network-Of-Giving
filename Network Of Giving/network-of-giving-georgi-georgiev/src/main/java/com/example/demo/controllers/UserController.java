package com.example.demo.controllers;

import com.example.demo.models.pojo.Charity;
import com.example.demo.models.pojo.User;
import com.example.demo.models.services.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @SneakyThrows
    @PostMapping("/user/registration")
    public ResponseEntity<User> add(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User regUser = userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(regUser);
    }

    @SneakyThrows
    @PostMapping("/user/create")
    public Charity createCharity(@RequestBody Charity charity, Authentication session) {
        User u = (User) session.getPrincipal();
//        if(u == null){
//            throw new AuthorizationException();
//        }
        return userService.create(charity, u);
    }

    @SneakyThrows
    @GetMapping("/user/volunteer/{id}")
    public Charity volunteer(@PathVariable("id") Long id, Authentication session) {
        User u = (User) session.getPrincipal();
//        if(u==null){
//            throw new AuthorizationException();
//        }
        return userService.volunteerFor(id, u);

    }

    @SneakyThrows
    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable("id") Long id) {
//        if(u==null){
//            throw new AuthorizationException();
//        }
        return userService.getUserByID(id);
    }
    @SneakyThrows
    @GetMapping("user/details")
    public User getUserDetails(Authentication session){
        User u = (User) session.getPrincipal();
        u.setDonations(userService.getDonations(u));
        return u;
    }

    @SneakyThrows
    @GetMapping("/user/allCharities")
    public List<Charity> allCharitiesOfUser(Authentication session) {
        User u = (User) session.getPrincipal();
//        if(u==null){
//            throw new AuthorizationException();
//        }
        return userService.getAllCharities(u);
    }

    @SneakyThrows
    @GetMapping("/user/list")
    public List<User> get() {
        return userService.getAllUsers();
    }

    @SneakyThrows
    @PostMapping("user/donate/{id}")
    public Charity donateTo(@PathVariable("id") Long id, @RequestBody int money, Authentication session){
        User u = (User) session.getPrincipal();
//        if(u == null){
//            throw new Exception("User is not logged in.");
//        }

        return userService.donate(id,money,u);

    }

    @SneakyThrows
    @DeleteMapping("/home/charity/{id}")
    public void deleteCharity(@PathVariable("id")Long id, Authentication session ){
        User u = (User) session.getPrincipal();
        userService.delete(id,u);
    }


}
