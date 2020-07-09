//package com.example.demo.models.services;
//
//import com.example.demo.models.pojo.Charity;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import java.util.List;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.*;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.Assert.*;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CharityServicesTest {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void testGetAllTasks() {
//        ResponseEntity<List<Charity>> responseEntity = restTemplate.exchange("/charities",
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<Charity>>() {});
//        HttpStatus responseStatus = responseEntity.getStatusCode();
//        final List<Charity> charityList = responseEntity.getBody();
//        assertEquals(HttpStatus.OK, responseStatus);
//        assertNotNull(charityList);
//        assertFalse(charityList.isEmpty());
//
//    }
//
//}
