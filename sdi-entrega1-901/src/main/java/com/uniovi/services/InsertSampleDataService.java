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
		User usuarioABorrar1 = new User("borrar@mail.com","Borrar" ,"borrado");
		usuarioABorrar1.setPassword("123456");
		usuarioABorrar1.setRole(rolesService.getRoles()[0]);
		
		User userAdmin = new User( "admin@email.com", "admin", "admin");
		userAdmin.setPassword("admin");
		userAdmin.setRole(rolesService.getRoles()[1]);
		
		User user1 = new User("ejemplo1@mail.es", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		
		User user2 = new User("ejemplo2@mail.es", "Martín", "Corrales");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		
		User usuarioABorrar2 = new User("borrar2@mail.com","Borrar2" ,"borrado2");
		usuarioABorrar2.setPassword("123456");
		usuarioABorrar2.setRole(rolesService.getRoles()[0]);
		
		User usuarioABorrar3 = new User("borrar3@mail.com","Borrar3" ,"borrado3");
		usuarioABorrar3.setPassword("123456");
		usuarioABorrar3.setRole(rolesService.getRoles()[0]);
		
		User usuarioABorrar4 = new User("borrar4@mail.com","Borrar4" ,"borrado4");
		usuarioABorrar4.setPassword("123456");
		usuarioABorrar4.setRole(rolesService.getRoles()[0]);
		
		User usuarioABorrarUltimo = new User("borrarult@mail.com","Borrarult" ,"borradoult");
		usuarioABorrarUltimo.setPassword("123456");
		usuarioABorrarUltimo.setRole(rolesService.getRoles()[0]);
		
		usersService.addUser(usuarioABorrar1);
		usersService.addUser(userAdmin);
		usersService.addUser(usuarioABorrar2);
		usersService.addUser(usuarioABorrar3);
		usersService.addUser(user1);
		usersService.addUser(usuarioABorrar4);
		usersService.addUser(user2);
		usersService.addUser(usuarioABorrarUltimo);
		
		Offer offer1= new Offer("Auriculares","Auriculares de cable" ,2.5,user1);
		Offer offer2=new Offer("Teclado","Teclado mecánico nuevo" ,20.0,user1);
		Offer offer3= new Offer("Ratón inalámbrico","Con Bluetooth 4.0" ,15.4,user2);
		Offer offer4= new Offer("Ratón antiguo", "Ratón del año 1996", 30, usuarioABorrarUltimo);
		Offer offer5= new Offer("Auriculares Bluetooth", "Nuevos, sin estrenar", 30,user2);
		Offer offer6= new Offer("Camiseta running", "Talla M", 19.99,user1);
		
		offersService.addOffer(offer1);
		offersService.addOffer(offer2);
		offersService.addOffer(offer3);
		offersService.addOffer(offer4);
		offersService.addOffer(offer5);
		offersService.addOffer(offer6);
		
		purchasesService.addPurchase(new Purchase(user1, offer3));
		
		
	}

}
