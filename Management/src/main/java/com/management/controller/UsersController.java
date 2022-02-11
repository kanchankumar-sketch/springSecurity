package com.management.controller;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.management.entity.Users;
import com.management.repositry.UsersRepo;

@Controller
@RequestMapping("/management")
public class UsersController implements ErrorController{
	@Autowired
	private UsersRepo repo;
	@RequestMapping("/")
	public ModelAndView home(Users user,Principal principal)
	{
		ModelAndView model=new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> s=auth.getAuthorities();
		System.out.println(s.toString());
		String role=s.toString();
		if(role!=null)
		{
			model.addObject("role",role);
		}
		String name=principal.getName();
		
		model.addObject("name",name);
		model.setViewName("home");
		return model;
	}
	
	@PostMapping(value="/addUser",consumes = "application/x-www-form-urlencoded")
	public ModelAndView addUser(Users user)
	{
		
		BCryptPasswordEncoder br=new BCryptPasswordEncoder();
		user.setPassword(br.encode(user.getPassword()));
		user=repo.save(user);
		System.out.println(user);
		ModelAndView model=new ModelAndView();
		model.setViewName("redirect:/management/");
		return model;
	}
	
	@RequestMapping("/error")
	public ModelAndView handler1(HttpServletRequest req, Exception ex, ModelAndView andView) {
		andView.setViewName("redirect:/");

		return andView;

	}
	
	@RequestMapping("/viewUsers")
	public ModelAndView admin(ModelAndView andView)
	{
		andView.setViewName("admin");
		return andView;
	}

}
