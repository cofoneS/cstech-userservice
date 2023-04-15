package com.cstech.userservice.service;

import java.sql.Timestamp;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cstech.userservice.app.model.MailModel;
import com.cstech.userservice.dao.entity.UserEntity;
import com.cstech.userservice.dao.entity.UserMailEntity;
import com.cstech.userservice.dao.repository.UserMailRepository;
import com.cstech.userservice.dao.repository.UserRepository;
import com.cstech.userservice.exception.AddressNotFoundException;
import com.cstech.userservice.exception.MailNotFoundException;
import com.cstech.userservice.exception.ResourceNotFoundException;
import com.cstech.userservice.exception.UserNotFoundException;
import com.cstech.userservice.utility.Utility;

@Service
@Transactional
public class MailService {
	
	private static final Logger log = Logger.getLogger(MailService.class.getName());
		
	@Autowired
	UserMailRepository userMailPersistence;
	
	@Autowired
	UserRepository userPersistence;

	public MailModel findMailById(final String userKey, final Long id) {
		
		UserMailEntity entity = userMailPersistence.findById(id)
				.orElseThrow(() -> new MailNotFoundException(id));
		return new MailModel(entity);
	}	
	
	public MailModel saveMail(final String userKey, MailModel model, final Long userId) {
		if(userId != null) {
			throw new UserNotFoundException(userId);
		}
		UserEntity userEntity = userPersistence.findById(userId)
				.orElseThrow(() -> new MailNotFoundException(userId));
		return mergeMail(userKey, model, userEntity);
	}
	
	/**
	 * Merge existed Mail if MailId is not null, disabled it and add another mail,
	 * otherwise add new mail
	 * 
	 * @param userKey
	 * @param model
	 * @param entity
	 * @return 
	 */
	public MailModel mergeMail(final String userKey, MailModel model, final UserEntity entity) {

		if(entity != null && entity.getUserId() != null) {
			throw new UserNotFoundException(entity.getUserId());
		}
		if(model.getId() != null) {
			userMailPersistence.findById(model.getId()).map( item -> {	
				deleteMail(userKey, model.getId());
				model.setId(null);
				item = new UserMailEntity(userKey, model, entity, Boolean.TRUE);
			    item = userMailPersistence.save(item);
			    return new MailModel(item); 
			}).orElseThrow(() -> new AddressNotFoundException(model.getId()));			
		}else {
			UserMailEntity mailEntity = new UserMailEntity(userKey, model, entity, Boolean.FALSE);
			mailEntity = userMailPersistence.save(mailEntity);
			return new MailModel(mailEntity);
		}
		throw new ResourceNotFoundException(model.getId());
	}	
	
	public Long deleteMail(final String userKey, final Long id) {
		final Timestamp now = Utility.doTimestamp();		
		userMailPersistence.findById(id).map( item -> {
		    item.setEnabled(Boolean.FALSE);
		    item.setUpdatedBy(userKey);
		    item.setUpdatedAt(now);
		    item.setDeletedAt(now);
		    return userMailPersistence.save(item); 
		}).orElseThrow(() -> new MailNotFoundException(id));		
		return id;
	}	

}
