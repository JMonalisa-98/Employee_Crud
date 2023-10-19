package com.orive.employee.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orive.employee.Dto.EmployeeDto;
import com.orive.employee.Entity.EmployeeEntity;
import com.orive.employee.Repository.EmployeeRepository;

@Service
public class EmployeeService1 {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

    public EmployeeDto saveEmployee(EmployeeDto employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity, EmployeeDto.class);
    }

    public List<EmployeeDto> getData() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(entity -> modelMapper.map(entity, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDto> getByEmployeeId(int id) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        if (employeeEntity.isPresent()) {
            return Optional.of(modelMapper.map(employeeEntity.get(), EmployeeDto.class));
        } else {
            throw new NoSuchElementException("User not Found");
        }
    }

    public List<EmployeeDto> getByEmployeeName(String name) {
        List<EmployeeEntity> employeeEntities = employeeRepository.findByEmployeeName(name);
        if (employeeEntities.isEmpty()) {
            throw new NoSuchElementException("No User Found By This name");
        } else {
            return employeeEntities.stream()
                    .map(entity -> modelMapper.map(entity, EmployeeDto.class))
                    .collect(Collectors.toList());
        }
    }

    public List<EmployeeDto> getByEmployeeDepartment(String dept) {
        List<EmployeeEntity> employeeEntities = employeeRepository.findByEmployeeDept(dept);
        if (employeeEntities.isEmpty()) {
            throw new NoSuchElementException("No User Found By This department");
        } else {
            return employeeEntities.stream()
                    .map(entity -> modelMapper.map(entity, EmployeeDto.class))
                    .collect(Collectors.toList());
        }
    }

    public EmployeeDto updateEmployeeById(int id, EmployeeDto updatesEmployeeDTO) {
        Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findById(id);
        if (employeeEntityOptional.isPresent()) {
            EmployeeEntity employeeEntity = employeeEntityOptional.get();
            modelMapper.map(updatesEmployeeDTO, employeeEntity); // Update entity
            EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
            return modelMapper.map(savedEntity, EmployeeDto.class);
        } else {
            throw new NoSuchElementException("User not Found");
        }
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

}
