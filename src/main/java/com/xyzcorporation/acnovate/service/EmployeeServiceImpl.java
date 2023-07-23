package com.xyzcorporation.acnovate.service;

import com.xyzcorporation.acnovate.model.Employee;
import com.xyzcorporation.acnovate.model.EmployeeHierarchy;
import com.xyzcorporation.acnovate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployeeHierarchy(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeHierarchy getEmployeeHierarchy(String employeeName) {
        EmployeeHierarchy hierarchy = null;
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeName.toLowerCase()); // Convert the input name to lowercase
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            String supervisorName = employee.getSupervisor();
            String supervisorOfSupervisorName = null; // Initialize supervisorOfSupervisorName to null

            if (supervisorName != null) {
                Optional<Employee> supervisorOptional = employeeRepository.findById(supervisorName);
                if (supervisorOptional.isPresent()) {
                    Employee supervisor = supervisorOptional.get();
                    supervisorOfSupervisorName = supervisor.getSupervisor();
                }
            }

            hierarchy = new EmployeeHierarchy(supervisorName != null ? supervisorName : "No supervisor available.", supervisorOfSupervisorName != null ? supervisorOfSupervisorName : "No supervisorOfSupervisor for the given employee.");
        } else {
            // Employee not found in the database
            hierarchy = new EmployeeHierarchy("Employee not found.", "Employee not found.");
        }
        return hierarchy;
    }
}
