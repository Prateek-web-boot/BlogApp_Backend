package com.boot.blog.services;

import java.util.List;

import com.boot.blog.entities.CategoryEntity;

public interface CategoryService {
	
	
	public CategoryEntity addCategory(CategoryEntity cat);
	
	public List<CategoryEntity> getAllCategory();
	
	public CategoryEntity getCategoryById(int catId);
	
	public CategoryEntity updateCategory(CategoryEntity cat, int catId);
	
	void deleteCategory(int catId);
	
	

}
