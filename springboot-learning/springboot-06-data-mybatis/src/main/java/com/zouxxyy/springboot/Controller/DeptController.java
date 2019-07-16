package com.zouxxyy.springboot.Controller;


import com.zouxxyy.springboot.Bean.Department;
import com.zouxxyy.springboot.Bean.Employee;
import com.zouxxyy.springboot.mapper.DepartmentMapper;
import com.zouxxyy.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class DeptController {

    @Resource
    DepartmentMapper departmentMapper;

    @Resource
    EmployeeMapper employeeMapper;


    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentMapper.getDeptById(id);
    }

    @GetMapping("/dept")
    @Transactional
    public Department insertDept(Department department) {
        departmentMapper.insertDept(department);
//        System.err.println("Inserted successfully");
//        System.err.println("---> 1/0 = " + (1 / 0));
        return department;
    }

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return employeeMapper.getEmpById(id);
    }

}