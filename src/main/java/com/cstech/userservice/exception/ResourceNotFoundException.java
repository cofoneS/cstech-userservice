package com.cstech.userservice.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(Long id){
		super("Resource not found " + id);
	}
}
