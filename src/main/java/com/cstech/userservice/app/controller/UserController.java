package com.cstech.userservice.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cstech.userservice.app.model.CustomResponseModel;
import com.cstech.userservice.app.model.UserModel;
import com.cstech.userservice.app.model.UserSearchModel;
import com.cstech.userservice.constant.Constants;
import com.cstech.userservice.service.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/v1")
public class UserController extends CustomController{
	
	private static final Logger log = Logger.getLogger(UserController.class.getName());
	
	private static final String USER_ID = "id";
	private static final String FIELD_USER_KEY = "key";
	public static final String DEFAULT_SORT_FIELD_VALUE = "userId";
	
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
    
    @PostMapping("/users")
    public @NotNull CustomResponseModel<Map<String, Object>> users(
    		@RequestHeader(value = Constants.HEADER_FIELD_AUTH_KEY, required = true) @NotEmpty String userKey,
    		@RequestBody(required = true) @NotNull @Valid UserSearchModel payload,
			@RequestParam(value = Constants.FIELD_PAGE_KEY, defaultValue = Constants.DEFAULT_PAGE_VALUE, required = false) int page,
			@RequestParam(value = Constants.FIELD_SIZE_KEY, defaultValue = Constants.DEFAULT_ITEM_FOR_PAGE_VALUE, required = false) int size,
			@RequestParam(value = Constants.FIELD_DIRECTION_KEY, defaultValue = Constants.DEFAULT_SORT_DIRECTION_VALUE, required = false) Sort.Direction direction,
			@RequestParam(value = Constants.FIELD_SORT_KEY, defaultValue = DEFAULT_SORT_FIELD_VALUE, required = false) String sort    		
    		){
    	Map<String, Object> data = new HashMap<>();
    	PageRequest pr = PageRequest.of(page, size, direction, sort);
    	Page<UserModel> res = userService.findUsers(payload, pr);
    	data.put(Constants.FIELD_DATA_KEY, res);
    	return doResponseOk(data);
    }     

    @GetMapping(value = "/users/{key}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @NotNull CustomResponseModel<Map<String, Object>> users(
    		@RequestHeader(value = Constants.HEADER_FIELD_AUTH_KEY, required = true) @NotEmpty String userKey,
    		@PathVariable(value = FIELD_USER_KEY, required = true) @NotEmpty String key,
			@RequestParam(value = Constants.FIELD_PAGE_KEY, defaultValue = Constants.DEFAULT_PAGE_VALUE, required = false) int page,
			@RequestParam(value = Constants.FIELD_SIZE_KEY, defaultValue = Constants.DEFAULT_ITEM_FOR_PAGE_VALUE, required = false) int size,
			@RequestParam(value = Constants.FIELD_DIRECTION_KEY, defaultValue = Constants.DEFAULT_SORT_DIRECTION_VALUE, required = false) Sort.Direction direction,
			@RequestParam(value = Constants.FIELD_SORT_KEY, defaultValue = DEFAULT_SORT_FIELD_VALUE, required = false) String sort        		
    		) {
    
    	log.log(Level.INFO, "key: {0} ", key);
    	Map<String, Object> data = new HashMap<>();
    	PageRequest pr = PageRequest.of(page, size, direction, sort);
    	Page<UserModel> res = userService.findUsersByKey(key, pr);
    	data.put(Constants.FIELD_DATA_KEY, res);
        return doResponseOk(data);
    }
}
