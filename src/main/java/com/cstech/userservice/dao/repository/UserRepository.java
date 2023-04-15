package com.cstech.userservice.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cstech.userservice.dao.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserCriteriaRepository {

}
