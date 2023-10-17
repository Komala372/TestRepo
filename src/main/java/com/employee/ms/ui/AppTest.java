package com.employee.ms.ui;

import com.employee.ms.domain.Employee;
import com.employee.ms.exception.EmployeeNotFoundException;
import com.employee.ms.exception.InvalidNameException;
import com.employee.ms.service.EmployeeServiceImpl;
import com.employee.ms.service.IEmployeeService;

import java.util.List;

public class AppTest {

    IEmployeeService employeeService = new EmployeeServiceImpl();

    public static void main(String[] args) {

        AppTest test = new AppTest();
        test.runApp();


    }

    private void runApp() {
        registerAndDisplay("Sachin","Tendulkar");
        registerAndDisplay("Puneeth","Rajkumar");
        registerAndDisplay("Sachin","Ambani");
        findByIdAndDisplay(3);
        findByIdAndDisplay(4);
        findEmployeesByFirstNameAscendingById("sachin");
    }

    void registerAndDisplay(String firstName,String lastName){
        try {
            Employee employee = employeeService.register(firstName, lastName);
            System.out.println("Employee registered successfully");
        }catch (InvalidNameException e){
            System.out.println("Invalid Name--"+e.getMessage());
        }

    }

    void findByIdAndDisplay(long id){
        try{
            Employee employee = employeeService.findById(id);
            System.out.println("Employee found successfully");
        }catch(EmployeeNotFoundException e){
            System.out.println("Employee not found--"+e.getMessage());
        }
    }

    void findEmployeesByFirstNameAscendingById(String firstName){
        try{
            List<Employee> employeeList = employeeService.findEmployeesByFirstNameAscending(firstName);
            for (Employee employee:employeeList) {
                System.out.println("Employee:: id = "+employee.getId()+" FirstName="+employee.getFirstName()+" LastName="+employee.getLastName());
            }
        }catch(EmployeeNotFoundException e){
            System.out.println("No employee found--"+e.getMessage());
        }
    }


}
