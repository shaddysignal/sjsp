package com.improveit.simpleapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.improveit.simpleapp.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("users", userService.getAllUsers());
		return "index";
	}
	
	@RequestMapping(value = "/delete/#{id}")
	public String deleleUser() {
		return "index";
	}
	
}
