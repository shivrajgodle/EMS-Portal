package com.shivraj.ems.services;

import com.shivraj.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long EmployeeId , EmployeeDto employee);

    void deleteEmployee(Long EmployeeId);
}
