package com.example.trainee.bank.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trainee.bank.app.dto.RegisteredUserDto;
import com.example.trainee.bank.app.entity.RegisteredUser;
import com.example.trainee.bank.app.repository.RegisteredUserRepository;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserSrvice {

    Logger logger = LogManager.getLogger(RegisteredUserServiceImpl.class);

    @Autowired
    RegisteredUserRepository registerUserrepo;

    @Override
    public RegisteredUser saveUser(RegisteredUserDto registeredUserDto) {
        RegisteredUser registeredUser = new RegisteredUser();
        try {
            RegisteredUser user = new RegisteredUser();
            BeanUtils.copyProperties(registeredUserDto, user);
            registeredUser = registerUserrepo.save(user);
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
        return registeredUser;
    }

    @Override
    public Boolean checkUser(String userName, String password) {
        boolean check = false;
        RegisteredUser userList;
        RegisteredUser user = new RegisteredUser();
        try {
            userList = registerUserrepo.findByUserName(userName);
            if (userList != null) {
                user = userList;
            }
            if (password.equals(user.getPassword())) {
                check = true;
            } else {
                check = false;
            }
            System.out.println(check);
            System.out.println(user);
        } catch (Exception e) {
            logger.error(e);
            throw e;
        }
        return check;
    }
}
