package com.uniovi.controllers;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.uniovi.validators.AddOfferValidator;

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
	
	@Autowired
	private AddOfferValidator addOfferValidator;
	


	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		User activeUser = usersService.getUserByMail(mail);
		httpSession.setAttribute("loggedUser", activeUser);
		model.addAttribute("offer", new Offer());
		return "offer/add";
	}

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(@Validated Offer offer, BindingResult result) {
		addOfferValidator.validate(offer, result);
		if (result.hasErrors()) {
			return "offer/add";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		User activeUser = usersService.getUserByMail(mail);
		offer.setUser(activeUser);
		offer.setPresentDate();
		offersService.addOffer(offer);
		return "redirect:/offer/search";
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		User activeUser = usersService.getUserByMail(mail);
		httpSession.setAttribute("loggedUser", activeUser);
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
		httpSession.setAttribute("loggedUser", activeUser);
		model.addAttribute("offerList", activeUser.getOffers());
		return "offer/myOffers";
	}

	@RequestMapping(value = "/offer/buy/{id}", method = RequestMethod.GET)
	public String setBuy(Model model, @PathVariable Long id) {
		Offer boughtOffer = offersService.getOffer(id);
		User currentUser = usersService.getCurrentUser();
		boolean exito = purchasesService.addPurchase(currentUser, boughtOffer);
		httpSession.setAttribute("fail",!exito);
		httpSession.setAttribute("loggedUser",currentUser);
		return "redirect:/offer/search";
	}
	
	@RequestMapping("/home/update")
	public String getHome(Model model) {
		model.addAttribute("offerList",offersService.getPromoted());

		return "/home :: tableOffers";
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
	
	@RequestMapping(value = "/offer/{id}/promote", method = RequestMethod.GET)
	public String setPromotedTrue(Model model, @PathVariable Long id) {
		User activeUser = usersService.getCurrentUser();
		offersService.setOfferPromoted(activeUser,true, id);
		return "redirect:/offer/myOffers";
	}
	
	@RequestMapping("/offer/myOffers/update")
	public String getOffers(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mail = auth.getName();
		User activeUser = usersService.getUserByMail(mail);
		model.addAttribute("offerList", activeUser.getOffers());

		return "offer/myOffers :: tableOffers";
	}
	
	

}
