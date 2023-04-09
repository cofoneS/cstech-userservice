package com.cstech.userservice.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cstech.userservice.app.model.AddressModel;
import com.cstech.userservice.app.model.MailModel;
import com.cstech.userservice.app.model.UserModel;
import com.cstech.userservice.dao.entity.UserAddressEntity;
import com.cstech.userservice.dao.entity.UserEntity;
import com.cstech.userservice.dao.entity.UserMailEntity;
import com.cstech.userservice.dao.persistence.UserAddressPersistence;
import com.cstech.userservice.dao.persistence.UserMailPersistence;
import com.cstech.userservice.dao.persistence.UserPersistence;
import com.cstech.userservice.exception.UserNotFoundException;
import com.cstech.userservice.utility.Utility;

@Service
@Transactional
public class UserService {
	
	private static final Logger log = Logger.getLogger(UserService.class.getName());
	
	@Autowired
	UserPersistence userPersistence;
	
	@Autowired
	UserAddressPersistence userAddressPersistence;	
	
	@Autowired
	UserMailPersistence userMailPersistence;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AddressService addressService;

	public UserModel findUserById(final String userKey, final Long id) {
		
		UserEntity entity = userPersistence.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		return doUserModel(entity);
	}
	
	public UserModel saveUser(final String userKey, final UserModel model) {

		log.log(Level.INFO, "saveUser: {} ", model.toString());
		
		UserEntity userEntity = userPersistence.save(doUserEntityAll(userKey, model));
		
		//UserMailEntity userMailEntity = userMailPersistence.save( doUserMailEntity(userKey, model.getMail(), userEntity) );
		//userEntity.setUserMail(Arrays.asList(userMailEntity));
		
		//UserAddressEntity userAddressEntity = userAddressPersistence.save( doUserAddressEntity(userKey, model.getAddress(), userEntity) );
		//userEntity.setUserAddress(Arrays.asList(userAddressEntity));
		
		return doUserModel(userEntity);
	}
	
	public UserModel mergeUser(final String userKey, UserModel model) {
		final Timestamp now = Utility.doTimestamp();
		final UserEntity entity = userPersistence.findById(model.getId()).map( item -> {
										    item.setFirstName(model.getFirstName());
										    item.setSurname(model.getSurname()); 
										    item.setNickname(model.getNickname());
										    item.setAvatar(model.getAvatar());
										    item.setUpdatedBy(userKey);
										    item.setUpdatedAt(now);
										    return userPersistence.save(item); 
										}).orElseThrow(() -> new UserNotFoundException(model.getId()));
		//mailService.mergeMail(userKey, model.getMail(), entity);
		//addressService.mergeAddress(userKey, model.getAddress(), entity);
		return doUserModel(entity);
	}
	
	public UserEntity deleteUserOnly(final String userKey, final Long id) {
		final Timestamp now = Utility.doTimestamp();		
		final UserEntity entity = userPersistence.findById(id).map( item -> {
		    item.setEnabled(Boolean.FALSE);
		    item.setUpdatedBy(userKey);
		    item.setUpdatedAt(now);
		    item.setDeletedAt(now);
		    return userPersistence.save(item); 
		}).orElseThrow(() -> new UserNotFoundException(id));
		return entity;
	}	
	
	public Long deleteUser(final String userKey, final Long id) {
		//NO physicist delete
		//userPersistence.deleteById(id);
		
		//YES logical delete
		final UserEntity entity = deleteUserOnly(userKey, id);
		
		final long addressId = entity.getUserAddress().get(0).getUserAddressId();
		addressService.deleteAddress(userKey, addressId);
		
		final long mailId = entity.getUserMail().get(0).getUserMailId();
		mailService.deleteMail(userKey, mailId);
		return id;
	}
	
	private UserModel doUserModel(final UserEntity entity) {
		UserModel model = new UserModel();
		model.setId(entity.getUserId());
		model.setFirstName(entity.getFirstName());
		model.setSurname(entity.getSurname());
		model.setUserKey(entity.getUserKey());
		model.setNickname(entity.getNickname());
		model.setBirthdate( Utility.doOffsetDateTime(entity.getBirthdate()) );
		model.setCityOfBirth(entity.getCityOfBirth());
		model.setCountryOfBirth(entity.getCountryOfBirth());
		model.setIdentityDocumentCode(entity.getIdentityDocumentCode());
		model.setCieCode(entity.getCieCode());
		model.setAvatar(entity.getAvatar());
		model.setTin(entity.getTin());
		model.setTinCountry(entity.getTinCountryKey());
		model.setVat(entity.getVat());
		model.setAddress( doAddressModel(entity.getUserAddress()) );
		model.setMail( doMailModel(entity.getUserMail()) );
		return model;
	}
	
	private AddressModel doAddressModel(final List<UserAddressEntity> entities) {
		if(entities != null && entities.size() > 0 && entities.get(0) != null) {
			return addressService.doAddressModel(entities.get(0));
		}
		return null;
	}
	
	private MailModel doMailModel(final List<UserMailEntity> entities) {
		if(entities != null && entities.size() > 0 && entities.get(0) != null ) {
			return mailService.doMailModel(entities.get(0));			
		}
		return null;
	}

	private UserEntity doUserEntity(final String userKey, final UserModel model) {
		if(model != null) {
			final Timestamp now = Utility.doTimestamp();
			final UserEntity entity = new UserEntity();
			entity.setUserId(model.getId());
			entity.setFirstName( Utility.upperCase(model.getFirstName()) );
			entity.setSurname( Utility.upperCase(model.getSurname()) );
			entity.setUserKey( Utility.upperCase(model.getUserKey()) );
			entity.setNickname( Utility.upperCase(model.getNickname()) );
			entity.setBirthdate( Utility.doOffsetDateTime(model.getBirthdate()));
			entity.setCityOfBirth( Utility.upperCase(model.getCityOfBirth()) );
			entity.setCountryOfBirth( Utility.upperCase(model.getCountryOfBirth()) );
			entity.setIdentityDocumentCode( Utility.upperCase(model.getIdentityDocumentCode()) );
			entity.setCieCode( Utility.upperCase(model.getCieCode()) );
			entity.setAvatar(model.getAvatar());
			entity.setTin( Utility.upperCase(model.getTin()) );
			entity.setTinCountryKey( Utility.upperCase(model.getTinCountry()) );
			entity.setVat( Utility.upperCase(model.getVat()) );
			entity.setEnabled(true);
			entity.setCreatedAt(now);
			entity.setUpdatedBy(userKey);
			entity.setUpdatedAt(now);
			entity.setDeletedAt(null);
			return entity;
		}		
		return null;
	}	
	
	private UserEntity doUserEntityAll(final String userKey, UserModel model) {

		UserEntity entity = doUserEntity(userKey, model);
				
		List<UserAddressEntity> userAddress = new ArrayList<>();
		userAddress.add(addressService.doUserAddressEntity(userKey, model.getAddress(), entity));
		entity.setUserAddress(userAddress);			

		List<UserMailEntity> userMails = new ArrayList<>();
		userMails.add(mailService.doMailEntity(userKey, model.getMail(), entity));
		entity.setUserMail(userMails);
		
		return entity;
	}
}
