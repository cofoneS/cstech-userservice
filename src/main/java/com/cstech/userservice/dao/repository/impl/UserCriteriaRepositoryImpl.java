package com.cstech.userservice.dao.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.query.QueryUtils;

import com.cstech.userservice.app.controller.UserController;
import com.cstech.userservice.app.model.UserModel;
import com.cstech.userservice.app.model.UserSearchModel;
import com.cstech.userservice.dao.entity.BaseEntity_;
import com.cstech.userservice.dao.entity.UserAddressEntity;
import com.cstech.userservice.dao.entity.UserAddressEntity_;
import com.cstech.userservice.dao.entity.UserEntity;
import com.cstech.userservice.dao.entity.UserEntity_;
import com.cstech.userservice.dao.entity.UserMailEntity;
import com.cstech.userservice.dao.entity.UserMailEntity_;
import com.cstech.userservice.dao.repository.UserCriteriaRepository;
import com.cstech.userservice.utility.Utility;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class UserCriteriaRepositoryImpl implements UserCriteriaRepository{
	
	private static final Logger log = Logger.getLogger(UserCriteriaRepositoryImpl.class.getName());

    @PersistenceContext
    private EntityManager em;
    
    @Override
	public Page<UserModel> findUsers(UserSearchModel payload, PageRequest page){
    			
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserModel> query = cb.createQuery(UserModel.class);
        
        Root<UserEntity> userRoot = query.from(UserEntity.class);   
        Join<UserEntity, UserMailEntity> userMails = userRoot.join(UserEntity_.userMails);
        Join<UserEntity, UserAddressEntity> userAddresses = userRoot.join(UserEntity_.userAddresses);
        
        List<Predicate> criteria = new ArrayList<>();
        criteria.add(cb.equal(userRoot.get(BaseEntity_.enabled), Boolean.TRUE));
        if( Utility.checkCriteriaField( payload.getFirstName() )) {
        	criteria.add(cb.equal(userRoot.get(UserEntity_.firstName), Utility.formatField(payload.getFirstName())));
        }
        if( Utility.checkCriteriaField( payload.getSurname())) {
        	criteria.add(cb.equal(userRoot.get(UserEntity_.surname), Utility.formatField(payload.getSurname())));
        }
        if( Utility.checkCriteriaField(payload.getNickname())) {
        	criteria.add(cb.equal(userRoot.get(UserEntity_.nickname), Utility.formatField(payload.getNickname())));
        }
        if( Utility.checkCriteriaField(payload.getUserKey())) {
        	criteria.add(cb.equal(userRoot.get(UserEntity_.userKey), Utility.formatField(payload.getUserKey())));
        }        
        if( Utility.checkCriteriaField(payload.getTin()) && Utility.checkCriteriaField(payload.getTinCountry())) {
        	criteria.add(cb.equal(userRoot.get(UserEntity_.tin), Utility.formatField(payload.getTin())));
        	criteria.add(cb.equal(userRoot.get(UserEntity_.tinCountryKey), Utility.formatField(payload.getTinCountry())));
        }
        if( Utility.checkCriteriaField(payload.getMail())) {
        	criteria.add(cb.equal(userMails.get(BaseEntity_.enabled), Boolean.TRUE));
        	criteria.add(cb.equal(userMails.get(UserMailEntity_.mail), Utility.formatField(payload.getMail())));
        }
        if( Utility.checkCriteriaField(payload.getPostalCode())) {
        	criteria.add(cb.equal(userAddresses.get(BaseEntity_.enabled), Boolean.TRUE));
        	criteria.add(cb.equal(userAddresses.get(UserAddressEntity_.postalCode), Utility.formatField(payload.getPostalCode())));
        }
                
        query.where(criteria.toArray(new Predicate[criteria.size()]));
        
        if(!page.getSort().isEmpty()) {
        	query.orderBy(QueryUtils.toOrders(page.getSort(), userRoot, em.getCriteriaBuilder()));
        }
        
        return new PageImpl<>( findTypeQuery(query, userRoot, userAddresses, userMails) );
	}

	@Override
	public Page<UserModel> findUsersByKey(String key, PageRequest page) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UserModel> query = cb.createQuery(UserModel.class);
        
        Root<UserEntity> userRoot = query.from(UserEntity.class);   
        Join<UserEntity, UserMailEntity> userMails = userRoot.join(UserEntity_.userMails);
        Join<UserEntity, UserAddressEntity> userAddresses = userRoot.join(UserEntity_.userAddresses);
        
        List<Predicate> criteria = new ArrayList<>();
        criteria.add(cb.equal(userRoot.get(BaseEntity_.enabled), Boolean.TRUE));
        criteria.add(cb.equal(userMails.get(BaseEntity_.enabled), Boolean.TRUE));
        final String keyUpper = Utility.formatLikeField(key);
        log.log(Level.INFO, "key: {0} ", keyUpper);
        
    	Predicate pMail = cb.like(userMails.get(UserMailEntity_.mail), keyUpper);
    	Predicate pUserKey = cb.like(userRoot.get(UserEntity_.userKey), keyUpper);
    	Predicate pSurname = cb.like(userRoot.get(UserEntity_.surname), keyUpper);
    	Predicate pNickname = cb.like(userRoot.get(UserEntity_.nickname), keyUpper);
    	Predicate pfinal = cb.or(pMail, pUserKey, pSurname, pNickname);
    	criteria.add(pfinal);
    	
        query.where(criteria.toArray(new Predicate[criteria.size()]));
        
        if(!page.getSort().isEmpty()) {
        	query.orderBy(QueryUtils.toOrders(page.getSort(), userRoot, em.getCriteriaBuilder()));
        }

        return new PageImpl<>( findTypeQuery(query, userRoot, userAddresses, userMails) );
	}
    
	private List<UserModel> findTypeQuery(CriteriaQuery<UserModel> query, 
			Root<UserEntity> userRoot,
			Join<UserEntity, UserAddressEntity> userAddresses,
			Join<UserEntity, UserMailEntity> userMails){
        TypedQuery<UserModel> typedQuery = em.createQuery(
            	query.multiselect(
            		userRoot.get(UserEntity_.userId)
            		,userRoot.get(UserEntity_.firstName)
            		,userRoot.get(UserEntity_.surname)
            		,userRoot.get(UserEntity_.userKey)
            		,userRoot.get(UserEntity_.nickname)
            		,userRoot.get(UserEntity_.birthdate)
            		,userRoot.get(UserEntity_.cityOfBirth)
            		,userRoot.get(UserEntity_.countryOfBirth)
            		,userRoot.get(UserEntity_.identityDocumentCode)
            		,userRoot.get(UserEntity_.cieCode)
            		,userRoot.get(UserEntity_.avatar)
            		,userRoot.get(UserEntity_.tin)
            		,userRoot.get(UserEntity_.tinCountryKey)
            		,userRoot.get(UserEntity_.vat)
            		
            		,userAddresses.get(UserAddressEntity_.cityKey)
            		,userAddresses.get(UserAddressEntity_.endedAt)
            		,userAddresses.get(UserAddressEntity_.note)
            		,userAddresses.get(UserAddressEntity_.postalCode)
            		,userAddresses.get(UserAddressEntity_.startedAt)
            		,userAddresses.get(UserAddressEntity_.street)
            		,userAddresses.get(UserAddressEntity_.streetNumber)
            		,userAddresses.get(UserAddressEntity_.userAddressId) 
            		
            		,userMails.get(UserMailEntity_.checked)
            		,userMails.get(UserMailEntity_.checkedAt)
            		,userMails.get(UserMailEntity_.endedAt)
            		,userMails.get(UserMailEntity_.mail)
            		,userMails.get(UserMailEntity_.note)
            		,userMails.get(UserMailEntity_.startedAt)
            		,userMails.get(UserMailEntity_.userMailId)
            ));
        return typedQuery.getResultList();
	}

}