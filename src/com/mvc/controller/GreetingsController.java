package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.HasAccess;
import com.mvc.Role;

@Controller
public class GreetingsController {
	
	@HasAccess(value = Role.ADMIN)
	@RequestMapping(value="/hello-page")
	public ModelAndView goToHelloPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("hello"); //name of the jsp-file in the 'page' folder

		String str = "Welcome to MVC Spring.";
		view.addObject("message", str); //adding of str object as 'message' parameter

		return view;
	}

}
