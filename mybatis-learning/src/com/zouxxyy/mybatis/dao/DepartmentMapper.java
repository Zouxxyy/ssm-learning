package com.zouxxyy.mybatis.dao;

import com.zouxxyy.mybatis.bean.Department;

public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public Department getDeptByIdStep(Integer id);

}
