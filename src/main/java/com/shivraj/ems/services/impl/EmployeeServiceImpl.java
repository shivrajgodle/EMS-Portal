package com.shivraj.ems.services.impl;

import com.shivraj.ems.dto.EmployeeDto;
import com.shivraj.ems.entity.Employee;
import com.shivraj.ems.exceptions.ResourceNotFoundException;
import com.shivraj.ems.mapper.EmployeeMapper;
import com.shivraj.ems.repository.EmployeeRepository;
import com.shivraj.ems.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id " + employeeId));
        return  EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long EmployeeId, EmployeeDto updateEmployee) {
     Employee employee =  employeeRepository.findById(EmployeeId).orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id " + EmployeeId));
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());

       Employee updatedEmployeeObj = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long EmployeeId) {
        Employee employee = employeeRepository.findById(EmployeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id " + EmployeeId));
        employeeRepository.deleteById(EmployeeId);
    }
}
