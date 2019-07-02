package com.zouxxyy.mybatis.dao;

//动态sql功能测试

import com.zouxxyy.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public List<Employee> getEmpsByConditionChoose(Employee employee);

    public void updateEmp(Employee employee);

    public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);

    public void addEmps(@Param("emps") List<Employee> emps);


    // 测试内置参数
    public List<Employee> getEmpsTestInnerParameter(Employee employee);

}
