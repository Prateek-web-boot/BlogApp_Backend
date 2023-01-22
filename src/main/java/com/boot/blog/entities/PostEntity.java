package com.boot.blog.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="posts")
public class PostEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date postDate;
	
	
	@ManyToOne
	@JoinColumn(name = "user_ID")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "category_ID")
	private CategoryEntity category;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "PostEntity [postId=" + postId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", postDate=" + postDate + ", user=" + user + ", category=" + category + "]";
	}

	public PostEntity(int postId, String title, String content, String imageName, Date postDate, UserEntity user,
			CategoryEntity category) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.postDate = postDate;
		this.user = user;
		this.category = category;
	}

	public PostEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}
