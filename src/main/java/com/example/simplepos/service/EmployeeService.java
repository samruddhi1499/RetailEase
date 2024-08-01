package com.example.simplepos.service;

import com.example.simplepos.entity.Employee;
import com.example.simplepos.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public void updateEmployee (Employee employeeNew, Employee employeeOld){


        employeeOld.setEmployeeName(employeeNew.getEmployeeName());
        employeeOld.setEmployeeContact(employeeNew.getEmployeeContact());
        employeeOld.setEmployeeSalary(employeeNew.getEmployeeSalary());
        employeeOld.setDOJ(employeeNew.getDOJ());
        employeeOld.setEmployeeAddress(employeeNew.getEmployeeAddress());
        employeeOld.setEmployeeEmergency(employeeNew.getEmployeeEmergency());
        employeeRepository.save(employeeOld);

    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
