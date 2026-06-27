package com.example.trainee.bank.app.service;

import com.example.trainee.bank.app.dto.EmployeeLeaveDTO;
import com.example.trainee.bank.app.dto.LeaveApprovalDto;
import com.example.trainee.bank.app.dto.ServiceResponse;

import java.time.LocalDate;

public interface EmployeeLeaveService {
    ServiceResponse requestLeave(EmployeeLeaveDTO leaveDto);

    ServiceResponse approveLeave(LeaveApprovalDto leaveApprovalDto);

    ServiceResponse cancelLeave(EmployeeLeaveDTO leaveDTO);

    ServiceResponse updateLeaveBalance(EmployeeLeaveDTO leaveDTO);

    ServiceResponse getLeaveGroupList(long managerId);

    ServiceResponse getLeaveDatesByMonth(LocalDate startDate, LocalDate endDate, long employeeId);
}
