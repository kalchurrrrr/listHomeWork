package com.list.listHomeWork;

public class EmployeeStorageIsFullException extends RuntimeException {
    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}
