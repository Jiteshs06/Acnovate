package com.xyzcorporation.acnovate.service;

import com.xyzcorporation.acnovate.model.Employee;
import com.xyzcorporation.acnovate.model.EmployeeHierarchy;
import com.xyzcorporation.acnovate.repository.EmployeeRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEmployeeHierarchy() {
        // Create a sample employee object
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setSupervisor("Manager");

        // Call the method to be tested
        employeeService.saveEmployeeHierarchy(employee);

        // Verify that the save method of the repository is called once with the employee object
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    public void testGetEmployeeHierarchyWithoutSupervisor() {
        // Create a sample employee object without a supervisor
        Employee employee = new Employee();
        employee.setName("John Doe");

        // Mock the behavior of the employee repository
        when(employeeRepository.findById("john doe")).thenReturn(Optional.of(employee));

        // Call the method to be tested
        EmployeeHierarchy result = employeeService.getEmployeeHierarchy("john doe");

        // Verify the result
        assertEquals("No supervisor available.", result.getSupervisor());
        assertEquals("No supervisorOfSupervisor for the given employee.", result.getSupervisorOfSupervisor());
    }

    @Test
    public void testGetEmployeeHierarchyEmployeeNotFound() {
        // Mock the behavior of the employee repository to return an empty optional
        doReturn(Optional.empty()).when(employeeRepository).findById(Mockito.anyString());

        // Call the method to be tested
        EmployeeHierarchy result = employeeService.getEmployeeHierarchy("NonExistingEmployee");

        // Verify the result
        assertEquals("Employee not found.", result.getSupervisor());
        assertEquals("Employee not found.", result.getSupervisorOfSupervisor());
    }
}