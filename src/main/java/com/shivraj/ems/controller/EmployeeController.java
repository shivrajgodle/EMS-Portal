package com.shivraj.ems.controller;

import com.shivraj.ems.dto.EmployeeDto;
import com.shivraj.ems.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

   // Create New Employee
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
       return new ResponseEntity<EmployeeDto>(savedEmployee, HttpStatus.CREATED);
    }

    //Get New Employee

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long employeeId){
       EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
       return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employeeDto = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> UpdateEmployee(@PathVariable("id") Long employeeId , @RequestBody EmployeeDto employeeDto) {
       EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeId, employeeDto);
       return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted successfully!!!");
    }
}
