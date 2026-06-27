package com.example.trainee.bank.app.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trainee.bank.app.authservice.AuthenticationService;
import com.example.trainee.bank.app.dto.UserAuth;

@CrossOrigin("*")
@RestController
@RequestMapping("/token")
public class AuthTokenGenerationController {

	@Autowired
	AuthenticationService service;

	@PostMapping("/get")
	public JSONObject getAccessToken(@RequestBody UserAuth userAuth) {

		return service.generateAccessToken(userAuth); 
	}
}
