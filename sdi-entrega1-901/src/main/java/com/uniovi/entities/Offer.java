package com.uniovi.entities;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Offer {	
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String details;
	private Date upDate;
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(mappedBy="offer" ,cascade= CascadeType.ALL)
	private Purchase purchase;
	
	public Offer() {
		
	}
	
	public Offer(String title, String details, double price) {
		this.title=title;
		this.details=details;
		this.price=price;
	}
	public Offer(String title, String details, double price,User user) {
		this.title=title;
		this.details=details;
		this.price=price;
		this.user=user;
	}
	
	public Offer(String title, double price,User user) {
		super();
		this.title=title;
		this.price=price;
		this.user = user;
	}
	
	public Offer(String title, String details, Date upDate, double price) {
		super();
		this.title = title;
		this.details = details;
		this.upDate = upDate;
		this.price = price;
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

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
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
