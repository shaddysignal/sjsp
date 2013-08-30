package com.improveit.simpleapp.controller;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.improveit.simpleapp.model.Steps;
import com.improveit.simpleapp.model.User;
import com.improveit.simpleapp.services.UserService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("user", userService.getCurrentUser());
		return "finale";
	}
	
	@RequestMapping(value="/define", method=RequestMethod.GET)
	public String define(ModelMap model) {
		return "define";
	}
	
	@RequestMapping(value="/define", method=RequestMethod.POST)
	public String defining(@RequestParam("email") String uemail, @RequestParam("password") String upassword) {
		Map<String, String> params = new Hashtable<String, String>(2, 1);
		params.put("email", uemail);
		params.put("password", upassword);
		List<User> login = userService.getUsersByParams(params);
		if(login.isEmpty())
			return "error";
		userService.setUser(login.get(0), Steps.finale);
		return "redirect:/";
	}
	
	@RequestMapping(value="/undefine", method=RequestMethod.DELETE)
	public String undefining(@RequestParam User user) {
		userService.setUser(new User(), Steps.define);
		return "redirect:/define";
	}
	
	@RequestMapping(value="/first", method=RequestMethod.GET)
	public String first(ModelMap model) {
		model.addAttribute("user", userService.getCurrentUser());
		userService.setUserStep(Steps.first);
		return "first";
	}
	
	@RequestMapping(value="/next_step", method=RequestMethod.POST)
	public String next_step(@RequestParam Map<String, String> values, User user, ModelMap model) {		
		Map<String, String> errors = userService.validate(values);
		if(errors.isEmpty()) {
			userService.setUserStep(userService.getUserStep().next());
			userService.putUser(user);			
		} else {
			model.addAttribute("errors", errors);
		}		
		model.addAttribute("user", user);
		return userService.getUserStep().toString();
	}
	
	@RequestMapping(value="/finale", method=RequestMethod.POST)
	public String finale(@RequestParam Map<String, String> values, User user, ModelMap model) {
		model.addAttribute("user", user);
		userService.userDone();
		return "finale";
	}

}
