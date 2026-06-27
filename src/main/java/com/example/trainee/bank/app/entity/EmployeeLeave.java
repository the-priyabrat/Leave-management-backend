package com.example.trainee.bank.app.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "EMP_LEAVE")
@Entity
public class EmployeeLeave {
    @Id
    private long leaveId;
    private long employeeId;
    private String leaveType;
    private LocalDate StartDate;
    private LocalDate EndDate;
    private String status;
    private String approvalNote;
}
