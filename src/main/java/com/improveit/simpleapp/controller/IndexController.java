package com.improveit.simpleapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.CookieGenerator;

import com.improveit.simpleapp.model.UserScope;

@Controller
public class IndexController {
	
	private final String[] STEPS = {"first", "second", "third"};  
	
	@Autowired
	private CookieGenerator cookieGenerator;
	
	@Autowired
	private UserScope userScope;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(ModelMap model) {
		userScope.setStep(0);
		model.addAttribute("name", userScope.getUser().getFirstName());
		userScope.getUser().setFirstName("Test");
		return "cabinet";
	}
	
	@RequestMapping(value="/define", method=RequestMethod.GET)
	public String define() {
		return "index";
	}
	
	@RequestMapping(value="/define", method=RequestMethod.POST)
	public String defining(HttpServletResponse response) {
		// TODO after spring security
		return "first";
	}
	
	@RequestMapping(value="/next_step", method=RequestMethod.GET)
	public String next_step(HttpServletRequest request, HttpServletResponse response) {
		return "error";
	}

}
