package com.cstech.userservice.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cstech.userservice.app.model.CustomResponseModel;

@RestControllerAdvice
public class  ApiExceptionHandler{
	
	private static final Logger log = Logger.getLogger(ApiExceptionHandler.class.getName());

	@SuppressWarnings("rawtypes")
    @ExceptionHandler({Exception.class, Throwable.class})
    public ResponseEntity<CustomResponseModel> handle(Exception e) {
        log.log(Level.ALL, "Fallback exception handler, generating KO response: "+e.getMessage(), e);
        log.log(Level.INFO, "Exception: "+e.getMessage(), e);
        return new ResponseEntity<>(new CustomResponseModel(false, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomResponseModel> handle(ResourceNotFoundException e) {
    	log.log(Level.INFO, "Exception: "+e.getMessage(), e);
        return new ResponseEntity<>(new CustomResponseModel(false, e.getMessage()), HttpStatus.NOT_FOUND);
    }
    
    @SuppressWarnings("rawtypes")
    @ExceptionHandler({UserNotFoundException.class, MailNotFoundException.class, AddressNotFoundException.class})
    public ResponseEntity<CustomResponseModel> handle(UserNotFoundException e) {
    	log.log(Level.INFO, "Exception: "+e.getMessage(), e);
        return new ResponseEntity<>(new CustomResponseModel(false, e.getMessage()), HttpStatus.NOT_FOUND);
    }
    
    @SuppressWarnings("rawtypes")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomResponseModel> handle(DataIntegrityViolationException e) {
    	log.log(Level.INFO, "Exception: "+e.getMessage(), e);
        return new ResponseEntity<>(new CustomResponseModel(false, e.getMessage()), HttpStatus.CONFLICT);
    }

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<CustomResponseModel> handle(InvalidDataAccessApiUsageException e) {
    	log.log(Level.INFO, "Exception: "+e.getMessage(), e);
        return new ResponseEntity<>(new CustomResponseModel(false, e.getMessage()), HttpStatus.EXPECTATION_FAILED);
    }
}
