package com.list.listHomeWork;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.addEmployee(employee);
        return employee;
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee employee = employeeService.findEmployee(firstName, lastName);
        employeeService.removeEmployee(employee);
        return employee;
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @ExceptionHandler(EmployeeStorageIsFullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleStorageIsFullException(EmployeeStorageIsFullException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAlreadyAddedException(EmployeeAlreadyAddedException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(EmployeeNotFoundException e) {
        return e.getMessage();
    }
}
