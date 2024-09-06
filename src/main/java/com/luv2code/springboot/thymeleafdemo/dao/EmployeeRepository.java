package com.luv2code.springboot.thymeleafdemo.dao;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

    // sort by last name; note the name of this function correspond to the sequence to
    // find all employee by order by last name ascending (jpa query methods)
    public List<Employee> findAllByOrderByLastNameAsc();

}
