package com.boot.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
