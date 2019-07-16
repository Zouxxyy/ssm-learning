package com.zouxxyy.springboot.mapper;

import com.zouxxyy.springboot.Bean.Department;
import org.apache.ibatis.annotations.*;

//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    int updateDept(Department department);
}
