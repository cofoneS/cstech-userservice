package com.cstech.userservice.service;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cstech.userservice.app.model.UserModel;
import com.cstech.userservice.app.model.UserSearchModel;
import com.cstech.userservice.dao.entity.UserEntity;
import com.cstech.userservice.dao.repository.UserAddressRepository;
import com.cstech.userservice.dao.repository.UserMailRepository;
import com.cstech.userservice.dao.repository.UserRepository;
import com.cstech.userservice.exception.UserNotFoundException;
import com.cstech.userservice.utility.Utility;

@Service
@Transactional
public class UserService {
	
	private static final Logger log = Logger.getLogger(UserService.class.getName());
	
	@Autowired
	UserRepository userPersistence;
	
	@Autowired
	UserAddressRepository userAddressPersistence;	
	
	@Autowired
	UserMailRepository userMailPersistence;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AddressService addressService;

	public UserModel findUserById(final String userKey, final Long id) {
		
		UserEntity entity = userPersistence.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		return new UserModel(entity);
	}
	
	public UserModel saveUser(final String userKey, final UserModel model) {

		log.log(Level.INFO, "saveUser: {} ", model.toString());
		
		UserEntity userEntity = userPersistence.save( new UserEntity(userKey, model, Boolean.FALSE));
		
		//UserMailEntity userMailEntity = userMailPersistence.save( doUserMailEntity(userKey, model.getMail(), userEntity) );
		//userEntity.setUserMail(Arrays.asList(userMailEntity));
		
		//UserAddressEntity userAddressEntity = userAddressPersistence.save( doUserAddressEntity(userKey, model.getAddress(), userEntity) );
		//userEntity.setUserAddress(Arrays.asList(userAddressEntity));
		
		return new UserModel(userEntity);
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
		return new UserModel(entity);
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
		
		final long addressId = entity.getUserAddresses().stream().findFirst().get().getUserAddressId();
		addressService.deleteAddress(userKey, addressId);
		
		final long mailId = entity.getUserMails().stream().findFirst().get().getUserMailId();
		mailService.deleteMail(userKey, mailId);
		return id;
	}
			
	public Page<UserModel> findUsers(UserSearchModel item, PageRequest page) {
		return userPersistence.findUsers(item, page);
	}
	
	public Page<UserModel> findUsersByKey(String key, PageRequest page) {
		return userPersistence.findUsersByKey(key, page);
	}	
}
