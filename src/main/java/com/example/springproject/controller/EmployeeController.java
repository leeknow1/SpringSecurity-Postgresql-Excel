package com.example.springproject.controller;

import com.example.springproject.entity.EmployeeEntity;
import com.example.springproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public List<EmployeeEntity> getAll(){
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeEntity> getById(@PathVariable(value = "id") Integer id){
        return employeeService.getById(id);
    }

    @PostMapping("")
    public List<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employee){
        return employeeService.createEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public List<EmployeeEntity> deleteEmployee(@PathVariable(value = "id") Integer id){
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/{id}")
    public List<EmployeeEntity> updateEmployee(@PathVariable(value = "id") Integer id, @RequestBody EmployeeEntity employee) {
        return employeeService.updateEmployee(id, employee);
    }
}
