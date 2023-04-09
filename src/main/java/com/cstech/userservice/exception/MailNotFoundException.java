package com.cstech.userservice.exception;

public class MailNotFoundException extends RuntimeException {

	public MailNotFoundException(Long id){
		super("Could not find mail " + id);
	}
}
