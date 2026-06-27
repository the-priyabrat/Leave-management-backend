package com.example.trainee.bank.app.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class EmployeeLeaveDTO {

    @NotNull(message = "Employee ID is required")
    private Long leaveId;
    @NotNull(message = "Employee ID is required")
    @Positive(message = "Employee ID must be positive")
    private Long employeeId;
    @NotBlank(message = "Leave type is required")
    private String leaveType;
    @Future
    private LocalDate startDate;
    @Future
    private LocalDate endDate;

    @NotBlank(message = "Description is required")
    @Size(min = 7, max = 50, message = "Size must be 7 to 50")
    private String description;

    private String approvalNote;

    private String status;

}