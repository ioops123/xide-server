package com.xide.jpa.repository;

import com.xide.jpa.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, String> {

    @Query("SELECT a FROM Users a WHERE a.openid = ?1")
    Users findByOpenid(String openid);
}