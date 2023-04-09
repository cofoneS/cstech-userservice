package com.cstech.userservice.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cstech.userservice.app.model.CustomResponseModel;
import com.cstech.userservice.app.model.UserModel;
import com.cstech.userservice.constant.Constants;
import com.cstech.userservice.service.UserService;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/v1")
public class UserController extends CustomController{
	
	private static final String USER_ID = "id";
	
	@Autowired
	UserService userService;

    @GetMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @NotNull CustomResponseModel<UserModel> user(
    		@RequestHeader(value = Constants.HEADER_FIELD_AUTH_KEY, required = true) @NotEmpty String userKey,
    		@PathVariable(value = USER_ID, required = true) @NotNull Long id
    		) {
    
    	UserModel data = userService.findUserById(userKey, id);
        return doResponseOk(data);
    }
    

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @NotNull CustomResponseModel<UserModel> user(
    		@RequestHeader(value = Constants.HEADER_FIELD_AUTH_KEY, required = true) @NotEmpty String userKey,
    		@RequestBody(required = true) @NotNull UserModel user
    		){
    	    	
    	UserModel data = userService.saveUser(userKey, user);
    	return doResponseOk(data);
    }    
    
    @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @NotNull CustomResponseModel<UserModel> mergeUser(
    		@RequestHeader(value = Constants.HEADER_FIELD_AUTH_KEY, required = true) @NotEmpty String userKey,
    		@RequestBody(required = true) @NotNull UserModel user
    		){
      
    	UserModel data = userService.mergeUser(userKey, user);
    	return doResponseOk(data);
    }

    @DeleteMapping(value = "/user/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @NotNull CustomResponseModel<Long> deleteUser(
    		@RequestHeader(value = Constants.HEADER_FIELD_AUTH_KEY, required = true) @NotEmpty String userKey,
    		@PathVariable(value = USER_ID, required = true) @NotNull Long id
    		) {
    	Long data = userService.deleteUser(userKey, id);
    	return doResponseOk(data);
    }    

}
