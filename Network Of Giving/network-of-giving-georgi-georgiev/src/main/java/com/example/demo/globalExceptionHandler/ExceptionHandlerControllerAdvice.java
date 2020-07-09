package com.example.demo.globalExceptionHandler;
import com.example.demo.controllers.exceptions.AuthorizationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice("com.example.demo.controllers")
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<String> handle(AuthorizationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

}
