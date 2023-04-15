package com.cstech.userservice.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.cstech.userservice.app.model.UserModel;
import com.cstech.userservice.app.model.UserSearchModel;

@Repository
public interface UserCriteriaRepository {
	
	public Page<UserModel> findUsers(UserSearchModel payload, PageRequest page);
	
	public Page<UserModel> findUsersByKey(String key, PageRequest page);

}
