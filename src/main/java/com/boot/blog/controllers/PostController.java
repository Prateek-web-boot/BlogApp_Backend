package com.boot.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.blog.entities.PostEntity;
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

}
