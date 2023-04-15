package com.cstech.userservice.dao.entity;

import java.sql.Timestamp;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BaseEntity.class)
public abstract class BaseEntity_ {

	public static volatile SingularAttribute<BaseEntity, Boolean> enabled;
	public static volatile SingularAttribute<BaseEntity, Timestamp> createdAt;
	public static volatile SingularAttribute<BaseEntity, Timestamp> updatedAt;
	public static volatile SingularAttribute<BaseEntity, String> updatedBy;
	public static volatile SingularAttribute<BaseEntity, Timestamp> deletedAt;
}
