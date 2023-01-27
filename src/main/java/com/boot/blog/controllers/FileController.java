package com.boot.blog.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.boot.blog.payloads.FileResponse;
import com.boot.blog.services.FileService;

import jakarta.servlet.http.HttpServletResponse;

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
	
	
	
	// serve / Display or download Image
	
	@GetMapping(value = "/download/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)

	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException
	{
		InputStream content = this.fileService.getImage(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(content, response.getOutputStream());
		
	}


}
