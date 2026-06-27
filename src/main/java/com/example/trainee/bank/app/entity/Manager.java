package com.example.trainee.bank.app.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "MANAGER")
@Entity
public class Manager {

    @EmbeddedId
    private ManagerPk managerPk;
    private String managerName;
    private String dept;
    private String userType;
}
