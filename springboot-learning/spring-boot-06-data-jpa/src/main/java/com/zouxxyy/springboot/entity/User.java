package com.zouxxyy.springboot.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// 使用jpa注解配置映射关系
@Entity
@Table(name = "tbl_user")
public class User {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键自增
    private Integer id;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
