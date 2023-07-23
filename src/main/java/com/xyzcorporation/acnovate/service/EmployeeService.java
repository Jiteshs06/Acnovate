package com.xyzcorporation.acnovate.service;

import com.xyzcorporation.acnovate.model.Employee;
import com.xyzcorporation.acnovate.model.EmployeeHierarchy;

public interface EmployeeService {
    void saveEmployeeHierarchy(Employee employee);

    EmployeeHierarchy getEmployeeHierarchy(String employeeName);
}
