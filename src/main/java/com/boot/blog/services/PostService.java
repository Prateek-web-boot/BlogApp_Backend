package com.boot.blog.services;

import java.util.List;

import com.boot.blog.entities.PostEntity;

public interface PostService {
	
	//create 
	public PostEntity addPost(PostEntity post, int userId, int categoryId);
	
	//update
	public PostEntity updatePost(PostEntity post, int postId);
	
	//delete	
	void deletePost(int postId);
	
	//get All post
	public List<PostEntity> getAllPosts();
	
	//getPost by Id
	public PostEntity getPostById(int postId);
	
	
	//get all post by user
	public List<PostEntity> getPostsByUser(int userId);
	
	//get all posts by category
	public List<PostEntity> getPostsByCategory(int categoryId);

}
