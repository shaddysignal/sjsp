package com.improveit.simpleapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.improveit.simpleapp.model.User;
import com.improveit.simpleapp.services.UserService;

@Controller
@RequestMapping("/")
public class IndexController {
	
	private enum STEPS {
		finale(null), third(finale), second(third), first(second);
		
		private STEPS next;
		private STEPS(STEPS next) {
			this.next = next;
		}
		
		public STEPS next() {
			return next;
		}		
	};
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("user", userService.getCurrentUser());
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
	
	@RequestMapping(value="/undefine", method=RequestMethod.DELETE)
	public String undefining(@RequestParam User user) {		
		return "index";
	}
	
	@RequestMapping(value="/first", method=RequestMethod.GET)
	public String first(User user) {
		userService.setUserStep("first");
		return "first";
	}
	
	@RequestMapping(value="/next_step", method=RequestMethod.POST)
	public String next_step(@RequestParam Map<String, String> values, User user) {
		boolean valid = true;
		for(String key : values.keySet()) {
			valid = valid && userService.validate(key, values.get(key)) ? true : false;
		}
		if(valid) {
			// I know this horrible, but it seems like smooth solution
			userService.setUserStep(STEPS.valueOf(userService.getUserStep()).next().toString());
			userService.putUser(user);
		}
		return userService.getUserStep();
	}
	
	@RequestMapping(value="/finale", method=RequestMethod.POST)
	public String finale(@RequestParam Map<String, String> values, User user) {
		next_step(values, user);
		userService.putUser(user);
		return "cabinet";
	}

}
