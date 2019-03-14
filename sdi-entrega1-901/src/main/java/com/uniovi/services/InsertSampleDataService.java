package com.uniovi.services;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private OffersService offersService;
	
	@Autowired
	private PurchasesService purchasesService;
	
	@PostConstruct
	public void init() {
		
		User userAdmin = new User("admin@email.com", "admin", "admin");
		userAdmin.setPassword("admin");
		userAdmin.setRole(rolesService.getRoles()[1]);

		User user1 = new User("ejemplo1@mail.es", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);

		User user2 = new User("ejemplo2@mail.es", "Martín", "Corrales");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);

		User user3 = new User("ejemplo3@mail.es", "Juan", "Gomez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);

		usersService.addUser(userAdmin);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);

		Offer offer1 = new Offer("Auriculares", "Auriculares de cable", 3, user1);
		Offer offer2 = new Offer("Teclado", "Teclado mecánico nuevo", 20.0, user1);
		Offer offer3 = new Offer("Camiseta running", "Talla M", 20, user1);
		Offer offer4 = new Offer("Ratón inalámbrico", "Con Bluetooth 4.0", 15, user2);
		Offer offer5 = new Offer("Ratón antiguo", "Ratón del año 1996", 30, user2);
		Offer offer6 = new Offer("Auriculares Bluetooth", "Nuevos, sin estrenar", 30, user2);
		Offer offer7 = new Offer("Lavadora inteligente", "Segunda mano BOSCH", 90, user3);
		Offer offer8 = new Offer("Samsung TV", "Televisión OLED 40' Samsung", 500, user3);
		Offer offer9 = new Offer("Mesa", "Mesa escritorio IKEA", 30, user3);

		offersService.addOffer(offer1);
		offersService.addOffer(offer2);
		offersService.addOffer(offer3);
		offersService.addOffer(offer4);
		offersService.addOffer(offer5);
		offersService.addOffer(offer6);
		offersService.addOffer(offer7);
		offersService.addOffer(offer8);
		offersService.addOffer(offer9);
		
		
		
	}

}
