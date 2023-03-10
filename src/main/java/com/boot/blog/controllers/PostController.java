package com.boot.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boot.blog.config.AppConstants;
import com.boot.blog.entities.PostEntity;
import com.boot.blog.exceptions.ApiResponse;
import com.boot.blog.payloads.PostResponse;
import com.boot.blog.services.FileService;
import com.boot.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	
	@Autowired
	private PostService postService;
	
	@Autowired 
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	
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
	public ResponseEntity<PostResponse> getAllPosts(
			
			@RequestParam(value="pageNumber", defaultValue=AppConstants.PAGE_NUMBER, required=false) int pageNumber,
			@RequestParam(value="pageSize", defaultValue=AppConstants.PAGE_SIZE, required=false) int pageSize,
			@RequestParam(value="sortBy", defaultValue = "postId", required=false) String sortBy
			)
	{
		PostResponse allPosts = this.postService.getAllPosts(pageNumber, pageSize, sortBy);
		
		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
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
	
	
	// Search Post
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostEntity>> searchPostByKeyword(@PathVariable String keyword)
	{
		List<PostEntity> searchPosts = this.postService.searchByKeyword(keyword);
		return new ResponseEntity<List<PostEntity>>(searchPosts, HttpStatus.OK);
	}
	
	
	
	//upload image
	@PostMapping("/post/upload/image/{postId}")
	public ResponseEntity<PostEntity> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("postId") int postId) throws IOException
	{
		PostEntity postById = this.postService.getPostById(postId);
		
		String uploadImage = this.fileService.uploadImage(path, image);
		
		postById.setImageName(uploadImage);
		
		PostEntity updatePost = this.postService.updatePost(postById, postId);
		
		return new ResponseEntity<PostEntity>(updatePost,HttpStatus.OK);
	}
	
	
	
	// serve/Display the image
	@GetMapping(value = "/post/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)

	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException
	{
		InputStream content = this.fileService.getImage(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(content, response.getOutputStream());

	}
}
