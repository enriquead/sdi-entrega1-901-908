package com.uniovi.controllers;


import java.util.LinkedList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.services.OffersService;
import com.uniovi.services.PurchasesService;

import com.uniovi.services.UsersService;

@Controller
public class OffersController {

	@Autowired
	private OffersService offersService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private PurchasesService purchasesService;
	
	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/offer/list")
	public String getList(Model model) {
		model.addAttribute("offerList", offersService.getOffers());
		return "offer/list";
	}

	@RequestMapping(value = "/offer/add")
	public String getOffer() {
		return "offer/add";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(@ModelAttribute Offer offer) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		User activeUser = usersService.getUserByMail(mail);
		offer.setUser(activeUser);
		offersService.addOffer(offer);
		return "redirect:/offer/list";
	}

	@RequestMapping("/offer/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("offer", offersService.getOffer(id));
		return "offer/details";
	}

	@RequestMapping("/offer/delete/{id}")
	public String deleteOffer(@PathVariable Long id) {
		offersService.deleteOffer(id);
		return "redirect:/offer/myOffers";
	}

	@RequestMapping("/offer/search")
	public String getSearch(Model model, Pageable pageable,
			@RequestParam(value = "", required = false) String searchText) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());

		if (searchText != null && !searchText.isEmpty()) {
			offers = offersService.searchOffersByTitle(pageable, searchText);
		} else {
			offers = offersService.getOffers(pageable);
		}
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);

		return "offer/search";
	}

	@RequestMapping(value = { "offer/myOffers" }, method = RequestMethod.GET)
	public String myOffers(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		User activeUser = usersService.getUserByMail(mail);
		model.addAttribute("offerList", activeUser.getOffers());
		return "offer/myOffers";
	}

	@RequestMapping(value = "/offer/buy/{id}", method = RequestMethod.GET)
	public String setBuy(Model model, @PathVariable Long id) {
		Offer boughtOffer = offersService.getOffer(id);
		User currentUser = usersService.getCurrentUser();
		boolean exito = purchasesService.addPurchase(currentUser, boughtOffer);
		httpSession.setAttribute("fail",!exito);
		return "redirect:/offer/search";
	}

	@RequestMapping("/offer/search/update")
	public String getSearch(Model model, Pageable pageable) {
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersService.getOffers(pageable);
		
		model.addAttribute("offerList", offers.getContent());
		model.addAttribute("page", offers);
		
		model.addAttribute("fail",httpSession.getAttribute("fail"));

		return "offer/search :: tableOffers";
	}
	

}
