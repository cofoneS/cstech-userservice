package com.cstech.userservice.dao.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserAddressEntity.class)
public abstract class UserAddressEntity_ {

	public static volatile SingularAttribute<UserAddressEntity, Long> userAddressId;
	public static volatile SingularAttribute<UserAddressEntity, String> street;
	public static volatile SingularAttribute<UserAddressEntity, String> postalCode;
	public static volatile SingularAttribute<UserAddressEntity, String> streetNumber;
	public static volatile SingularAttribute<UserAddressEntity, String> cityKey;
	public static volatile SingularAttribute<UserAddressEntity, String> note;
	public static volatile SingularAttribute<UserAddressEntity, OffsetDateTime> startedAt;
	public static volatile SingularAttribute<UserAddressEntity, OffsetDateTime> endedAt;
	public static volatile SingularAttribute<UserAddressEntity, UserEntity> user;
	
}
