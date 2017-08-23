package com.codingdojo.loginandreg.controllers;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.loginandreg.models.User;
import com.codingdojo.loginandreg.services.UserService;

@Controller
public class Logreg {
	private UserService userService;
//	UserService/userService is from the .services/UserService.java file reference
	
	public Logreg (UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String index(@Valid @ModelAttribute("user") User user, 
			@RequestParam(value="error", required=false) String error, 
			@RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successfull!");
        }
        return "loginandreg.jsp";
    }
	
	@PostMapping("/registration")
	public String registration(@ Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			return "loginandreg.jsp";
		}
		else {
			userService.saveUser(user);
			return "redirect:/dashboard";
		}
	}
	
	@RequestMapping("/dashboard")
    public String home(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        model.addAttribute("lastSignIn", new Date());
        return "dashboard.jsp";
    }
}
