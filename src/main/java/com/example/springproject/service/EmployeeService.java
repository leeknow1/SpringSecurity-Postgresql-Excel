package com.example.springproject.service;

import com.example.springproject.entity.EmployeeEntity;
import com.example.springproject.exceling.ExcelExporter;
import com.example.springproject.repository.EmployeeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    ExcelExporter ex = new ExcelExporter();

    @Autowired
    private EmployeeEntityRepository employeeEntityRepository;

    public List<EmployeeEntity> getAll(){
        ex.export();
        return employeeEntityRepository.findAll();
    }

    public Optional<EmployeeEntity> getById(Integer id){
        if(employeeEntityRepository.findById(id).isEmpty()){
            throw new NoSuchElementException();
        };
        return employeeEntityRepository.findById(id);
    }

    public List<EmployeeEntity> createEmployee(@Valid EmployeeEntity employee){
        employeeEntityRepository.save(employee);
        ex.export();

        return employeeEntityRepository.findAll();
    }

    public List<EmployeeEntity> deleteEmployee(Integer id) {
        EmployeeEntity emp = employeeEntityRepository.findById(id).orElseThrow();
        employeeEntityRepository.delete(emp);
        ex.export();

        return employeeEntityRepository.findAll();
    }

    public List<EmployeeEntity> updateEmployee(Integer id, @Valid EmployeeEntity employeeEntity){
        EmployeeEntity employee = employeeEntityRepository.findById(id).orElseThrow();

            employee.setLastname(employeeEntity.getLastname());
            employee.setFirstname(employeeEntity.getFirstname());
            employee.setFathername(employeeEntity.getFathername());
            employee.setBirthday(employeeEntity.getBirthday());
            employee.setId_position(employeeEntity.getId_position());
            employee.setId_rank(employeeEntity.getId_rank());
            employee.setId_division(employeeEntity.getId_division());
            employee.setContract_conclusion(employeeEntity.getContract_conclusion());
            employee.setContract_term(employeeEntity.getContract_term());

        employeeEntityRepository.save(employee);

        ex.export();

        return employeeEntityRepository.findAll();
    }

    public int getLast(){
        List<EmployeeEntity> employee = employeeEntityRepository.findAll();
        EmployeeEntity emp = employee.get(employee.size()-1);
        return emp.getId();
    }
}
