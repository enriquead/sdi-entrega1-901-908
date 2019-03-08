package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Offer;
import com.uniovi.services.OffersService;

@Controller
public class OffersController {

	@Autowired
	private OffersService offersService;

	@RequestMapping("/offer/list")
	public String getList(Model model) {
		model.addAttribute("offerList", offersService.getOffers());
		return "offer/list";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(@ModelAttribute Offer offer) {
		offersService.addOffer(offer);
		return "redirect:/offer/list";
	}

	@RequestMapping("/offer/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("offer", offersService.getOffer(id));
		return "offer/details";
	}

}
