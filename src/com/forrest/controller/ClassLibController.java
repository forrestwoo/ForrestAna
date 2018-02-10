package com.forrest.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ClassLibController {

	@RequestMapping("/getClassLib")
	public String getClassLib()
	{
		String baseUrl = "http://mvnrepository.com/open-source";
		return "getClassLib";
				
	}
}
