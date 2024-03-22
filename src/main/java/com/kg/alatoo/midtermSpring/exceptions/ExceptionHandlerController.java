package com.kg.alatoo.midtermSpring.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException exception) {
        // You can add custom logic here, such as logging the exception
        System.out.println("Not Found Exception Handler");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found");
    }
}
