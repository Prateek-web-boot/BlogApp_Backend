package com.boot.blog.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boot.blog.payloads.FileResponse;
import com.boot.blog.services.FileService;

@RestController
@RequestMapping("/image")
public class FileController {
	
	// used to extract the value set in application.properties file
	@Value("${project.image}")
	private String path;

	@Autowired
	private FileService fileService;
	
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> uploadImage(@RequestParam("image") MultipartFile image)
	{
		
		String fileName = null;
		try
		{
			fileName = this.fileService.uploadImage(path, image);
		}
		catch(IOException e)
		{
			return new ResponseEntity<>(new FileResponse(null, "Error while uploading Image!"), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		return new ResponseEntity<>(new FileResponse(fileName, "Image Uploaded successfully!"), HttpStatus.OK);
		
	}


}
