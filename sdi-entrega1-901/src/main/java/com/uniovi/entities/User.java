package com.uniovi.entities;


import javax.persistence.*;


@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String mail;
	private String name;
	private String lastName;
	private double money;
	private String role;
	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	private List<Offer> offers;
	
	
	
	private String password;
	@Transient
	private String passwordConfirm;
	
	public User() {
		
	}

	public User(String mail, String name, String lastName) {
		super();
		this.mail = mail;
		this.name = name;
		this.lastName = lastName;
		this.money = 100;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	

}
