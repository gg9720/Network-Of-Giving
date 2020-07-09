package com.example.demo.controllers;
import com.example.demo.models.services.UserService;
import org.junit.Test;
import com.example.demo.models.pojo.Charity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CharityControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserService userService;

    @Test
    public void testGetAllCharities() {
        ResponseEntity<List<Charity>> responseEntity = restTemplate.exchange("/charities",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Charity>>() {});
        HttpStatus responseStatus = responseEntity.getStatusCode();
        final List<Charity> charityList = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseStatus);
        assertNotNull(charityList);
        assertFalse(charityList.isEmpty());

    }
    @Test
    public void testGetCharity() {
        ResponseEntity<Charity> responseEntity = restTemplate.getForEntity("/charities/1", Charity.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();
        Charity charity = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseStatus);
        assertNotNull(charity);
    }

    @Test
    public void testGetNonExistingCharity() {
        ResponseEntity<Charity> responseEntity = restTemplate.getForEntity("/tasks/11111", Charity.class);
        HttpStatus responseStatus = responseEntity.getStatusCode();

        assertEquals(HttpStatus.UNAUTHORIZED, responseStatus);
    }

}