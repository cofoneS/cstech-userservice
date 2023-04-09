package com.cstech.userservice.service;

import java.sql.Timestamp;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cstech.userservice.app.model.MailModel;
import com.cstech.userservice.dao.entity.UserEntity;
import com.cstech.userservice.dao.entity.UserMailEntity;
import com.cstech.userservice.dao.persistence.UserMailPersistence;
import com.cstech.userservice.dao.persistence.UserPersistence;
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
	UserMailPersistence userMailPersistence;
	
	@Autowired
	UserPersistence userPersistence;

	public MailModel findMailById(final String userKey, final Long id) {
		
		UserMailEntity entity = userMailPersistence.findById(id)
				.orElseThrow(() -> new MailNotFoundException(id));
		return doMailModel(entity);
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
				item = doMailEntity(userKey, model, entity);
			    item = userMailPersistence.save(item);
			    return doMailModel(item); 
			}).orElseThrow(() -> new AddressNotFoundException(model.getId()));			
		}else {
			UserMailEntity mailEntity = doMailEntity(userKey, model, entity);
			mailEntity = userMailPersistence.save(mailEntity);
			return doMailModel(mailEntity);
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

	public UserMailEntity doMailEntity(final String userKey, final MailModel model, final UserEntity userEntity) {
		if(model != null) {
			final Timestamp now = Utility.doTimestamp();
			final UserMailEntity entity = new UserMailEntity();
			entity.setChecked(model.getChecked());
			entity.setCheckedAt( Utility.epochToTimestamp(model.getCheckedAt()) );
			entity.setEndedAt( Utility.epochToTimestamp(model.getEndedAt()) );
			entity.setUserMailId(model.getId());
			entity.setMail(model.getMail());
			entity.setNote(model.getNote());
			entity.setStartedAt( Utility.epochToTimestamp(model.getStartedAt()) );
			entity.setEnabled(Boolean.TRUE);
			entity.setCreatedAt(now);
			entity.setUpdatedBy(userKey);
			entity.setUpdatedAt(now);
			entity.setDeletedAt(null);
			entity.setUser(userEntity);
			return entity;
		}		
		return null;
	}
	
	public MailModel doMailModel(final UserMailEntity entity) {
		if(entity != null ) {
			final MailModel model = new MailModel();
			model.setChecked(entity.getChecked());
			model.setId(entity.getUserMailId());
			model.setNote(entity.getNote());
			model.setCheckedAt( Utility.doEpocTime(entity.getCheckedAt()) );
			model.setEndedAt( Utility.doEpocTime(entity.getEndedAt()) );
			model.setMail( Utility.upperCase(entity.getMail()) );
			model.setStartedAt(entity.getStartedAt().getTime());
			return model;			
		}
		return null;
	}	
}
