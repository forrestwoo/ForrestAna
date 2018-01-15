package com.forrest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.istack.internal.logging.Logger;

@Controller
public class UserController {

	@RequestMapping(value = "/findUser")
	public String getUser() {
		return "findUser";
	}

	@RequestMapping("/")
	public String getIndex() {
		return "index";
	}
}
