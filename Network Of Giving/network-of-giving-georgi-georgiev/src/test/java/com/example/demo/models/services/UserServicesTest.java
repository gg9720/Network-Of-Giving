//package com.example.demo.models.services;
//
//import com.example.demo.models.pojo.Charity;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.*;
//import org.springframework.test.context.junit4.SpringRunner;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserServicesTest {
//
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
////    @Test
////    public void testGetTask() {
////        ResponseEntity<Task> responseEntity = restTemplate.getForEntity("/tasks/1", Task.class);
////        HttpStatus responseStatus = responseEntity.getStatusCode();
////        Task task = responseEntity.getBody();
////
////        assertEquals(HttpStatus.OK, responseStatus);
////        assertNotNull(task);
////    }
////
////    @Test
////    public void testGetNonExistingTask() {
////        ResponseEntity<Task> responseEntity = restTemplate.getForEntity("/tasks/11111", Task.class);
////        HttpStatus responseStatus = responseEntity.getStatusCode();
////
////        assertEquals(HttpStatus.NOT_FOUND, responseStatus);
////    }
////
////    @Test
////    public void testCreateNewTask() {
////        Task task = new Task();
////        task.setTitle("Test title");
////        task.setDetails("Test details");
////        task.setCompleted(true);
////
////        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/tasks", task, Void.class);
////        HttpStatus responseStatus = responseEntity.getStatusCode();
////
////        assertEquals(HttpStatus.NO_CONTENT, responseStatus);
////    }
////
////    @Test
////    public void testCreateNewInvalidTask() {
////        Task task = new Task();
////
////        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/tasks", task, Void.class);
////        HttpStatus responseStatus = responseEntity.getStatusCode();
////
////        assertEquals(HttpStatus.BAD_REQUEST, responseStatus);
////    }
//
//}
