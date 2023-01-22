package com.boot.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.blog.entities.CategoryEntity;
import com.boot.blog.exceptions.ApiResponse;
import com.boot.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	//add a category
	
	@PostMapping("/add_category")
	public ResponseEntity<CategoryEntity> addNewCategory(@Valid @RequestBody CategoryEntity cat)
	{
		CategoryEntity addCategory = this.categoryService.addCategory(cat);
		return new ResponseEntity<CategoryEntity>(addCategory, HttpStatus.CREATED);
	}
	
	
	
	
	//get all the categories
	@GetMapping("/")
	public ResponseEntity<List<CategoryEntity>> getAllCategory()
	{
		List<CategoryEntity> getAllCategory = this.categoryService.getAllCategory();
		return new ResponseEntity<List<CategoryEntity>>(getAllCategory, HttpStatus.OK);
	}
	
	//get Category by ID
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryEntity> getCategoryById(@PathVariable int catId)
	{
		CategoryEntity categoryById = this.categoryService.getCategoryById(catId);
		return new ResponseEntity<CategoryEntity>(categoryById, HttpStatus.OK);
	}
	
	
	@PutMapping("/updateCat/{catId}")
	public ResponseEntity<CategoryEntity> updateCategory(@Valid @RequestBody CategoryEntity cat, @PathVariable("catId") int catId)
	{
		CategoryEntity updateCategoryById = this.categoryService.updateCategory(cat, catId);
		return new ResponseEntity<CategoryEntity>(updateCategoryById, HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") int catId)
	{
		this.categoryService.deleteCategory(catId);
		ApiResponse apiResponse = new ApiResponse("Category Deleted!", true);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
	
	

}
