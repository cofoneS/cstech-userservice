package com.cstech.userservice.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

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
    public Object handleTimCatalogException(Exception e) {
        log.log(Level.ALL, "Fallback exception handler, generating KO response: "+e.getMessage(), e);
        CustomResponseModel error = new CustomResponseModel(false, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }	

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomResponseModel> handle(ResourceNotFoundException e) {
    	CustomResponseModel error = new CustomResponseModel(false, e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
