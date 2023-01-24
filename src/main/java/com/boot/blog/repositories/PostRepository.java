package com.boot.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.boot.blog.entities.CategoryEntity;
import com.boot.blog.entities.PostEntity;
import com.boot.blog.entities.UserEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{

	
	List<PostEntity> findByUser(UserEntity user);
	
	List<PostEntity> findByCategory(CategoryEntity category);
	
	//List<PostEntity> findByTitleContaining(String keyword);
	
	@Query("select p from PostEntity p where p.title like :key")
	List<PostEntity> searchByTitle(@Param("key") String title);
}
