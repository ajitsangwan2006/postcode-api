package com.power.springbootapi.controller;

import com.power.springbootapi.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@Controller
public interface ApplicationExceptionHandler {
    Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    default ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    default ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(Constants.DATA_EXIST_MSG, HttpStatus.BAD_REQUEST);
    }
}
