package com.uniovi.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpValidator;

@Controller
public class UsersController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private SignUpValidator signUpValidator;

	@RequestMapping("/user/list")
	public String getListado(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("user") User user, BindingResult result) {
		signUpValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		user.setRole(rolesService.getRoles()[0]);
		usersService.addUser(user);
		securityService.autoLogin(user.getMail(), user.getPasswordConfirm());
		return "redirect:home";
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping("/user/list/delete")
	public String updateList(Model model,@RequestParam Long id){
		usersService.deleteUser(id);
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list :: userTable";
	}
	
	
}



