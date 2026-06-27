package com.example.trainee.bank.app.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ManagerPk {
    private long managerId;
    private long employeeId;
}
