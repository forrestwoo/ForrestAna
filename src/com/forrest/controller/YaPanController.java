package com.forrest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.forrest.dao.MatchesDao;
import com.forrest.dao.YaPanDao;
import com.forrest.mapper.YaPanMapper;

@Controller
public class YaPanController {

	private YaPanDao yaPanDao;
	
	@Autowired
	private MatchesDao matchesDao;
	
	public YaPanController(YaPanDao yaPanDao)
	{
		this.yaPanDao = yaPanDao;
	}
	
	@RequestMapping("/insertYaPan")
	public String insertYaPan() throws Exception
	{
		
		return"insertYaPan";
	}
}
