package com.uniovi.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.PurchasesService;
import com.uniovi.services.UsersService;

@Controller
public class PurchasesController {
	
	@Autowired
	private PurchasesService purchasesService;
	
	@Autowired
	private UsersService usersService;

	@RequestMapping("/purchase/list")
	public String getList(Model model,Principal principal) {
		String mail = principal.getName(); 
		User user = usersService.getUserByMail(mail);
		model.addAttribute("purchasesList", purchasesService.getPurchasesForUser(user));
		return "purchase/list";
	}

}
