package com.zouxxyy.mybatis.dao;

import com.zouxxyy.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

// 优点： 更强的类型检查
public interface EmployeeMapper {

    // 告诉mybatis封装这个map时候用哪个属性作为map的key
    @MapKey("id")
    public Map<Integer, Employee>  getEmptyByLastNameReturnMap(String lastName);

    public Employee getEmpById(Integer id);

    public List<Employee> getEmpByLastName(String lastName);

    // 多个参数 方式1： 可以添加装饰器，指定参数名
    public Employee getEmpByIdAndLastName(@Param("id")Integer id, @Param("lastName")String lastName);

    // 多个参数 方式2： 使用map传入
    public Employee getEmpByMap(Map<String, Object> map);

    public void addEmp(Employee employee);

    public void updateEmp(Employee employee);

    public void deleteEmpById(Integer id);

}
