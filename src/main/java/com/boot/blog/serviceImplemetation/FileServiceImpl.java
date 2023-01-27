package com.boot.blog.serviceImplemetation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.boot.blog.services.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		// getting Original Filename  -> abc.png
		
		String fileName = file.getOriginalFilename();
		
		//changing the original filename to some random String Name using UUID	
		String randomID = UUID.randomUUID().toString();
		
		
				
		//extracting the extension of the image using substring 
		String newFileName = randomID.concat(fileName.substring(fileName.lastIndexOf(".")));
				
		//creating Full path   ex: -> C:user/images/abc.png
		String filePath = path + File.separator + newFileName;
		
			
		// creating Image Folder if not created
		File f = new File(path);
		
		if(!f.exists())
		{
			f.mkdir();
		}
		
		
		//file copy		
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}

	
	
	@Override
	public InputStream getImage(String path, String fileName) throws FileNotFoundException {
		
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
		
		
		return is;
	}

}
