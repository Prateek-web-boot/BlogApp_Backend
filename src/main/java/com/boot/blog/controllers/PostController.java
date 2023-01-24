package com.boot.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.blog.entities.PostEntity;
import com.boot.blog.entities.UserEntity;
import com.boot.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	
	@Autowired
	private PostService postService;
	
	//create new post
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostEntity> addNewPost(@RequestBody PostEntity ps, @PathVariable int userId, @PathVariable int categoryId)
	{
		PostEntity newPost = this.postService.addPost(ps, userId, categoryId);
		
		return new ResponseEntity<PostEntity>(newPost, HttpStatus.CREATED); 
	}
	
	
	// get all post
	
	
	//get all posts by User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostEntity>> getPostsByUser(@PathVariable int userId)
	{
		
		List<PostEntity> allPostByUser = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostEntity>>(allPostByUser, HttpStatus.OK);
	}
	
	
	//get all posts By Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostEntity>> getPostsByCategory(@PathVariable int categoryId)
	{
		
		List<PostEntity> allPostByCategory = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostEntity>>(allPostByCategory, HttpStatus.OK);
	}

}
