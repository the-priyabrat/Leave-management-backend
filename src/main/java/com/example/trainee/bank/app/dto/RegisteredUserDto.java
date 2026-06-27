package com.example.trainee.bank.app.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 
@Entity
public class RegisteredUserDto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String userName;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	} 

	public String getUserName() {
		return userName;
	}

	public RegisteredUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegisteredUserDto(Long id, String userName) {
		super();
		Id = id;
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "RegisteredUser [Id=" + Id + ", userName=" + userName + "]";
	}

}
