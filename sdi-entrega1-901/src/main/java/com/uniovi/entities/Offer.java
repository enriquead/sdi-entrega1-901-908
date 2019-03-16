package com.uniovi.entities;

import java.util.Calendar;

import java.util.GregorianCalendar;

import javax.persistence.*;

@Entity
public class Offer {	
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String details;
	private String upDate;
	private double price;
	private boolean promoted = false;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(mappedBy="offer", cascade=CascadeType.ALL)
	private Purchase purchase;
	
	public Offer() {
		
	}

	public Offer(String title, String details, double price,User user) {
		this.title=title;
		this.details=details;
		this.price=price;
		this.user=user;
		setPresentDate();
	}
	
	public Offer(String title, double price,User user) {
		super();
		this.title=title;
		this.price=price;
		this.user = user;
		setPresentDate();
	}
	
	public Offer(String title, String details, String upDate, double price, User user) {
		super();
		this.title = title;
		this.details = details;
		this.upDate = upDate;
		this.price = price;
		this.user=user;
	}

	public boolean isPromoted() {
		return promoted;
	}

	public void setPromoted(boolean promoted) {
		this.promoted = promoted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getUpDate() {
		return upDate;
	}
	
	public void setPresentDate() {
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha = dia + "/" + mes + "/" + annio;
		setUpDate(fecha);
	}

	public void setUpDate(String upDate) {
		this.upDate = upDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	
	
	
	
	
	
	

}
