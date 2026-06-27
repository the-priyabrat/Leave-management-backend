package com.example.trainee.bank.app.controller;

import com.example.trainee.bank.app.dto.EmployeeLeaveDTO;
import com.example.trainee.bank.app.dto.LeaveApprovalDto;
import com.example.trainee.bank.app.dto.ServiceResponse;
import com.example.trainee.bank.app.service.EmployeeLeaveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin("*")
@RestController
@RequestMapping("/leave")
public class EmpLeaveController {

    @Autowired
    private EmployeeLeaveService leaveService;

    @PostMapping("/request")
    public ResponseEntity<ServiceResponse> requestLeave(@Valid @RequestBody EmployeeLeaveDTO leaveDto) {
        ServiceResponse response = leaveService.requestLeave(leaveDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/approve")
    public ResponseEntity<ServiceResponse> approveLeave(@Valid @RequestBody LeaveApprovalDto leaveApprovalDto) {
        ServiceResponse response = leaveService.approveLeave(leaveApprovalDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/leaveList")
    public ResponseEntity<ServiceResponse> getData(@RequestParam long managerId) {
        ServiceResponse response = leaveService.getLeaveGroupList(managerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/empLeaveDetails")
    public ResponseEntity<ServiceResponse> getLeaveDetails(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam long employeeId) {
        ServiceResponse response = leaveService.getLeaveDatesByMonth(startDate, endDate, employeeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
