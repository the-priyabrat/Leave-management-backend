package com.example.trainee.bank.app.repository;

import com.example.trainee.bank.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByEmployeeName(String employeeName);
}
