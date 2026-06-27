package com.example.trainee.bank.app.exception.handler;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.trainee.bank.app.dto.ServiceResponse;
import com.example.trainee.bank.app.exception.RecordCreatedException;
import com.example.trainee.bank.app.exception.RecordNotFoundException;

@SuppressWarnings("unchecked")
@ControllerAdvice
public class CustomHandler {

    private static Logger logger = LogManager.getLogger(CustomHandler.class);

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ServiceResponse> handelRecordNotFound(RecordNotFoundException ex) {
        logger.error(ex);
//		JSONObject errorDetail = new JSONObject();
//		errorDetail.put("message", ex.getMessage());
        logger.error(ex.getClass());
        ServiceResponse response = new ServiceResponse("NOT_FOUND", "No element to display !", null);
        return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
    }

    @ExceptionHandler(RecordCreatedException.class)
    public ResponseEntity<ServiceResponse> handelRecordCreated(RecordCreatedException ex) {
        logger.error(ex);
//		JSONObject errorDetail=new JSONObject();
//		errorDetail.put("message",ex.getMessage());
//		errorDetail.put("Exception type", ex.getClass());
        logger.error(ex.getClass());
        ServiceResponse response = new ServiceResponse("Record Created", "Created successfully", null);
        return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
    }
} 
