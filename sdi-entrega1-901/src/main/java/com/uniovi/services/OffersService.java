package com.uniovi.services;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;

@Service
public class OffersService {

	@PostConstruct
	public void init() {

	}

	@Autowired
	private OffersRepository offersRepository;

	public List<Offer> getOffers() {
		List<Offer> offers = new ArrayList<Offer>();
		offersRepository.findAll().forEach(offers::add);
		return offers;
	}

	public Offer getOffer(Long id) {
		return offersRepository.findById(id).get();
	}

	public void addOffer(Offer offer) {
		offersRepository.save(offer);
	}

	public Page<Offer> searchOffersByTitle(Pageable pageable, String searchText) {
		searchText = "%" + searchText + "%";
		Page<Offer> offers = new PageImpl<Offer>(new LinkedList<Offer>());
		offers = offersRepository.searchByTitle(pageable, searchText);

		return offers;
	}

	public Page<Offer> getOffers(Pageable pageable) {
		Page<Offer> offers = offersRepository.findAll(pageable);
		return offers;
	}

	public void deleteOffer(Long id) {
		offersRepository.deleteById(id);
	}

	public List<Offer> getPromoted() {
		return offersRepository.findPromoted();
	}

	public boolean setOfferPromoted(User user, boolean revised, Long id) {
		Offer offer = offersRepository.findById(id).get();
		double disc = 20.0;
		if(user.getMoney() >= disc && offer.getUser().getMail().equals(user.getMail())) {
			user.setMoney(user.getMoney() - disc);
			offersRepository.updatePromoted(revised, id);
			offersRepository.updatePromotedMoney(user.getMoney(), user.getId());
			return true;
		}
		return false;
	}

}
