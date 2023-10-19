package com.orive.employee.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.employee.Entity.EmployeeEntity;
import com.orive.employee.Repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	
	public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity)
	{
	EmployeeEntity employeeEntity2 = employeeRepository.save(employeeEntity);
	return employeeEntity2;		
	}
	
	
	public List<EmployeeEntity> getData()
	{
		return employeeRepository.findAll();
	}
	
	public Optional<EmployeeEntity> getByEmployeeId(int id)
	{
		Optional<EmployeeEntity> employeeEntity=employeeRepository.findById(id);
		if(employeeEntity.isPresent())
		{
			return employeeEntity;
		}
		else 
		{
			throw new NoSuchElementException("User not Found");
		}
	}
	
	
	public List<EmployeeEntity> getByEmployeeName(String name)
	{
		List<EmployeeEntity> employeeEntity=employeeRepository.findByEmployeeName(name);
		if(employeeEntity.isEmpty())
		{
			throw new NoSuchElementException(" No User Found By This name");	
		}
		else 
		{
			return employeeEntity;
		}
	}
	
	public List<EmployeeEntity> getByEmployeeDepartment(String dept)
	{
		List<EmployeeEntity> employeeEntity=employeeRepository.findByEmployeeDept(dept);
		if(employeeEntity.isEmpty())
		{
			throw new NoSuchElementException(" No User Found By This department");	
		}
		else 
		{
			return employeeEntity;
		}
	}
	
	public EmployeeEntity updateEmployeeById(int id, EmployeeEntity updatesemployeeEntity)
	{
		Optional<EmployeeEntity> employeeEntity1 = employeeRepository.findById(id);
		if(employeeEntity1.isPresent())
		{
			EmployeeEntity employeeEntity2 = employeeEntity1.get();
			employeeEntity2.setEmployeeName(updatesemployeeEntity.getEmployeeName());
			employeeEntity2.setEmployeeAge(updatesemployeeEntity.getEmployeeAge());
			employeeEntity2.setEmployeeSalary(updatesemployeeEntity.getEmployeeSalary());
			employeeEntity2.setEmployeeDept(updatesemployeeEntity.getEmployeeDept());
			
		 EmployeeEntity save = employeeRepository.save(employeeEntity2);
		 return save;
		}
		else 
		{
			throw new NoSuchElementException("User not Found");
		}	
	}
	
	public void deleteEmployeeById(int id)
	{
		 employeeRepository.deleteById(id);
	}
}


