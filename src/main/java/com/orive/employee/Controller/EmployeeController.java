package com.orive.employee.Controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orive.employee.Dto.EmployeeDto;
import com.orive.employee.Entity.EmployeeEntity;
import com.orive.employee.Service.EmployeeService;
import com.orive.employee.Service.EmployeeService1;

@RestController
@RequestMapping(value = "employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService1 employeeService1;
	
//	@Autowired
//	private EmployeeService employeeService;
//	
//	@PostMapping(value = "/save")
//	public EmployeeEntity saveEmployee( @RequestBody EmployeeEntity employeeEntity)
//	{
//		EmployeeEntity employeeEntity2 = employeeService.saveEmployee(employeeEntity);
//		return employeeEntity2;	
//	}
//	
//	@GetMapping(value = "/get")
//	public @ResponseBody List<EmployeeEntity> getData()
//	{
//		return employeeService.getData();	
//	}
//
//
//	@GetMapping(value = "/get_By_Id/{id}")
//	public Optional<EmployeeEntity>getByEmployeeId(@PathVariable("id") int id)
//	{
//		return employeeService.getByEmployeeId(id);
//	}
//
//	@GetMapping(value = "/get_By_Name/{name}")
//	public List<EmployeeEntity> getByEmployeeName(@PathVariable("name") String name)
//	{
//		return employeeService.getByEmployeeName(name);	
//	}
//
//	@GetMapping(value = "/get_By_Department/{dept}")	
//	public List<EmployeeEntity> getByEmployeeDepartment(@PathVariable("dept") String dept)
//	{
//		return employeeService.getByEmployeeDepartment(dept);	
//	}
//
//	@PutMapping(value = "/update_By_Id/{id}")
//	public EmployeeEntity updateEmployeeById(@PathVariable("id") int id, @RequestBody EmployeeEntity updatesemployeeEntity)
//	{
//		return employeeService.updateEmployeeById(id, updatesemployeeEntity);		
//	}
//	
//	@DeleteMapping(value = "/delete_By_Id/{id}")
//	public void deleteEmployeeById(@PathVariable("id") int id)
//	{
//		 employeeService.deleteEmployeeById(id);
//	}
	
//	------------ Using Dto Service Class----------------------------
	
	 @PostMapping
	    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDTO) {
	        EmployeeDto savedEmployee = employeeService1.saveEmployee(employeeDTO);
	        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	    }

	    @GetMapping
	    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
	        List<EmployeeDto> employees = employeeService1.getData();
	        return new ResponseEntity<>(employees, HttpStatus.OK);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id) {
	        Optional<EmployeeDto> employee = employeeService1.getByEmployeeId(id);
	        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    @GetMapping("/name/{name}")
	    public ResponseEntity<List<EmployeeDto>> getEmployeesByName(@PathVariable String name) {
	        List<EmployeeDto> employees = employeeService1.getByEmployeeName(name);
	        return new ResponseEntity<>(employees, HttpStatus.OK);
	    }

	    @GetMapping("/department/{dept}")
	    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartment(@PathVariable String dept) {
	        List<EmployeeDto> employees = employeeService1.getByEmployeeDepartment(dept);
	        return new ResponseEntity<>(employees, HttpStatus.OK);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int id, @RequestBody EmployeeDto employeeDTO) {
	        try {
	            EmployeeDto updatedEmployee = employeeService1.updateEmployeeById(id, employeeDTO);
	            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable int id) {
	        employeeService1.deleteEmployeeById(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
}

