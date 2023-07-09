package com.list.listHomeWork;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employees;
    private final int maxEmployeesCount;

    public EmployeeService() {
        employees = new ArrayList<>();
        maxEmployeesCount = 10;
    }

    public void addEmployee(Employee employee) {
        if (employees.size() >= maxEmployeesCount) {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное количество сотрудников");
        }

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }

        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }
}