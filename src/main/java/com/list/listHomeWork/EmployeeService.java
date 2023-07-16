package com.list.listHomeWork;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    private Map<String, Employee> employeeMap;
    private final int maxEmployeesCount;

    public EmployeeService() {
        employeeMap = new HashMap<>();
        maxEmployeesCount = 10;
    }

    public void addEmployee(Employee employee) {
        if (employeeMap.size() >= maxEmployeesCount) {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное количество сотрудников");
        }

        String key = generateKey(employee);
        if (employeeMap.containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        }

        employeeMap.put(key, employee);
    }

    public void removeEmployee(Employee employee) {
        String key = generateKey(employee);
        if (employeeMap.remove(key) == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        String key = generateKey(firstName, lastName);
        return employeeMap.get(key);
    }

    private String generateKey(Employee employee) {
        return generateKey(employee.getFirstName(), employee.getLastName());
    }

    private String generateKey(String firstName, String lastName) {
        return firstName.toLowerCase() + "_" + lastName.toLowerCase();
    }
}