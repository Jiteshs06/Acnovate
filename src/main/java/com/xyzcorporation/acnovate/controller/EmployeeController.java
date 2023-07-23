package com.xyzcorporation.acnovate.controller;

import com.xyzcorporation.acnovate.model.Employee;
import com.xyzcorporation.acnovate.model.EmployeeHierarchy;
import com.xyzcorporation.acnovate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployeeHierarchy(employee);
        return ResponseEntity.ok("Employee hierarchy saved successfully.");
    }

    @GetMapping("/employees/{name}")
    public ResponseEntity<EmployeeHierarchy> getEmployeeHierarchy(@PathVariable String name) {
        EmployeeHierarchy hierarchy = employeeService.getEmployeeHierarchy(name);
        if (hierarchy == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hierarchy);
    }
}
