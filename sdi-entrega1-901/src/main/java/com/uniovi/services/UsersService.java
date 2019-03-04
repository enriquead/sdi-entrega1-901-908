package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

public class UsersService {
	
	@PostConstruct
	public void init() {
	}

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		usersRepository.findAll().forEach(users::add);
		return users;
	}

	public User getUser(Long id) {
		return usersRepository.findById(id).get();
	}

	public void addUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
	}

	public User getUserByMail(String mail) {
		return usersRepository.findByMail(mail);
	}

	public void deleteUser(Long id) {
		usersRepository.deleteById(id);
	}

}