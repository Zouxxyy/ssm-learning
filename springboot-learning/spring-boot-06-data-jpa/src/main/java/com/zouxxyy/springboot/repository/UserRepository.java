package com.zouxxyy.springboot.repository;

import com.zouxxyy.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 继承JpaRepository完成操作
public interface UserRepository extends JpaRepository<User, Integer> {
}