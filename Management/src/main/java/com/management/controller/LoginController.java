package com.management.controller;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.management.entity.Users;

@Controller
public class LoginController {

	@RequestMapping("/")
	public ModelAndView getPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}

	@GetMapping("/signup")
	public ModelAndView signUp(Users user) {
		ModelAndView model = new ModelAndView();
		model.addObject("users", user);
		model.setViewName("signup");
		return model;
	}

	@GetMapping("/login")
	public ModelAndView login(HttpServletRequest req, ModelAndView andView, Principal principal) {

		if (principal == null) {
			String s = req.getParameter("logout");
			if (s != null) {
				andView.addObject("message", "Logout success");
			}
			String errr = req.getParameter("error");
			if (errr != null) {
				andView.addObject("error", "Login Failed");
			}
			andView.setViewName("login");
			return andView;
		}
		else
		{
			
			andView.setViewName("redirect:/management/");
			return andView;
		}
	}

}
