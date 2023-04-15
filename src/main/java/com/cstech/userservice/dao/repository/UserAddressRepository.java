package com.cstech.userservice.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cstech.userservice.dao.entity.UserAddressEntity;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {

}
