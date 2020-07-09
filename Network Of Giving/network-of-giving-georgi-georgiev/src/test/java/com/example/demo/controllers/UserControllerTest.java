package com.example.demo.controllers;
import com.example.demo.models.pojo.Charity;
import com.example.demo.models.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange("/user/list",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        HttpStatus responseStatus = responseEntity.getStatusCode();
        final List<User> users = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseStatus);
        assertNotNull(users);
        assertFalse(users.isEmpty());

    }

    @Test
    public void testGetAllCharitiesOfUser() {
        ResponseEntity<List<Charity>> responseEntity = restTemplate.exchange("/user/allCharities",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Charity>>() {});
        HttpStatus responseStatus = responseEntity.getStatusCode();
        final List<Charity> users = responseEntity.getBody();
        assertEquals(HttpStatus.UNAUTHORIZED, responseStatus);
    }

    @Test
    public void testGetUserByID() {
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("/user/1", User.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();
        assertEquals(HttpStatus.UNAUTHORIZED, responseStatus);
    }
    @Test
    public void testGetNonExistingUser() {
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("/user/11111", User.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();
        assertEquals(HttpStatus.UNAUTHORIZED, responseStatus);
    }


}