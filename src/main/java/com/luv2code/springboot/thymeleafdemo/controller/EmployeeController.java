package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/showAddEmployee")
    public String showAddEmployee(Model employeeModel) {
        Employee employee = new Employee();
        employeeModel.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @GetMapping("/showUpdateEmployee")
    public String showUpdateEmployee(@RequestParam("employeeId") int employeeId, Model employeeModel) {
        Employee employee = employeeService.findById(employeeId);
        employeeModel.addAttribute("employee", employee);
        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {
        employeeService.deleteById(employeeId);
        return "redirect:/employees/list";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    // add mapping for "/list
    @GetMapping("/list")
    public String listEmployees(Model employeeModel) {
        // fetch employees from db
        List<Employee> employeesList = employeeService.findAllOrderLastNameAsc();
        // add results to model
        employeeModel.addAttribute("employees", employeesList);
        return "employees/list-employees";
    }
}
