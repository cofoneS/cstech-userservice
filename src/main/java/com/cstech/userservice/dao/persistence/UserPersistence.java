package com.cstech.userservice.dao.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cstech.userservice.dao.entity.UserEntity;

@Repository
public interface UserPersistence extends JpaRepository<UserEntity, Long> {
	
}
