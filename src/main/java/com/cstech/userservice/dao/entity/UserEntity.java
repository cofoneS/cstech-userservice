package com.cstech.userservice.dao.entity;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private List<UserMailEntity> userMail;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	private List<UserAddressEntity> userAddress;

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

	public List<UserMailEntity> getUserMail() {
		return userMail;
	}

	public void setUserMail(List<UserMailEntity> userMail) {
		this.userMail = userMail;
	}

	public List<UserAddressEntity> getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(List<UserAddressEntity> userAddress) {
		this.userAddress = userAddress;
	}

}
