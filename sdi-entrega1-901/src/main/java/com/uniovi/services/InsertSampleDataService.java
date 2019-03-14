package com.uniovi.services;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.Purchase;
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
		
		User user4 = new User("ejemplo4@mail.es", "Lucas", "Martínez");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		
		User user5 = new User("ejemplo5@mail.es","Ramón","Ramos");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);

		usersService.addUser(userAdmin);
		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		

		Offer offer1 = new Offer("Auriculares", "Auriculares de cable", 3, user1);
		Offer offer2 = new Offer("Teclado", "Teclado mecánico nuevo", 20.0, user1);
		Offer offer3 = new Offer("Camiseta running", "Talla M", 20, user1);
		Offer offer4 = new Offer("Ratón inalámbrico", "Con Bluetooth 4.0", 15, user2);
		Offer offer5 = new Offer("Ratón antiguo", "Ratón del año 1996", 30, user2);
		Offer offer6 = new Offer("Auriculares Bluetooth", "Nuevos, sin estrenar", 30, user2);
		Offer offer7 = new Offer("Lavadora inteligente", "Segunda mano BOSCH", 90, user3);
		Offer offer8 = new Offer("Samsung TV", "Televisión OLED 40' Samsung", 500, user3);
		Offer offer9 = new Offer("Mesa", "Mesa escritorio IKEA", 30, user3);
		Offer offer10 = new Offer("Silla", "Silla antigua",25,user4);
		Offer offer11 = new Offer("Teclado","Teclado chino",25,user4);
		Offer offer12 = new Offer("Camiseta running","Nueva, a estrenar",100,user4);
		Offer offer13 = new Offer("Sartén", "Sartén cerámica",100,user5);
		Offer offer14 = new Offer("Monitor","Monitor de 20 pulgadas",30,user5);
		Offer offer15 = new Offer("Botas de montaña","Número 42",33,user5);
		

		offersService.addOffer(offer1);
		offersService.addOffer(offer2);
		offersService.addOffer(offer3);
		offersService.addOffer(offer4);
		offersService.addOffer(offer5);
		offersService.addOffer(offer6);
		offersService.addOffer(offer7);
		offersService.addOffer(offer8);
		offersService.addOffer(offer9);
		offersService.addOffer(offer10);
		offersService.addOffer(offer11);
		offersService.addOffer(offer12);
		offersService.addOffer(offer13);
		offersService.addOffer(offer14);
		offersService.addOffer(offer15);
		
		Purchase purchase1 = new Purchase(user1,offer4);
		Purchase purchase2 = new Purchase(user1,offer7);
		Purchase purchase3 = new Purchase(user2,offer1);
		Purchase purchase4 = new Purchase(user2,offer9);
		Purchase purchase5 = new Purchase(user3,offer2);
		Purchase purchase6 = new Purchase(user3,offer10);
		Purchase purchase7 = new Purchase(user4,offer12);
		Purchase purchase8 = new Purchase(user4,offer3);
		Purchase purchase9 = new Purchase(user5,offer11);
    	Purchase purchase10 = new Purchase(user5,offer6);
	
		purchasesService.addPurchase(purchase1);
		purchasesService.addPurchase(purchase2);
		purchasesService.addPurchase(purchase3);
		purchasesService.addPurchase(purchase4);
		purchasesService.addPurchase(purchase5);
		purchasesService.addPurchase(purchase6);
		purchasesService.addPurchase(purchase7);
		purchasesService.addPurchase(purchase8);
	    purchasesService.addPurchase(purchase9);
		purchasesService.addPurchase(purchase10);
		
		
		
	}

}
