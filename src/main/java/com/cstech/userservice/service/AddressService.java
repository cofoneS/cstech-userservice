package com.cstech.userservice.service;

import java.sql.Timestamp;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cstech.userservice.app.model.AddressModel;
import com.cstech.userservice.dao.entity.UserAddressEntity;
import com.cstech.userservice.dao.entity.UserEntity;
import com.cstech.userservice.dao.repository.UserAddressRepository;
import com.cstech.userservice.dao.repository.UserRepository;
import com.cstech.userservice.exception.AddressNotFoundException;
import com.cstech.userservice.exception.ResourceNotFoundException;
import com.cstech.userservice.exception.UserNotFoundException;
import com.cstech.userservice.utility.Utility;

@Service
@Transactional
public class AddressService {

	private static final Logger log = Logger.getLogger(AddressService.class.getName());
		
	@Autowired
	UserAddressRepository userAddressPersistence;	
	
	@Autowired
	UserRepository userPersistence;	

	public AddressModel findAddressById(final String userKey, final Long id) {		
		UserAddressEntity entity = userAddressPersistence.findById(id)
				.orElseThrow(() -> new AddressNotFoundException(id));
		return new AddressModel(entity);
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
				item = new UserAddressEntity(userKey, model, entity, Boolean.TRUE);
			    item = userAddressPersistence.save(item);
			    return new AddressModel(item); 
			}).orElseThrow(() -> new AddressNotFoundException(model.getId()));			
		}else {
			UserAddressEntity addressEntity = new UserAddressEntity(userKey, model, entity, Boolean.FALSE);
			addressEntity = userAddressPersistence.save(addressEntity);
			return new AddressModel(addressEntity);
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
	
}
