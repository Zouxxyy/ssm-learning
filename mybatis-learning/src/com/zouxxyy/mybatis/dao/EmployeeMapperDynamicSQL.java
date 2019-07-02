package com.zouxxyy.mybatis.dao;

//动态sql功能测试

import com.zouxxyy.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIf(Employee employee);
}
