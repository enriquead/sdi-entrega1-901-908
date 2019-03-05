package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@PostConstruct
	public void init() {
		User userAdmin = new User( "admin@email.com", "admin", "admin");
		userAdmin.setPassword("admin");
		userAdmin.setRole(rolesService.getRoles()[1]);
		User user1 = new User("ejemplo1@mail.es", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("ejemplo2@mail.es", "Martín", "Corrales");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
	
		usersService.addUser(userAdmin);
		usersService.addUser(user1);
		usersService.addUser(user2);
	}

}