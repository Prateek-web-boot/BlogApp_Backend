package com.boot.blog.payloads;

import java.util.List;

import com.boot.blog.entities.PostEntity;

public class PostResponse {
	
	private List<PostEntity> content;
	private int pageNumber;
	private int pageSize;
	private int totalPage;
	private long totalElements;
	private boolean lastPage;
	public List<PostEntity> getContent() {
		return content;
	}
	public void setContent(List<PostEntity> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long l) {
		this.totalElements = l;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	@Override
	public String toString() {
		return "PostResponse [content=" + content + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", totalPage=" + totalPage + ", totalElements=" + totalElements + ", lastPage=" + lastPage + "]";
	}
	public PostResponse(List<PostEntity> content, int pageNumber, int pageSize, int totalPage, int totalElements,
			boolean lastPage) {
		super();
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.totalElements = totalElements;
		this.lastPage = lastPage;
	}
	public PostResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	

}
