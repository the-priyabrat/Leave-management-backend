package com.example.trainee.bank.app.repository;

import com.example.trainee.bank.app.dto.EmployeeLeaveView;
import com.example.trainee.bank.app.entity.Manager;
import com.example.trainee.bank.app.entity.ManagerPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, ManagerPk> {

    @Query(value = "Select m.manager_id As managerId, e.leave_type As leaveType, e.start_date As startDate, e.end_date As endDate, e.employee_id As employeeId, e.description As description, u.employee_name As employeeName, e.status As status, e.leave_id As leaveId from manager m RIGHT JOIN emp_leave e ON m.employee_id = e.employee_id LEFT JOIN employee u ON u.employee_id = e.employee_id where manager_id=:id", nativeQuery = true)
    List<EmployeeLeaveView> findEmployeeLeaveByManagerName(@Param("id") long managerId);

    List<Manager> findDistinctByManagerName(String managerName);
}
