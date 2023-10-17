package com.employee.ms.service;

import com.employee.ms.domain.Employee;
import com.employee.ms.exception.EmployeeNotFoundException;
import com.employee.ms.exception.InvalidNameException;

import java.util.*;

public class EmployeeServiceImpl implements IEmployeeService{

    Map<Long,Employee> employeeMap = new HashMap<>();

    long index = 0;
    public long generateId(){
        return ++index;
    }

    @Override
    public Employee register(String firstName, String lastName) throws InvalidNameException {
        if((firstName.length()>2 && firstName.length()<10) && (lastName.length()>2 && lastName.length()<10)){
            long id = generateId();
            employeeMap.put(id,new Employee(id,firstName,lastName));
            System.out.println("Employee details::"+employeeMap.get(id).getId()+" "+employeeMap.get(id).getFirstName()
            +" "+employeeMap.get(id).getLastName());
            return employeeMap.get(id);
        }else{
            throw new InvalidNameException("Invalid name");
        }

    }

    @Override
    public Employee findById(long id) throws EmployeeNotFoundException {
        if(id<1){
            System.out.println("Invalid Id");
        }
        Employee employee = employeeMap.get(id);
        if(employee!=null){
            System.out.println("Employee::id = "+employee.getId()+" firstName = "+employee.getFirstName()+" lastName = "+employee.getLastName());
            return employee;
        }else{
            throw new EmployeeNotFoundException("Employee not found for the id "+id);
        }
    }

    @Override
    public List<Employee> findEmployeesByFirstNameAscending(String firstName) throws EmployeeNotFoundException {

        if(firstName.length()<3){
            System.out.println("Insufficient text for search");
        }

        List<Employee> list = new ArrayList<>();

        for (Map.Entry<Long,Employee> entry:employeeMap.entrySet()) {
            if(entry.getValue().getFirstName().startsWith("Sachin")){
                //System.out.println("Inside list adding value");
                list.add(entry.getValue());
            }
        }

        Collections.sort(list,valueComparator);
        if(list == null){
            throw new EmployeeNotFoundException("Employee not found");
        }

        return list;
    }

    Comparator<Employee> valueComparator = new Comparator<Employee>() {
        @Override
        public int compare(Employee o1, Employee o2) {
            int id1 = (int) o1.getId();
            int id2 = (int)o2.getId();
            return id1-id2;
        }
    };

}
