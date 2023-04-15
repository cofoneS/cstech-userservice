package com.cstech.userservice.dao.entity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Cascade;

import com.cstech.userservice.app.model.UserModel;
import com.cstech.userservice.utility.Utility;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "cs_user", schema = BaseEntity.SCHEMA_DB_APP)
public class UserEntity extends BaseEntity{

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Basic
	@NotNull
	@Column(name = "firstname", length = 255)
	private String firstName;

	@Basic
	@NotNull
	@Column(name = "surname", length = 255)
	private String surname;	
	
	@Basic
	@NotNull
	@Column(name = "user_key", length = 255)
	private String userKey;
	
	@Basic
	@NotNull
	@Column(name = "tin", length = 255)
	private String tin;
	
	@Basic
	@Column(name = "vat", length = 255)
	private String vat;	
	
	@Basic
	@NotNull
	@Column(name = "tin_country_key", length = 31)
	private String tinCountryKey;	
	
	@Basic
	@Column(name = "nickname", length = 255)
	private String nickname;
	
	@Basic
	@Column(name = "birthdate")
	private OffsetDateTime birthdate;	
	
	@Basic
	@Column(name = "city_of_birth", length = 31)
	private String cityOfBirth;
	
	@Basic
	@Column(name = "country_of_birth", length = 31)
	private String countryOfBirth;
	
	@Basic
	@Column(name = "id_code", length = 255)
	private String identityDocumentCode;
	
	@Basic
	@Column(name = "cie_code", length = 255)
	private String cieCode;	
	
	@Basic
	@Column(name = "avatar", length = 511)
	private String avatar;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	//@JoinColumn(name = "user_id")
	private Collection<UserMailEntity> userMails;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	//@JoinColumn(name = "user_id") --> because we are using mappedBy
	//@Cascade({CascadeType.PERSIST, CascadeType.MERGE})
	private Collection<UserAddressEntity> userAddresses;
	
	public UserEntity() {
		super();
	}
	
	public UserEntity(final String userKey, UserModel model, final Boolean isDeleted) {
		super(userKey, isDeleted);
		if(model != null) {
			this.userId = model.getId();
			this.firstName = Utility.upperCase(model.getFirstName());
			this.surname = Utility.upperCase(model.getSurname());
			this.userKey = Utility.upperCase(model.getUserKey());
			this.nickname = Utility.upperCase(model.getNickname());
			this.birthdate = Utility.doOffsetDateTime(model.getBirthdate());
			this.cityOfBirth = Utility.upperCase(model.getCityOfBirth());
			this.countryOfBirth = Utility.upperCase(model.getCountryOfBirth());
			this.identityDocumentCode = Utility.upperCase(model.getIdentityDocumentCode());
			this.cieCode = Utility.upperCase(model.getCieCode());
			this.avatar = model.getAvatar();
			this.tin = Utility.upperCase(model.getTin());
			this.tinCountryKey = Utility.upperCase(model.getTinCountry());
			this.vat = Utility.upperCase(model.getVat());		
		}			
		
		Collection<UserAddressEntity> userAddress = new ArrayList<>();
		userAddress.add( new UserAddressEntity(userKey, model.getAddress(), this, Boolean.FALSE));
		this.userAddresses = userAddress;			

		Collection<UserMailEntity> userMails = new ArrayList<>();
		userMails.add(new UserMailEntity(userKey, model.getMail(), this, Boolean.FALSE));
		this.userMails = userMails;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getTinCountryKey() {
		return tinCountryKey;
	}

	public void setTinCountryKey(String tinCountryKey) {
		this.tinCountryKey = tinCountryKey;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public OffsetDateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(OffsetDateTime birthdate) {
		this.birthdate = birthdate;
	}

	public String getCityOfBirth() {
		return cityOfBirth;
	}

	public void setCityOfBirth(String cityOfBirth) {
		this.cityOfBirth = cityOfBirth;
	}

	public String getCountryOfBirth() {
		return countryOfBirth;
	}

	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}

	public String getIdentityDocumentCode() {
		return identityDocumentCode;
	}

	public void setIdentityDocumentCode(String identityDocumentCode) {
		this.identityDocumentCode = identityDocumentCode;
	}

	public String getCieCode() {
		return cieCode;
	}

	public void setCieCode(String cieCode) {
		this.cieCode = cieCode;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Collection<UserMailEntity> getUserMails() {
		return userMails;
	}

	public Collection<UserAddressEntity> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(Collection<UserAddressEntity> userAddresses) {
		this.userAddresses = userAddresses;
	}

	public void setUserMails(Collection<UserMailEntity> userMails) {
		this.userMails = userMails;
	}


}
