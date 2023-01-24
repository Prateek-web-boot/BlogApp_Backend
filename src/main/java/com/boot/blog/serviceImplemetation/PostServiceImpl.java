package com.boot.blog.serviceImplemetation;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.blog.entities.CategoryEntity;
import com.boot.blog.entities.PostEntity;
import com.boot.blog.entities.UserEntity;
import com.boot.blog.exceptions.ResourceNotFoundException;
import com.boot.blog.repositories.CategoryRepository;
import com.boot.blog.repositories.PostRepository;
import com.boot.blog.repositories.UserRepository;
import com.boot.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	

	@Override
	public PostEntity addPost(PostEntity post, int userId, int categoryId) {
		
		UserEntity user = this.userRepo.findById(userId).get();
		CategoryEntity category = this.categoryRepo.findById(categoryId).get();
		
		post.setImageName("default.png");
		post.setPostDate(new Date(0));
		post.setCategory(category);
		post.setUser(user);
		
		return this.postRepo.save(post);
		
	}

	@Override
	public PostEntity updatePost(PostEntity post, int postId) {
		// TODO Auto-generated method stub
		
		if(!this.postRepo.existsById(postId))
		{
			throw new ResourceNotFoundException("Post", "Post Id", postId);
			
		}
		else
		{
			return this.postRepo.save(post);
		}
	}

	@Override
	public void deletePost(int postId) {

		if(!this.postRepo.existsById(postId))
		{
			throw new ResourceNotFoundException("Post", "Post Id", postId);
		}
		else
		{
			this.postRepo.deleteById(postId);
		}
		
	}

	@Override
	public List<PostEntity> getAllPosts() {
		
		List<PostEntity> allPOsts = this.postRepo.findAll();
		return allPOsts;
		}

	@Override
	public PostEntity getPostById(int postId) {
		
		PostEntity post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId));
		return post;
	}

	@Override
	public List<PostEntity> getPostsByUser(int userId) {
		// TODO Auto-generated method stub
		UserEntity user = this.userRepo.findById(userId).get();
		List<PostEntity> postByUser = this.postRepo.findByUser(user);
		return  postByUser;
	}

	@Override
	public List<PostEntity> getPostsByCategory(int categoryId) {

		CategoryEntity category = this.categoryRepo.findById(categoryId).get();
		List<PostEntity> postByCategory = this.postRepo.findByCategory(category);
		return postByCategory;
	}

	@Override
	public List<PostEntity> searchByKeyword(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

}
