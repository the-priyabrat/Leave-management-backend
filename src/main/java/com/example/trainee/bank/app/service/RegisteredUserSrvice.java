package com.example.trainee.bank.app.service;

import com.example.trainee.bank.app.dto.RegisteredUserDto;
import com.example.trainee.bank.app.entity.RegisteredUser;

public interface RegisteredUserSrvice{
	public RegisteredUser saveUser(RegisteredUserDto registeredUserDto);
	public Boolean checkUser(String userName, String password);
}
 