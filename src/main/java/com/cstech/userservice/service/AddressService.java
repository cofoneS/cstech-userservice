package com.cstech.userservice.service;

import java.sql.Timestamp;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cstech.userservice.app.model.AddressModel;
import com.cstech.userservice.dao.entity.UserAddressEntity;
import com.cstech.userservice.dao.entity.UserEntity;
import com.cstech.userservice.dao.persistence.UserAddressPersistence;
import com.cstech.userservice.dao.persistence.UserPersistence;
import com.cstech.userservice.exception.AddressNotFoundException;
import com.cstech.userservice.exception.ResourceNotFoundException;
import com.cstech.userservice.exception.UserNotFoundException;
import com.cstech.userservice.utility.Utility;

@Service
@Transactional
public class AddressService {

	private static final Logger log = Logger.getLogger(AddressService.class.getName());
		
	@Autowired
	UserAddressPersistence userAddressPersistence;	
	
	@Autowired
	UserPersistence userPersistence;	

	public AddressModel findAddressById(final String userKey, final Long id) {		
		UserAddressEntity entity = userAddressPersistence.findById(id)
				.orElseThrow(() -> new AddressNotFoundException(id));
		return doAddressModel(entity);
	}
	
	public AddressModel saveAddress(final String userKey, AddressModel model, final Long userId) {
		if(userId != null) {
			throw new UserNotFoundException(userId);
		}
		UserEntity userEntity = userPersistence.findById(userId)
				.orElseThrow(() -> new AddressNotFoundException(userId));
		return mergeAddress(userKey, model, userEntity);
	}	
	
	/**
	 * Merge existed Address if AddressId is not null, disabled it and add another address,
	 * otherwise add new address
	 * 
	 * @param userKey
	 * @param model
	 * @param entity
	 * @return 
	 */
	public AddressModel mergeAddress(final String userKey, AddressModel model, final UserEntity entity) {

		if(entity != null && entity.getUserId() != null) {
			throw new UserNotFoundException(entity.getUserId());
		}

		if(model.getId() != null) {
			userAddressPersistence.findById(model.getId()).map( item -> {	
				deleteAddress(userKey, model.getId());
				item.setUserAddressId(null);
				item = doUserAddressEntity(userKey, model, entity);
			    item = userAddressPersistence.save(item);
			    return doAddressModel(item); 
			}).orElseThrow(() -> new AddressNotFoundException(model.getId()));			
		}else {
			UserAddressEntity addressEntity = doUserAddressEntity(userKey, model, entity);
			addressEntity = userAddressPersistence.save(addressEntity);
			return doAddressModel(addressEntity);
		}
		throw new ResourceNotFoundException(model.getId());
	}	
	
	public Long deleteAddress(final String userKey, final Long id) {
		final Timestamp now = Utility.doTimestamp();		
		userAddressPersistence.findById(id).map( item -> {
		    item.setEnabled(Boolean.FALSE);
		    item.setUpdatedBy(userKey);
		    item.setUpdatedAt(now);
		    item.setDeletedAt(now);
		    return userAddressPersistence.save(item); 
		}).orElseThrow(() -> new AddressNotFoundException(id));		
		return id;
	}	
	
	public UserAddressEntity doUserAddressEntity(final String userKey, AddressModel model, final UserEntity userEntity) {
		if(model != null) {
			final Timestamp now = Utility.doTimestamp();
			UserAddressEntity entity = new UserAddressEntity();
			entity.setCityKey(model.getCityKey());
			entity.setNote(model.getNote());
			entity.setCityKey(model.getPostalCode());
			entity.setStreet(model.getStreet());
			entity.setPostalCode(model.getPostalCode());
			entity.setStreetNumber(model.getStreetNumber());
			entity.setEndedAt( Utility.epochToTimestamp(model.getEndedAt()) );
			entity.setStartedAt( Utility.epochToTimestamp(model.getStartedAt()) );
			entity.setUserAddressId(model.getId());
			entity.setEnabled(true);
			entity.setCreatedAt(now);
			entity.setUpdatedBy(userKey);
			entity.setUpdatedAt(now);
			entity.setDeletedAt(null);
			entity.setUser(userEntity);
			return entity;
		}		
		return null;
	}
	
	public AddressModel doAddressModel(final UserAddressEntity entity) {
		if(entity != null) {
			final AddressModel model = new AddressModel();
			model.setCityKey( Utility.upperCase(entity.getCityKey()) );
			model.setEndedAt( Utility.doEpocTime(entity.getEndedAt()) );
			model.setId(entity.getUserAddressId());
			model.setNote(entity.getNote());
			model.setPostalCode( Utility.upperCase(entity.getPostalCode()) );
			model.setStartedAt( Utility.doEpocTime(entity.getStartedAt()) );
			model.setStreet(entity.getStreet());
			model.setStreetNumber(entity.getStreetNumber());
			return model;
		}
		return null;
	}	
}
