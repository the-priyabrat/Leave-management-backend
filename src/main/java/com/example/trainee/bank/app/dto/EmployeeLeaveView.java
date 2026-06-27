package com.example.trainee.bank.app.dto;

import java.time.LocalDate;

public interface EmployeeLeaveView {
    Long getManagerId();

    String getEmployeeName();

    String status();

    String getDept();

    String getManagerName();

    Long getEmployeeId();

    String getUserType();

    LocalDate getStartDate();

    LocalDate getEndDate();

    Long getLeaveId();

    String getLeaveType();

    String getStatus();

    String getDescription();
}
