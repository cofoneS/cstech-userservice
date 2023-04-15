package com.cstech.userservice.dao.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.metamodel.CollectionAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;


@StaticMetamodel(UserEntity.class)
public abstract class UserEntity_ {

	public static volatile SingularAttribute<UserEntity, Long> userId;
	public static volatile SingularAttribute<UserEntity, String> firstName;
	public static volatile SingularAttribute<UserEntity, String> surname;
	public static volatile SingularAttribute<UserEntity, String> userKey;
	public static volatile SingularAttribute<UserEntity, String> tin;
	public static volatile SingularAttribute<UserEntity, String> tinCountryKey;
	public static volatile SingularAttribute<UserEntity, String> vat;
	public static volatile SingularAttribute<UserEntity, String> nickname;
	public static volatile SingularAttribute<UserEntity, OffsetDateTime> birthdate;
	public static volatile SingularAttribute<UserEntity, String> cityOfBirth;
	public static volatile SingularAttribute<UserEntity, String> countryOfBirth;
	public static volatile SingularAttribute<UserEntity, String> identityDocumentCode;
	public static volatile SingularAttribute<UserEntity, String> cieCode;
	public static volatile SingularAttribute<UserEntity, String> avatar;
	//public static volatile ListAttribute<UserEntity, UserMailEntity> userMails;
	//public static volatile ListAttribute<UserEntity, UserAddressEntity> userAddresses;
	public static volatile CollectionAttribute<UserEntity, UserMailEntity> userMails;
	public static volatile CollectionAttribute<UserEntity, UserAddressEntity> userAddresses;
}
