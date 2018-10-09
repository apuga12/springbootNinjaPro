package com.industries.stark.springbootNinja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.industries.stark.springbootNinja.constant.ViewConstant;
import com.industries.stark.springbootNinja.model.UserCredential;

@Controller
public class LoginController {
	
	public static final Log LOG = LogFactory.getLog(LoginController.class);
	
	@GetMapping("/")
	public String redirectToLoing() {
		LOG.info(" --- METHOD : redirectToLoing");
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model, 
			@RequestParam(name="error",  required=false) String error,
			@RequestParam(name="logout", required=false) String logout) {
		LOG.info(" --- METHOD : login.  PARAMS : error = "+error+ " , logout = "+logout);
		model.addAttribute("userCredentials", new UserCredential());
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewConstant.LOGIN;
	}
	
	// Return a la vista principal del usuario
	@PostMapping("/logincheck")
	public String loginCheck(@ModelAttribute(name="userCredentials") UserCredential userCredential) {
		LOG.info(" --- METHOD : loginCheck.  PARAMS : "+userCredential.toString());
		if(userCredential.getUsername().equals("user") && userCredential.getPassword().equals("user")) {			
			//return ViewConstant.CONTACTS;   // Vista Estatica
			return "redirect:/contacts/showcontacts";
		}
		return "redirect:/login?error";
	}

}
