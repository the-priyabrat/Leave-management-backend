package com.example.trainee.bank.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LeaveApprovalDto {
    @NotNull(message = "Employee ID must not be null")
    private Long employeeId;

    @NotNull(message = "Manager ID must not be null")
    private Long managerId;

    @NotNull(message = "Leave ID must not be null")
    private Long leaveId;

    @NotBlank(message = "Approval note must not be blank")
    private String approvalNote;
}
