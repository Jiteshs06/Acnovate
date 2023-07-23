package com.xyzcorporation.acnovate.controller;

import com.xyzcorporation.acnovate.model.Employee;
import com.xyzcorporation.acnovate.model.EmployeeHierarchy;
import com.xyzcorporation.acnovate.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testAddEmployee() {
        Employee employee = new Employee("aurthor", "ian");
        ResponseEntity<String> response = employeeController.addEmployee(employee);

        // Verify that the employeeService.saveEmployeeHierarchy method is called
        verify(employeeService, times(1)).saveEmployeeHierarchy(employee);
        // Verify that the response is OK with the correct message
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Employee hierarchy saved successfully.", response.getBody());
    }

    @Test
    public void testGetEmployeeHierarchy_ExistingEmployee() {
        EmployeeHierarchy hierarchy = new EmployeeHierarchy("ian", "mike");
        when(employeeService.getEmployeeHierarchy("aurthor")).thenReturn(hierarchy);

        ResponseEntity<EmployeeHierarchy> response = employeeController.getEmployeeHierarchy("aurthor");

        // Verify that the employeeService.getEmployeeHierarchy method is called
        verify(employeeService, times(1)).getEmployeeHierarchy("aurthor");
        // Verify that the response is OK with the correct hierarchy
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hierarchy, response.getBody());
    }

    @Test
    public void testGetEmployeeHierarchy_NonExistingEmployee() {
        when(employeeService.getEmployeeHierarchy("unknown")).thenReturn(null);

        ResponseEntity<EmployeeHierarchy> response = employeeController.getEmployeeHierarchy("unknown");

        // Verify that the employeeService.getEmployeeHierarchy method is called
        verify(employeeService, times(1)).getEmployeeHierarchy("unknown");
        // Verify that the response is NOT_FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
