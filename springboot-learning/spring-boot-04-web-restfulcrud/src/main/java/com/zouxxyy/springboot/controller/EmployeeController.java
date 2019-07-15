package com.zouxxyy.springboot.controller;

import com.zouxxyy.springboot.dao.DepartmentDao;
import com.zouxxyy.springboot.dao.EmployeeDao;
import com.zouxxyy.springboot.entities.Department;
import com.zouxxyy.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    // 查询所有员工，放回列表页面
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    // 进入员工添加界面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        // 先查所有的部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    // 员工添加提交
    // SpringMvc 自动绑定（请求参数名字一致）
    @PostMapping("/emp")
    public String addEmp(Employee employee){

        employeeDao.save(employee);
        // 重定向
        return "redirect:/emps";
    }

    // 员工修改，通过id确定
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        // 先查所有的部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        // 查员工
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        return "emp/add";
    }

    // 员工修改提交
    @PutMapping("/emp")
    public String updateEmployee(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

}
