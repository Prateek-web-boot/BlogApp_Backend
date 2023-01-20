package com.boot.blog.exceptions;

public class ApiResponse {

	private String message;
	private boolean status;
	
	
	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", status=" + status + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public ApiResponse(String message, boolean status) {
		super();
		this.message = message;
		this.status = status;
	}
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
