package com.forrest.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forrest.dao.UserDao;
import com.forrest.model.User;

@Controller
public class UserController {
	private UserDao userDao;
	@Autowired
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	@RequestMapping(value = "/findUser")
	public String finduser(@RequestParam("userName") int userId,Model model) {
		User user = userDao.findUserById(userId);
		if (user == null) {
			return "index";
		}
		model.addAttribute("user",user);
		return "findUser";
	}

	@RequestMapping("/")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping(value = "/user/register")
	public String showRegisterForm()
	{
		return "registerForm";
	}
	
	@RequestMapping(value = "/tab")
	public String tab() {
		return "tab";
	}
	
	@RequestMapping(value = "/userlist")
	public String setting() {
		return "userList";
	}
}
