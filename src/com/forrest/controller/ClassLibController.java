package com.forrest.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.parse.BaiduImageParse;

public class ClassLibController {

	@RequestMapping("/getClassLib")
	public String getClassLib() throws Exception
	{
		BaiduImageParse.getImages();
		return "initData";
				
	}
}
