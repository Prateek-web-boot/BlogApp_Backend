package com.boot.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.blog.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
