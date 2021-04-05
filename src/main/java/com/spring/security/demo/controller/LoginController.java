package com.spring.security.demo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.security.demo.dto.UserDTO;
import com.spring.security.demo.entity.User;
import com.spring.security.demo.service.UserService;

@Controller
@RequestMapping
public class LoginController {

	@Autowired
	public UserService userService;

	@GetMapping("/login")
	public String login() {
			return "login";
	}

	@GetMapping("/")
	public String home(Model model, @ModelAttribute("user") UserDTO udto) {
		User user = userService.findByEmail(udto.getEmail());
		model.addAttribute("user", user);
		return "profile";
	}

	@PostMapping("/upload")
	public String changeAvata(@RequestBody String avatarBase64,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
		 if (session.getAttribute("email") == null) {
	            return "redirect:/login";
	        } 
		return avatarBase64;
		
	}

}
