package com.example.trainee.bank.app.service;

import com.example.trainee.bank.app.dto.EmployeeLeaveDTO;
import com.example.trainee.bank.app.dto.EmployeeLeaveView;
import com.example.trainee.bank.app.dto.LeaveApprovalDto;
import com.example.trainee.bank.app.dto.ServiceResponse;
import com.example.trainee.bank.app.entity.Employee;
import com.example.trainee.bank.app.entity.EmployeeLeave;
import com.example.trainee.bank.app.entity.Manager;
import com.example.trainee.bank.app.entity.ManagerPk;
import com.example.trainee.bank.app.exception.InvalidLeaveRequest;
import com.example.trainee.bank.app.exception.LeaveApprovalException;
import com.example.trainee.bank.app.repository.EmployeeRepository;
import com.example.trainee.bank.app.repository.LeaveRepositiory;
import com.example.trainee.bank.app.repository.ManagerRepository;
import com.example.trainee.bank.app.util.Constants;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements EmployeeLeaveService {

    @Autowired
    private LeaveRepositiory leaveRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private ManagerRepository managerRepo;

    Logger logger = LogManager.getLogger(LeaveServiceImpl.class);

    @Override
    public ServiceResponse requestLeave(EmployeeLeaveDTO leaveDto) {
        ServiceResponse response = new ServiceResponse();
        Integer leaveBalance = Integer.MIN_VALUE;
        if (!validateDates(leaveDto.getStartDate(), leaveDto.getEndDate())) {
            throw new InvalidLeaveRequest("Invalid date format");
        }
        try {
            Optional<Employee> employeeOptional = employeeRepo.findById(leaveDto.getEmployeeId());
            if (employeeOptional.isPresent()) {
                leaveBalance = employeeOptional.get().getEmployeeLeaveBalance();
            }
            long leaveDuration = ChronoUnit.DAYS.between(leaveDto.getStartDate(), leaveDto.getEndDate()) + 1;
            if (leaveDuration > leaveBalance) {
                throw new InvalidLeaveRequest("Insufficient leave balance");
            }
            EmployeeLeave empLeave = new EmployeeLeave();
            BeanUtils.copyProperties(leaveDto, empLeave);
            empLeave.setStatus(Constants.PENDING);
            EmployeeLeave result = leaveRepo.save(empLeave);
            if (result != null) {
                return new ServiceResponse("SUCCESS", "Successfully Saved", null);
            }
        } catch (Exception e) {
            throw e;
        }
        return new ServiceResponse("FAILED", "Save Fail", null);
    }

    public static boolean validateDates(LocalDate startDate, LocalDate endDate) {
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        if (startDate.isBefore(today) || endDate.isBefore(today)) {
            return false;
        }
        if (startDate.getYear() > currentYear || endDate.getYear() > currentYear) {
            return false;
        }
        if (startDate.isAfter(endDate)) {
            return false;
        }
        return true;

    }


    @Transactional
    @Override
    public ServiceResponse approveLeave(LeaveApprovalDto leaveApprovalDto) {
        try {
            Optional<Manager> managerOptional = managerRepo.findById(new ManagerPk(leaveApprovalDto.getManagerId(), leaveApprovalDto.getEmployeeId()));
            if (managerOptional.isEmpty()) {
                throw new LeaveApprovalException("Access Denied !");
            }
            Optional<EmployeeLeave> leaveOptional = leaveRepo.findById(leaveApprovalDto.getLeaveId());
            if (leaveOptional.isEmpty() || leaveOptional.get().getStatus().equals(Constants.APPROVED)) {
                throw new LeaveApprovalException("There is no pending leave");
            }
            EmployeeLeave employeeLeave = leaveOptional.get();
            employeeLeave.setStatus(Constants.APPROVED);
            Optional<Employee> empOptional = employeeRepo.findById(leaveApprovalDto.getEmployeeId());
            if (empOptional.isEmpty()) {
                throw new LeaveApprovalException("No Employee found with this id");
            }
            Employee employee = empOptional.get();
            employee.setEmployeeLeaveBalance(employee.getEmployeeLeaveBalance() - 1);
            employeeRepo.save(employee);
            employeeLeave.setApprovalNote(leaveApprovalDto.getApprovalNote());
            EmployeeLeave result = leaveRepo.save(employeeLeave);
            if (result != null) {
                return new ServiceResponse(Constants.SUCCESS, "Leave Approved", null);
            }
        } catch (Exception e) {
            throw e;
        }
        return new ServiceResponse(Constants.FAILED, "Leave Approval Failed", null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ServiceResponse getLeaveGroupList(long managerId) {
        ServiceResponse response = new ServiceResponse();
        List<JSONObject> details = new LinkedList<>();
        try {
            List<EmployeeLeaveView> empLeaveView = managerRepo.findEmployeeLeaveByManagerName(managerId);
            empLeaveView.forEach(data -> {
                JSONObject result = new JSONObject();
                result.put("managerId", data.getManagerId());
                result.put("leaveType", data.getLeaveType());
                result.put("leaveId", data.getLeaveId());
                result.put("startDate", data.getStartDate());
                result.put("endDate", data.getEndDate());
                result.put("description", data.getDescription());
                result.put("status", data.getStatus());
                result.put("employeeId", data.getEmployeeId());
                result.put("employeeName", data.getEmployeeName());
                details.add(result);
            });
        } catch (Exception e) {
            throw e;
        }
        return new ServiceResponse(Constants.SUCCESS, "Successfully Fetched", details);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ServiceResponse getLeaveDatesByMonth(LocalDate startDate, LocalDate endDate, long employeeId) {
        List<JSONObject> details = new ArrayList<>();
        try {
            List<EmployeeLeave> empList = leaveRepo.findEmpLeaveByLeaveDateBetween(startDate, endDate, employeeId);
            if (empList.isEmpty()) {
                throw new InvalidLeaveRequest("No leave found");
            }
            for (EmployeeLeave employeeLeave : empList) {
                JSONObject leave = new JSONObject();
                leave.put("leaveId", employeeLeave.getLeaveId());
                leave.put("employeeId", employeeLeave.getEmployeeId());
                leave.put("startDate", employeeLeave.getStartDate());
                leave.put("endDate", employeeLeave.getEndDate());
                leave.put("leaveType", employeeLeave.getLeaveType());
                leave.put("status", employeeLeave.getStatus());
                details.add(leave);
            }
            return new ServiceResponse(Constants.SUCCESS, "Data fetched", details);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ServiceResponse cancelLeave(EmployeeLeaveDTO leaveDTO) {
        return null;
    }

    @Override
    public ServiceResponse updateLeaveBalance(EmployeeLeaveDTO leaveDTO) {
        return null;
    }
}
