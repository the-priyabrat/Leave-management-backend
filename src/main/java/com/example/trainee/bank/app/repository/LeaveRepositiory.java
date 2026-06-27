package com.example.trainee.bank.app.repository;

import com.example.trainee.bank.app.entity.EmployeeLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepositiory extends JpaRepository<EmployeeLeave, Long> {
    @Query(value = "SELECT * FROM emp_leave e WHERE e.employee_id = :id AND e.start_date BETWEEN :startDate AND :endDate AND e.end_date   BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<EmployeeLeave> findEmpLeaveByLeaveDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("id") long employeeId);
}
