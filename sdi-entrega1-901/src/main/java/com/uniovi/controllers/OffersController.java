package com.uniovi.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.uniovi.services.OffersService;

@Controller
public class OffersController {

	@Autowired
	private OffersService offersService;
	
	@RequestMapping("/offer/list")
	public String getList(Model model) {
		model.addAttribute("offerList", offersService.getOffers() );
		return "offer/list";
	}

}
