package com.example.trainee.bank.app.authservice;

import com.example.trainee.bank.app.entity.Employee;
import com.example.trainee.bank.app.entity.Manager;
import com.example.trainee.bank.app.entity.RegisteredUser;
import com.example.trainee.bank.app.exception.InvalidLeaveRequest;
import com.example.trainee.bank.app.repository.EmployeeRepository;
import com.example.trainee.bank.app.repository.ManagerRepository;
import com.example.trainee.bank.app.repository.RegisteredUserRepository;
import com.example.trainee.bank.app.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.trainee.bank.app.dto.UserAuth;
import com.example.trainee.bank.app.service.RegisteredUserSrvice;

import java.util.List;


@Service
public class AuthenticationServiceImp implements AuthenticationService {

    Logger logger = LogManager.getLogger(AuthenticationServiceImp.class);

    @Autowired
    RegisteredUserSrvice service;

    @Autowired
    RestTemplate restTemplate;

    @Value("${key.cloak.token.token.url}")
    String tokenUrl;

    @Autowired
    EmployeeRepository empRepository;

    @Autowired
    RegisteredUserRepository userRepository;

    @Autowired
    ManagerRepository managerRepository;


    @SuppressWarnings("unchecked")
    @Override
    public JSONObject generateAccessToken(UserAuth userAuth) {
        JSONObject response = new JSONObject();
        JSONObject restReturned = new JSONObject();
        try {
            if (!service.checkUser(userAuth.getUserName(), userAuth.getPassword())) {
                throw new InvalidLeaveRequest("Access denied");
            }
            RegisteredUser user = userRepository.findByUserName(userAuth.getUserName());
            if (user.getUserType().equalsIgnoreCase(Constants.MANAGER)) {
                Manager manager = managerRepository.findDistinctByManagerName(userAuth.getUserName()).get(0);
                response.put("code", Constants.SUCCESS);
                response.put("userName", manager.getManagerName());
                response.put("userId", manager.getManagerPk().getManagerId());
                response.put("userDepartment", manager.getDept());
                response.put("userType", manager.getUserType());
            } else if (user.getUserType().equalsIgnoreCase(Constants.USER)) {
                List<Employee> employeeList = empRepository.findByEmployeeName(userAuth.getUserName());
                Employee employee = employeeList.get(0);
                response.put("code", Constants.SUCCESS);
                response.put("userName", employee.getEmployeeName());
                response.put("userDepartment", employee.getDepartment());
                response.put("userId", employee.getEmployeeId());
                response.put("userType", employee.getEmployeeType());
            }
        } catch (HttpClientErrorException e) {
            logger.error(e);
            throw e;
        }
        return response;
    }

}
