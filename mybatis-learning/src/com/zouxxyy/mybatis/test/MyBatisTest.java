package com.zouxxyy.mybatis.test;

import com.zouxxyy.mybatis.bean.Department;
import com.zouxxyy.mybatis.bean.Employee;
import com.zouxxyy.mybatis.dao.DepartmentMapper;
import com.zouxxyy.mybatis.dao.EmployeeMapper;
import com.zouxxyy.mybatis.dao.EmployeeMapperDynamicSQL;
import com.zouxxyy.mybatis.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MyBatisTest {

    // 方式 1 ，旧版本
    public static void test1() throws IOException {

        // 1、获取sqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        // 2、获取sqlSession对象
        // SqlSession 代表和数据库的一次会话，用完必须关闭。它是非线程安全的，每次使用都应该去获取新的对象。
        SqlSession openSession1 = sqlSessionFactory.openSession();

        // 3. 执行已经映射的sql语句
        Employee employee = openSession1.selectOne("com.zouxxyy.EmployeeMapper.selectEmployee", 1);
        System.out.println(employee);
        openSession1.close();
    }


    // 方式 2， 接口式编程，一般用这种
    public static void test2() throws IOException {
        // 1、获取sqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        // 3. 获取接口的实现类对象
        // EmployeeMapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象 mapper。（接口和 sql映射xml 进行绑定）
        EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmpById(1);
        System.out.println(employee);
        openSession.close();

    }

    // 增删改查测试
    public static void test3() throws IOException {
        // 1、获取sqlSessionFactory对象，它不会自动提交
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        try{
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            // 查找 返回列表
            // List<Employee> employeeList  = mapper.getEmpByLastName("Cat");
            // for (Employee employee : employeeList) {
            //     System.out.println(employee);
            // }

            // 查找 返回map
            // Map<Integer, Employee> integerEmployeeMap = mapper.getEmptyByLastNameReturnMap("Cat");
            // System.out.println(integerEmployeeMap);

            // 多个参数查找 方式1
            // Employee employeeGet = mapper.getEmpByIdAndLastName(1, "Cat");
            // System.out.println(employeeGet);

            // 多个参数查找 方式2 使用map
            // Map<String, Object> map = new HashMap<>();
            // map.put("id", 1);
            // map.put("lastName", "Cat");
            // Employee employeeGetByMap = mapper.getEmpByMap(map);
            // System.out.println(employeeGetByMap);

            // 添加
            // Employee employee = new Employee(null, "Cat", "cat@163.com", "1");
            // mapper.addEmp(employee);
            // System.out.println(employee.getId());

            // 更新
            // Employee employee = new Employee(2, "Cat", "cat@163.com", "1");
            // mapper.updateEmp(employee);

            // 删除
            // mapper.deleteEmpById(2);

            // 手动提交
            openSession.commit();
        }finally {
            openSession.close();
        }
    }

    // resultMap测试
    public static void test4() throws IOException {
        // 1、获取sqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();


        try {
            // 3. 获取接口的实现类对象
            // EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);

            // 简单的resultMap
            // Employee employee = mapper.getEmpById(1);
            // System.out.println(employee);

            // resultMap集联
            // Employee employee = mapper.getEmpAndDept(1);
            // System.out.println(employee);

            // resultMap分步查询(bean中含单个对象)
            // Employee employee = mapper.getEmpByIdStep(1);
            // System.out.println(employee);

            // resultMap分步查询(bean中含集合)

            Department department = mapper.getDeptByIdStep(2);
            System.out.println(department);
        }
        finally {
            openSession.close();
        }
    }

    // 动态sql测试
    public static void test5() throws IOException {
        // 1、获取sqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            // 3. 获取接口的实现类对象
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);


            // 动态sql :if , where, trim, choose, foreach 测试
            // Employee employee = new Employee(null, "Cat", null, null);
            // List<Employee> employeeList = mapper.getEmpsByConditionIf(employee);
            // List<Employee> employeeList1 = mapper.getEmpsByConditionTrim(employee);
            // List<Employee> employeeList2 = mapper.getEmpsByConditionChoose(employee);
            // List<Employee> employeeList3 = mapper.getEmpsByConditionForeach(Arrays.asList(1,3));
            // for (Employee e : employeeList3) {
            //     System.out.println(e);
            // }

            // 动态sql : set 测试
            // Employee employee = new Employee(3, "Dog", null, "0");
            // mapper.updateEmp(employee);
            // openSession.commit();

            // 一次添加多个对象
            // List<Employee> emps = new ArrayList<>();
            // emps.add(new Employee(null, "May", "May@163.com", "0", new Department(1)));
            // emps.add(new Employee(null, "Big", "Big@163.com", "1", new Department(2)));
            // mapper.addEmps(emps);
            // openSession.commit();

            // 测试内置的参数
             List<Employee> employeeList = mapper.getEmpsTestInnerParameter(new Employee(null, "a", null, null));
             for (Employee e : employeeList) {
                 System.out.println(e);
             }
        }
        finally{
            openSession.close();
        }
    }

    public static void main(String[] args) throws IOException {

//        MyBatisTest.test1();
//        MyBatisTest.test2();
//        MyBatisTest.test3();
//        MyBatisTest.test4();
        MyBatisTest.test5();
    }
}
