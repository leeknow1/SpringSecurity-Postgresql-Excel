package com.example.springproject.controller;

import com.example.springproject.entity.EmployeeEntity;
import com.example.springproject.exceling.ExcelExporter;
import com.example.springproject.repository.EmployeeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    ExcelExporter ex = new ExcelExporter();

    @Autowired
    private EmployeeEntityRepository employeeEntityRepository;

    @GetMapping("")
    public String getAll(Model model){
        List<EmployeeEntity> emp =employeeEntityRepository.findAll();
        model.addAttribute("employee", emp);

        return "employeePage";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable(value = "id") Integer id, Model model){
        EmployeeEntity emp = employeeEntityRepository.findById(id).orElseThrow();
        model.addAttribute("employee", emp);

        return "employeePage";
    }

    @PostMapping("")
    public String createEmployee(@RequestBody EmployeeEntity employee) {
        employeeEntityRepository.save(employee);

        ex.export();

        return "redirect:/employee";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Integer id){
        EmployeeEntity emp = employeeEntityRepository.findById(id).orElseThrow();
        employeeEntityRepository.delete(emp);

        ex.export();

        return "redirect:/employee";
    }

    @PutMapping("/{id}")
    public  String updateEmployee(@PathVariable Integer id, @RequestBody EmployeeEntity employeeDetails) {
        EmployeeEntity employee = employeeEntityRepository.findById(id).orElseThrow();

        if (employeeDetails.getLastname() != null)
            employee.setLastname(employeeDetails.getLastname());

        if (employeeDetails.getFirstname() != null)
            employee.setFirstname(employeeDetails.getFirstname());

        if (employeeDetails.getFathername() != null)
            employee.setFathername(employeeDetails.getFathername());

        if (employeeDetails.getBirthday() != null)
            employee.setBirthday(employeeDetails.getBirthday());

        if (employeeDetails.getId_position() != null)
            employee.setId_position(employeeDetails.getId_position());

        if (employeeDetails.getId_rank() != null)
            employee.setId_rank(employeeDetails.getId_rank());

        if (employeeDetails.getId_division() != null)
            employee.setId_division(employeeDetails.getId_division());

        if (employeeDetails.getContract_conclusion() != null)
            employee.setContract_conclusion(employeeDetails.getContract_conclusion());

        if(employeeDetails.getContract_term()!=null)
            employee.setContract_term(employeeDetails.getContract_term());


        EmployeeEntity updatedEmployee = employeeEntityRepository.save(employee);

        ex.export();

        return "redirect:/employee";
    }


}
