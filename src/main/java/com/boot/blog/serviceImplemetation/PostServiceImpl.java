package com.boot.blog.serviceImplemetation;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.boot.blog.entities.CategoryEntity;
import com.boot.blog.entities.PostEntity;
import com.boot.blog.entities.UserEntity;
import com.boot.blog.exceptions.ResourceNotFoundException;
import com.boot.blog.payloads.PostResponse;
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
	public List<PostEntity> getAllPosts(int pageNumber, int pageSize) {
	
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		Page<PostEntity> pagePost = this.postRepo.findAll(p);
		
		List<PostEntity> allPosts = pagePost.getContent();
		return allPosts;
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
		
		List<PostEntity> searchResult = this.postRepo.searchByTitle("%"+keyWord+"%");
		//List<PostEntity> searchResult = this.postRepo.findByTitleContaining(keyWord);
		return searchResult;
	}

}
