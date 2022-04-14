package com.stylclos.image_folder_handling;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class FolderHandling {
	
	public FolderHandling() throws IOException {
		// TODO Auto-generated constructor stub
//		uploadFilePath = 
		System.out.println("ctor folder handling : " );
	}
	
	public boolean createDir(String folderName) {
		
		System.out.println("filePath : " + folderName);
		
		File file = new File(folderName);
		
		if(file.exists())
			return true;
		
		if(file.mkdir()) {
			System.out.println("Folder created on path : " + folderName);
			System.out.println(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getAbsolutePath().toString()));
			return true;
		}
		
		System.out.println("Can't create folder");
		
		return false;
	}

}
