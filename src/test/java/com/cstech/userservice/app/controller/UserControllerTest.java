package com.cstech.userservice.app.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cstech.userservice.app.model.AddressModel;
import com.cstech.userservice.app.model.CustomResponseModel;
import com.cstech.userservice.app.model.MailModel;
import com.cstech.userservice.app.model.UserModel;

@SpringBootTest
public class UserControllerTest extends BaseControllerTest{
	
	private static final Logger log = Logger.getLogger(UserControllerTest.class.getName());
	
	private static final String DEFAULT_STRING_VALUE = "test";
	private static final String DEFAULT_COUNTRY_VALUE = "ITA";
	private static final Long DEFAULT_EPOCH_TIME_VALUE = 1680790458L;
	private static final String DEFAULT_EMAIL_VALUE = "TEST@TEST.COM";
	
	private final String userKey = DEFAULT_STRING_VALUE;
	
	@Autowired
	private UserController userController;
	
	private UserModel model;
	
	@BeforeEach
	void init() {
	    log.info("@BeforeEach - init");
	    this.model = new UserModel(); 
	    this.model.setFirstName(DEFAULT_STRING_VALUE);
	    this.model.setSurname(DEFAULT_STRING_VALUE);
	    this.model.setUserKey(DEFAULT_STRING_VALUE);
	    this.model.setTin(DEFAULT_STRING_VALUE);
	    this.model.setTinCountry(DEFAULT_STRING_VALUE);
		
		AddressModel address = new AddressModel();
		address.setCityKey(DEFAULT_COUNTRY_VALUE);
		address.setPostalCode(DEFAULT_STRING_VALUE);
		address.setStartedAt(DEFAULT_EPOCH_TIME_VALUE);
		address.setStreet(DEFAULT_STRING_VALUE);
		address.setStreetNumber(DEFAULT_STRING_VALUE);
		this.model.setAddress(address);
		
		MailModel mail = new MailModel();
		mail.setMail(DEFAULT_EMAIL_VALUE);
		mail.setStartedAt(DEFAULT_EPOCH_TIME_VALUE);
		this.model.setMail(mail);
	}	

	@DisplayName("CRUD user test successful")
	@Test
	public void createTest() {
		final CustomResponseModel<UserModel> resUser = userController.user(this.userKey, this.model);
		assertThat(resUser.getSuccess()).isEqualTo(Boolean.TRUE);
		assertThat(resUser.getData().getId()).isGreaterThan(0L);
		
		CustomResponseModel<UserModel> resFind = userController.user(this.userKey, resUser.getData().getId());
		assertThat(resFind.getSuccess()).isEqualTo(Boolean.TRUE);
		assertThat(resFind.getData().getId()).isEqualTo(resUser.getData().getId());

		resFind.getData().setNickname(DEFAULT_STRING_VALUE);
		final CustomResponseModel<UserModel> resMerge = userController.mergeUser(this.userKey, resFind.getData());
		assertThat(resMerge.getSuccess()).isEqualTo(Boolean.TRUE);
		assertThat(resMerge.getData().getId()).isEqualTo(resUser.getData().getId());
		assertThat(resMerge.getData().getNickname()).isEqualTo(DEFAULT_STRING_VALUE);

		final CustomResponseModel<Long> resDelete = userController.deleteUser(this.userKey, resUser.getData().getId());
		assertThat(resDelete.getSuccess()).isEqualTo(Boolean.TRUE);
		assertThat(resDelete.getData()).isEqualTo(resUser.getData().getId());

	}	
	
	

}
