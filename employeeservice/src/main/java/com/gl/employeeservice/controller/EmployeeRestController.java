/**
 * 
 */
package com.gl.employeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.employeeservice.entity.Employee;
import com.gl.employeeservice.entity.Role;
import com.gl.employeeservice.entity.User;
import com.gl.employeeservice.service.EmployeeService;

/**
 * 
 */
/**
 * This class acts as a REST controller for managing Employee entities.
 */
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        return employeeService.saveUser(user);
    }

    @PostMapping("/role")
    public Role saveRole(@RequestBody Role role) {
        return employeeService.saveRole(role);
    }

    // expose "/employees" and return list of employees with authorization check
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')") // Assuming roles for authorized users
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        // set id to 0 to force a save of new item
        theEmployee.setId(0);

        employeeService.save(theEmployee);

        return theEmployee;
    }

    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        employeeService.save(theEmployee);

        return theEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee tempEmployee = employeeService.findById(employeeId);

        // throw custom exception if null
        if (tempEmployee == null) {
            throw new EmployeeNotFoundException("Employee id not found - " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }

    @GetMapping("/employees/search/{firstName}")
    public List<Employee> searchByFirstName(@PathVariable String firstName) {
        return employeeService.searchByFirstName(firstName);
    }

    @GetMapping("/employees/sort")
    public List<Employee> sortByFirstName(@RequestParam(name = "order") String order) {

        return employeeService.sortByFirstName(order);
    }
}

// Custom exception for employee not found
class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
