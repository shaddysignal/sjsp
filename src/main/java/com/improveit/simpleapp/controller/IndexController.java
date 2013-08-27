package com.improveit.simpleapp.controller;

import java.util.EnumMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.improveit.simpleapp.model.Steps;
import com.improveit.simpleapp.model.User;
import com.improveit.simpleapp.services.UserService;
import com.improveit.simpleapp.services.validators.UserValidator.Rules;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("user", userService.getCurrentUser());
		return userService.getUserStep().toString();
	}
	
	@RequestMapping(value="/define", method=RequestMethod.GET)
	public String define(ModelMap model) {
		model.addAttribute("user", userService.getCurrentUser());
		return "index";
	}
	
	@RequestMapping(value="/define", method=RequestMethod.POST)
	public String defining(HttpServletResponse response) {
		// TODO after spring security
		return "first";
	}
	
	@RequestMapping(value="/undefine", method=RequestMethod.DELETE)
	public String undefining(@RequestParam User user) {
		// TODO after spring security
		return "index";
	}
	
	@RequestMapping(value="/first", method=RequestMethod.GET)
	public String first(ModelMap model) {
		model.addAttribute("user", userService.getCurrentUser());
		userService.setUserStep(Steps.first);
		return "first";
	}
	
	@RequestMapping(value="/next_step", method=RequestMethod.POST)
	public String next_step(@RequestParam Map<String, String> values, User user, ModelMap model) {
		model.addAttribute("user", user);
	 	Map<Rules, String> ruleToValue = new EnumMap<Rules, String>(values);
		Map<String, String> errors = userService.validate(values);
		if(errors.isEmpty()) {
			userService.setUserStep(userService.getUserStep().next());
			userService.putUser(user);
		} else {
			model.addAttribute("errors", errors);
		}
		return userService.getUserStep().toString();
	}
	
	@RequestMapping(value="/finale", method=RequestMethod.POST)
	public String finale(@RequestParam Map<String, String> values, User user, ModelMap model) {
		model.addAttribute("user", user);
		userService.userDone();
		return "finale";
	}

}
