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

import com.boot.blog.entities.PostEntity;
import com.boot.blog.exceptions.ApiResponse;
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
	
	
	// get all post
	
	@GetMapping("/allPosts")
	public ResponseEntity<List<PostEntity>> getAllPosts()
	{
		List<PostEntity> allPost = this.postService.getAllPosts();
		
		return new ResponseEntity<List<PostEntity>>(allPost, HttpStatus.OK);
	}

	
	// delete Post
	
	@DeleteMapping("/deletePost/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") int postId)
	{
		this.postService.deletePost(postId);
		ApiResponse response = new ApiResponse("Post Deleted",true);
		
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}
	
	
	// update Post
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostEntity> updatePost(@RequestBody PostEntity post, @PathVariable int postId)
	{
		PostEntity updatedPost = this.postService.updatePost(post, postId);
		
		return new ResponseEntity<PostEntity>(updatedPost, HttpStatus.CREATED);
	}
	
	
	//get Single post by ID
	@GetMapping("/singlePost/{postId}")
	public ResponseEntity<PostEntity> postById(@PathVariable int postId)
	{
		PostEntity singlePost = this.postService.getPostById(postId);
		return new ResponseEntity<PostEntity>(singlePost, HttpStatus.OK);
	}
	
}
