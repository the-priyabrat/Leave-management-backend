package com.example.trainee.bank.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trainee.bank.app.entity.RegisteredUser;

public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {
	
	RegisteredUser findByUserName(String name);

}
