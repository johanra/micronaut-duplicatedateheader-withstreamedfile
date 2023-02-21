package com.company;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import io.micronaut.http.server.types.files.StreamedFile;

@Controller("/api/document/")
public class DocumentController {
	
	public DocumentController() {
	}
	
	 @Get(uri = "/info")
	 public String returnString() {
		 return "This is a string";
	 }

	 @Get(uri = "/download")
	 public StreamedFile download() {
		 String someString = "This is a string to download";
		
		 InputStream targetStream = new ByteArrayInputStream(someString.getBytes());
		 return new StreamedFile(targetStream, MediaType.TEXT_PLAIN_TYPE);
	 }
}
