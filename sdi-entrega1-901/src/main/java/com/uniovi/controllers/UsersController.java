package com.uniovi.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.PurchasesService;
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
	private PurchasesService purchasesService;

	@Autowired
	private SignUpValidator signUpValidator;

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private OffersService offersService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());


	@RequestMapping("/user/list")
	public String getListado(Model model) {
		log.info("{} solicita ver el listado de usuarios",((User) httpSession.getAttribute("loggedUser")).getMail());
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpValidator.validate(user, result);
		log.info("Intento de registro",((User) httpSession.getAttribute("loggedUser")).getMail());
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
		log.info("Intento de login");
		return "login";
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		User activeUser = usersService.getUserByMail(mail);
		httpSession.setAttribute("loggedUser", activeUser);
		log.info("{} está en el sistema",((User) httpSession.getAttribute("loggedUser")).getMail());
		model.addAttribute("offerList", offersService.getPromoted());
		return "home";
	}
	
	@RequestMapping(value = "/user/list/delete",method=RequestMethod.POST)
	public String delete(@RequestParam List<Long> ids) {
		for (Long id: ids) {
			purchasesService.deleteByUserId(id);
			usersService.deleteUser(id);
			log.info("{} Intenta borrar un usuario",((User) httpSession.getAttribute("loggedUser")).getMail());
		}
		return "redirect:/user/list";
	}
	
	@RequestMapping("/user/list/update")
	public String updateListado(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "user/list";
	}

}
