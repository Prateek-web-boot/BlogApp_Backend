package com.boot.blog.serviceImplemetation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.blog.entities.CategoryEntity;
import com.boot.blog.exceptions.ResourceNotFoundException;
import com.boot.blog.repositories.CategoryRepository;
import com.boot.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public CategoryEntity addCategory(CategoryEntity cat) {
		return this.categoryRepo.save(cat);
	}

	@Override
	public List<CategoryEntity> getAllCategory() {
		List<CategoryEntity> allCategory = this.categoryRepo.findAll();
		return allCategory;
	}

	@Override
	public CategoryEntity getCategoryById(int catId) {
		return this.categoryRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", catId));
	}

	@Override
	public CategoryEntity updateCategory(CategoryEntity cat, int catId) {
		
		if(!this.categoryRepo.existsById(catId))
		{
			throw new ResourceNotFoundException("Category","Id",catId);
		}
		else
		{
			return this.categoryRepo.save(cat);
		}
	}

	@Override
	public void deleteCategory(int catId) {
		
		if(!this.categoryRepo.existsById(catId))
		{
			throw new ResourceNotFoundException("Category","Id",catId);
		}
		else
		{
			this.categoryRepo.deleteById(catId);
		}
		
	}

}
