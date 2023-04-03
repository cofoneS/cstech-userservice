package com.cstech.userservice.dao.entity;

import java.util.List;

import jakarta.persistence.Basic;
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
@Table(name = "cs_user", schema = "userservice")
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
	@Column(name = "nickname", length = 255)
	private String nickname;
	
	@Basic
	@Column(name = "avatar", length = 511)
	private String avatar;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<UserMailEntity> userMail;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<UserAddressEntity> userAddress;
	
}
