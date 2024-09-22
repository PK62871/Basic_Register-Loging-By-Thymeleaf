package com.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.login.entity.User;
import com.login.service.UserService;
import com.login.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/openRegisterPage")
	public String loadRegisterPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user,Model model) {
		boolean status = userService.registerUser(user);
		
		if(status) {
			model.addAttribute("msg", "User Register SuccessFully");
		}else {
			model.addAttribute("error", "User Not Registerd ");
		}
		return "register";
		
	}
	
	@GetMapping("/loginPage")
	public String openLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "loginPage";
	}
	
	@PostMapping("/loginForm")
	public String submitLoginForm(@ModelAttribute("user") User user,Model model) {
		User validUser = userService.loginUser(user.getEmail(),user.getPassword());
		
		if(validUser != null) {
			model.addAttribute("userName", validUser.getName());
			return "profile";
		}else {
			model.addAttribute("msg", "Email & Password not Correct");
			return "loginPage";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.invalidate();
		}
		model.addAttribute("user", new User());
		return "loginPage";
	}
	
}
