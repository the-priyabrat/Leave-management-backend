package com.example.trainee.bank.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "EMPLOYEE")
@Entity
public class Employee {
    @Id
    private long employeeId;
    private String employeeName;
    private String department;
    private String employeeType;
    private Integer employeeLeaveBalance;
    private String description;
}
