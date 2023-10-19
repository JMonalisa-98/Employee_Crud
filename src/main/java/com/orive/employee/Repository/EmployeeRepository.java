package com.orive.employee.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.orive.employee.Entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
	
 List<EmployeeEntity> findByEmployeeName (String employeeName);
 List<EmployeeEntity> findByEmployeeDept (String employeeDept);

}
