package com.cstech.userservice.dao.entity;

import java.sql.Timestamp;

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
@Table(name = "cs_user_address", schema = "userservice")
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
	private String startedAt;
	
	@Basic
	@Column(name = "ended_at")
	private Timestamp endedAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false, insertable = true, updatable = false)
	private UserEntity user;
}
