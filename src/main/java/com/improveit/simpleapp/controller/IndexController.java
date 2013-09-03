package com.improveit.simpleapp.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.improveit.simpleapp.model.Steps;
import com.improveit.simpleapp.model.User;
import com.improveit.simpleapp.services.UserService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		return "redirect:/step/finale.kitty";
	}
	
	@RequestMapping(value="/error", method=RequestMethod.GET)
	public String error() {
		return "error/error";
	}
	
	@RequestMapping(value="/define", method=RequestMethod.GET)
	public String define(ModelMap model) {
		return "define";
	}
	
	@RequestMapping(value="/successDefine", method=RequestMethod.GET)
	public String successDefine() {
		userService.setCurrentUser(userService.getFirstUser("email", SecurityContextHolder.getContext().getAuthentication().getName()));		
		return "redirect:/step/" + userService.getUserStep() + ".kitty";
	}
	
	@RequestMapping(value="/undefine", method=RequestMethod.GET)
	public String undefining() {
		return "/define";
	}
	
	@RequestMapping(value="/step/{step}", method=RequestMethod.GET)
	public String get_step(@PathVariable("step") String step, ModelMap model) {
		userService.setUserStep(Steps.valueOf(step));
		model.addAttribute("user", userService.getCurrentUser());
		return step;
	}
	
	@RequestMapping(value="/next_step", method=RequestMethod.POST)
	public String post_next_step(@RequestParam Map<String, String> values, User user, ModelMap model) {
		Map<String, String> errors = userService.validate(values);
		if(errors.isEmpty()) {
			Steps next = userService.getUserStep().next();
			userService.setUserStep(next);
			user.setStep(next);
			userService.mergeUser(user);
		} else {
			model.addAttribute("errors", errors);
		}		
		model.addAttribute("user", user);
		return userService.getUserStep().toString();
	}
	
	@RequestMapping(value="/finale", method=RequestMethod.POST)
	public String finale(@RequestParam Map<String, String> values, User user, ModelMap model) {
		Map<String, String> errors = userService.validate(values);
		if(!errors.isEmpty()) {
			model.addAttribute("user", user);
			model.addAttribute("errors", errors);
			return userService.getUserStep().toString();
		}
		user.setStep(Steps.finale);
		userService.mergeUser(user);
		Authentication auth = new UsernamePasswordAuthenticationToken(
				userService.loadUserByUsername(userService.getCurrentUser().getEmail()),
				new SimpleGrantedAuthority("ROLE_USER")
			);
		SecurityContextHolder.getContext().setAuthentication(auth);
		userService.userDone();
		return "redirect:/step/finale.kitty";
	}

}
