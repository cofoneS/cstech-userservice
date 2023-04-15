package com.cstech.userservice.dao.entity;

import java.time.OffsetDateTime;

import com.cstech.userservice.app.model.AddressModel;
import com.cstech.userservice.utility.Utility;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cs_user_address", schema = BaseEntity.SCHEMA_DB_APP)
public class UserAddressEntity extends BaseEntity{

	@Id
	@Column(name = "user_address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userAddressId;
	
	@Basic
	@NotNull
	@Column(name = "street", length = 1023)
	private String street;
	
	@Basic
	@NotNull
	@Column(name = "postal_code", length = 63)
	private String postalCode;	
	
	@Basic
	@NotNull
	@Column(name = "street_number", length = 31)
	private String streetNumber;	
	
	@Basic
	@NotNull
	@Column(name = "city_key", length = 31)
	private String cityKey;

	@Basic
	@Column(name = "note")
	private String note;	
	
	@Basic
	@NotNull
	@Column(name = "started_at")
	private OffsetDateTime startedAt;
	
	@Basic
	@Column(name = "ended_at")
	private OffsetDateTime endedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false, insertable = true, updatable = false)
	private UserEntity user;
	
	public UserAddressEntity() {
		super();
	}
	
	public UserAddressEntity(final String userKey, AddressModel model, final UserEntity userEntity, final Boolean isDeleted) {
		super(userKey, isDeleted);
		if(model != null) {
			this.userAddressId = model.getId();
			this.street = (model.getStreet());
			this.postalCode = model.getPostalCode();
			this.streetNumber = model.getStreetNumber();
			this.cityKey = model.getCityKey();
			this.note = model.getNote();
			this.startedAt = Utility.epochToOffsetDateTime(model.getStartedAt());
			this.endedAt = Utility.epochToOffsetDateTime(model.getEndedAt());
			this.user = userEntity;
		}	
	}

	public Long getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(Long userAddressId) {
		this.userAddressId = userAddressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCityKey() {
		return cityKey;
	}

	public void setCityKey(String cityKey) {
		this.cityKey = cityKey;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public OffsetDateTime getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(OffsetDateTime startedAt) {
		this.startedAt = startedAt;
	}

	public OffsetDateTime getEndedAt() {
		return endedAt;
	}

	public void setEndedAt(OffsetDateTime endedAt) {
		this.endedAt = endedAt;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
