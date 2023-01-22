package com.boot.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.blog.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer>{

}
