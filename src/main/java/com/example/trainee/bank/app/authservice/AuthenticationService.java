package com.example.trainee.bank.app.authservice;

import org.json.simple.JSONObject;

import com.example.trainee.bank.app.dto.UserAuth;

public interface AuthenticationService {
	JSONObject generateAccessToken(UserAuth userAuth);
}
