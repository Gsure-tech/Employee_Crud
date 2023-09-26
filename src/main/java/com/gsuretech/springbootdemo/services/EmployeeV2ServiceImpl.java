package com.gsuretech.springbootdemo.services;

import com.gsuretech.springbootdemo.entity.EmployeeEntity;
import com.gsuretech.springbootdemo.exception.EmployeeNotFoundException;
import com.gsuretech.springbootdemo.model.Employee;
import com.gsuretech.springbootdemo.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeV2ServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        if (employee.getEmployeeId() == null ||
                employee.getEmailId().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        EmployeeEntity entity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, entity);
        employeeRepository.save(entity);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntityList;
        employeeEntityList = employeeRepository.findAll();
        List<Employee> employees;
        employees = employeeEntityList.stream().map(employeeEntity -> {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeEntity, employee);
            return employee;
        }).collect(Collectors.toList());

        return employees;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with Id " + employeeId + " " + "not found "));
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    @Override
    public String deleteEmployeeById(String employeeId) {
        employeeRepository.deleteById(employeeId);
        return "Employee deleted with the Id: " + employeeId;
    }
}
