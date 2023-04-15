package com.cstech.userservice.app.model;

import java.io.Serializable;

import com.cstech.userservice.dao.entity.UserAddressEntity;
import com.cstech.userservice.utility.Utility;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddressModel implements Serializable {

	private Long id;
	
	@NotBlank(message = "Street is required.")
	@Size(min = 2, max = 255, message = "Street must be beetween 2 and 255 characters.")
	private String street;
	
	@NotBlank(message = "Postal code is required.")
	private String postalCode;
	
	@NotBlank(message = "Street number is required.")
	private String streetNumber;
	
	@NotBlank(message = "City key is required.")
	@Size(min = 2, max = 31, message = "City key must be beetween 2 and 31 characters.")
	private String cityKey;
	
	@Max(value = 4000, message = "Note too long!")
	private String note;
	
	@NotBlank(message = "Started data time is required.")
	@Min(value = 9, message = "Invalid started data time.")
	private Long startedAt;
	
	private Long endedAt;
	
	public AddressModel() {}
	
	public AddressModel(
			Long id,
			String street,
			String postalCode,
			String streetNumber,
			String cityKey,
			String note,
			Long startedAt,
			Long endedAt
			) {
		this.id = id;
		this.street = street;
		this.postalCode = postalCode;
		this.streetNumber = streetNumber;
		this.cityKey = cityKey;
		this.note = note;
		this.startedAt = startedAt;
		this.endedAt = endedAt;
	}
	
	public AddressModel(final UserAddressEntity entity) {
		if(entity != null) {
			this.id = entity.getUserAddressId();
			this.cityKey = Utility.upperCase(entity.getCityKey());
			this.endedAt = Utility.doEpocTime(entity.getEndedAt());
			this.note = entity.getNote();
			this.postalCode = Utility.upperCase(entity.getPostalCode());
			this.startedAt = Utility.doEpocTime(entity.getStartedAt());
			this.street = entity.getStreet();
			this.streetNumber = entity.getStreetNumber();
		}
	}
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(Long startedAt) {
		this.startedAt = startedAt;
	}
	public Long getEndedAt() {
		return endedAt;
	}
	public void setEndedAt(Long endedAt) {
		this.endedAt = endedAt;
	}
	
	@Override
	public String toString() {
		return "AddressModel [id=" + id + ", street=" + street + ", postalCode=" + postalCode + ", streetNumber="
				+ streetNumber + ", cityKey=" + cityKey + ", note=" + note + ", startedAt=" + startedAt + ", endedAt="
				+ endedAt + "]";
	}
	
}
