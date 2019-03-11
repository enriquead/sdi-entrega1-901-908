package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;
import com.uniovi.repositories.PurchasesRepository;

@Service
public class PurchasesService {

	@PostConstruct
	public void init() {
		
	}
		
	@Autowired
	private PurchasesRepository purchasesRepository;
	
	public List<Purchase> getPurchases() {
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchasesRepository.findAll().forEach(purchases::add);
		return purchases;
	}
	
	public List<Purchase> getPurchasesForUser(User user) {
		List<Purchase> purchases = new ArrayList<Purchase>();
		purchases = purchasesRepository.findAllByUser(user);
		return purchases;
	}
	
	public void addPurchase(Purchase purchase) {
		purchasesRepository.save(purchase);
	}
	
	public String addPurchase(User user, Offer offer) {
		if(user.getMoney() >= offer.getPrice()) {
			user.setMoney(user.getMoney()-offer.getPrice());
			Purchase purchase = new Purchase(user, offer);
			addPurchase(purchase);
			return "Compra realizada";
		}
		return "Compra fallida";
	}

}
