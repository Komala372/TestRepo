package com.employee.ms.service;

import com.employee.ms.domain.Employee;
import com.employee.ms.exception.EmployeeNotFoundException;
import com.employee.ms.exception.InvalidNameException;

import java.util.List;

public interface IEmployeeService {

    Employee register(String firstName,String lastName) throws InvalidNameException;

    Employee findById(long id) throws EmployeeNotFoundException;

    List<Employee> findEmployeesByFirstNameAscending(String firstName) throws EmployeeNotFoundException;


}
