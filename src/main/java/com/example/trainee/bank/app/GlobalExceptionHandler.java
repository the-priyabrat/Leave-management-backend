package com.example.trainee.bank.app;

import java.util.List;
import java.util.NoSuchElementException;

import com.example.trainee.bank.app.exception.InvalidLeaveRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.example.trainee.bank.app.dto.ServiceResponse;

@SuppressWarnings("unchecked")
@RestControllerAdvice
public class GlobalExceptionHandler {
    Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JSONObject> handleNotValidException(MethodArgumentNotValidException ex) {
        logger.error(ex);
        JSONArray errorDetails = new JSONArray();
        JSONObject response = new JSONObject();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            JSONObject errorDetail = new JSONObject();
            try {
                errorDetail.put(((FieldError) error).getField(), error.getDefaultMessage());
                errorDetails.add(errorDetail);
            } catch (Exception e) {
                logger.error(e);
            }
        });
        response.put("code", "Error Occured");
        response.put("message", "Validation failed !");
        response.put("details", errorDetails);
        return new ResponseEntity<JSONObject>(response, HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ServiceResponse> HandelingResourceNotFound(NoSuchElementException ex) {
        logger.error(ex);
        JSONObject errorDetail = new JSONObject();
        errorDetail.put("message", ex.getMessage());
        errorDetail.put("Exception type", ex.getClass());
        ServiceResponse response = new ServiceResponse("NOT_FOUND", "No element to display !", List.of(errorDetail));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ServiceResponse> httpClientException(HttpClientErrorException ex) {
        logger.error(ex);
        JSONObject errorDetail = new JSONObject();
        errorDetail.put("message", ex.getMessage());
        errorDetail.put("exception type", ex.getClass());
        ServiceResponse response = new ServiceResponse("Internal_Server_Error", "Can not process request !",
                List.of(errorDetail));
        return new ResponseEntity<ServiceResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidLeaveRequest.class)
    public ResponseEntity<ServiceResponse> httpClientException(InvalidLeaveRequest ex) {
        logger.error(ex);
        logger.error("{}{}", ex.getClass(), ex.getMessage());
        ServiceResponse response = new ServiceResponse("Internal_Server_Error", "Can not process request !",
                null);
        return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ServiceResponse> CommonException(Exception ex) {
        logger.error(ex);
        logger.error(ex.getMessage());
        ServiceResponse response = new ServiceResponse("Internal_Server_Error", "Exception occured",
                null);
        return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
    }
}
