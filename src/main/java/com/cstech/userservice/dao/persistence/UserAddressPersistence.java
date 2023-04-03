package com.cstech.userservice.dao.persistence;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cstech.userservice.dao.entity.UserAddressEntity;

@Repository
public interface UserAddressPersistence extends JpaRepository<UserAddressEntity, Long> {

}
