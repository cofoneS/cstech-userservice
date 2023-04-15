package com.cstech.userservice.dao.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserMailEntity.class)
public abstract class UserMailEntity_ {

	public static volatile SingularAttribute<UserMailEntity, Long> userMailId;
	public static volatile SingularAttribute<UserMailEntity, String> mail;
	public static volatile SingularAttribute<UserMailEntity, String> note;
	public static volatile SingularAttribute<UserMailEntity, Boolean> checked;
	public static volatile SingularAttribute<UserMailEntity, OffsetDateTime> checkedAt;
	public static volatile SingularAttribute<UserMailEntity, OffsetDateTime> startedAt;
	public static volatile SingularAttribute<UserMailEntity, OffsetDateTime> endedAt;
	public static volatile SingularAttribute<UserMailEntity, UserEntity> user;
	
}
