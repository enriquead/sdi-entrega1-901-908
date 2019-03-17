package com.uniovi.tests;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.entities.Offer;
import com.uniovi.entities.Purchase;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.OffersService;
import com.uniovi.services.PurchasesService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWallapopTests {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OffersService offersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private PurchasesService purchasesService;

	@Autowired
	private UsersRepository usersRepository;
	
	
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	//static String PathFirefox65 = "C:\\Users\\media service\\Desktop\\SDI\\p5\\FirefoxPortable\\App\\Firefox64\\firefox.exe";
	//static String Geckdriver024 = "C:\\Users\\media service\\Desktop\\SDI\\p5\\PL-SDI-Sesio´n5-material\\geckodriver024win64.exe";
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Enrique\\Desktop\\Gecko\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas):
	// static String PathFirefox65 =
	// "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	// static String Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URLREMOTA = "http://35.180.58.190:8080";
	static String URLLOCAL = "http://localhost:8080";
	static String URL = URLLOCAL;

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		
		initdb();
		driver.navigate().to(URL);
	}

	public void initdb() {
		
		usersRepository.deleteAll();
		
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
	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		driver.quit();
	}

	// -----------------REGISTRO----------------------

	// PR01. Registro de Usuario con datos válidos
	@Test
	public void PR01() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "santi@email.es", "Miguel", "García", "123456", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "Usuario");
	}

	// PR02. Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos).
	@Test
	public void PR02() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario. Email vacío
		PO_RegisterView.fillForm(driver, "", "Miguel", "García", "123456", "123456");
		// Comprobamos que seguimos en la vista de signup
		PO_View.checkElement(driver, "text", "Regístrate como usuario");
		// Rellenamos el formulario. Nombre vacío
		PO_RegisterView.fillForm(driver, "santiago@mail.es", "", "García", "123456", "123456");
		// Comprobamos que seguimos en la vista de signup
		PO_View.checkElement(driver, "text", "Regístrate como usuario");
		// Rellenamos el formulario. Apellido vacío
		PO_RegisterView.fillForm(driver, "santiago@mail.es", "Santiago", "", "123456", "123456");
		// Comprobamos que seguimos en la vista de signup
		PO_View.checkElement(driver, "text", "Regístrate como usuario");

	}

	// PR03. Registro de Usuario con datos inválidos (repetición de contraseña
	// inválida).
	@Test
	public void PR03() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "santiago@email.es", "Santiago", "Perez", "123456", "654321");
		PO_View.getP();
		// COmprobamos el error de coincidencia de contraseñas .
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());

	}

	// PR04. Registro de Usuario con datos inválidos (email existente)
	@Test
	public void PR04() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "ejemplo1@mail.es", "Santiago", "Perez", "123456", "123456");
		PO_View.getP();
		// COmprobamos el error de mail repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.mail.duplicate", PO_Properties.getSPANISH());

	}

	// -----------------INICIO DE SESIÓN----------------------

	// PR05. Inicio de sesión con datos válidos (administrador).
	@Test
	public void PR05() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// COmprobamos que entramos en la pagina privada de Administrador
		PO_View.checkElement(driver, "text", "Gestión Usuarios");
	}

	// PR06. Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void PR06() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada de usuario estándar
		PO_View.checkElement(driver, "text", "Gestión Ofertas");

	}

	// PR07. Inicio de sesión con datos inválidos (usuario estándar, campo email y
	// contraseña vacíos).
	@Test
	public void PR07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario vacío.
		PO_LoginView.fillForm(driver, "", "");
		// Comprobamos que seguimos en la vista de login
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// PR08. Inicio de sesión con datos válidos (usuario estándar, email existente,
	// pero contraseña incorrecta).
	@Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario, contraseña incorrecta.
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "admin");
		// Comprobamos que seguimos en la vista de login
		PO_View.checkNoElement(driver, "Desconectar");
	}

	// PR09. Inicio de sesión con datos inválidos (usuario estándar, email no
	// existente en la aplicación).
	@Test
	public void PR09() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario, email no existente.
		PO_LoginView.fillForm(driver, "pedro@mail.es", "123456");
		// Comprobamos que seguimos en la vista de login
		PO_View.checkElement(driver, "text", "Identifícate");

	}

	// -----------------FIN DE SESIÓN----------------------

	// PR10. Hacer click en la opción de salir de sesión y comprobar que se redirige
	// a la página de inicio de sesión (Login).
	@Test
	public void PR10() {
		// iniciamos sesión
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con datos válidos
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// Nos desconectamos.
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Comprobamos que volvemos a la vista de login
		PO_View.checkElement(driver, "text", "Identifícate");

	}

	// PR11. Comprobar que el botón cerrar sesión no está visible si el usuario no
	// está autenticado.
	@Test
	public void PR11() {
		// no nos registramos
		// comprobamos que no está el botón de desconectar
		PO_View.checkNoElement(driver, "Desconectar");
	}

	// PR12. Mostrar el listado de usuarios y comprobar que se muestran todos los
	// que existen en el sistema
	 @Test
	public void PR12() {
		//Inicio de sesión
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Comprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "Gestión Usuarios");
		//Vamos a la opción de gestionar usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		//Calcamos la opción
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		elementos.get(0).click();
		//Comprobamos que aparecen todos los elementos esperados(5 usuarios)
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		//Salimos de sesión
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// PR13. Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza y dicho usuario desaparece.
	@Test
	public void PR13() {
		//Inicio de sesión
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Comprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "Gestión Usuarios");
		//Vamos a la opción de gestionar usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		//Calcamos la opción
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		elementos.get(0).click();
		//Seleccionamos el primer usuario y hacemos click
		elementos = PO_View.checkElement(driver, "free", "//input");
		elementos.get(0).click();
		//Calcamos el botón de borrar
		elementos = PO_View.checkElement(driver, "id", "deleteButton");
		elementos.get(0).click();
		//Comprobamos que la página no contiene al 1 usuario. Email ejemplo1@mail.es
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "ejemplo1@mail.es", PO_View.getTimeout());
		//Comprobamos que ahora haya solo 4 usuarios
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 4);
		//Cerramos la sesión
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// PR14. Ir a la lista de usuarios, borrar el último usuario de la lista,
	// comprobar que la lista se actualiza y dicho usuario desaparece.
	 @Test
	public void PR14() {
		//Inicio de sesión
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Comprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "Gestión Usuarios");
		//Vamos a la opción de gestionar usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		//Calcamos la opción
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		elementos.get(0).click();
		//Seleccionamos el último usuario y hacemos click
		elementos = PO_View.checkElement(driver, "free", "//input");
		//Al ser 5 usuarios el último es el 4
		elementos.get(4).click();
		//Calcamos el botón de borrar
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "deleteButton", PO_View.getTimeout());
		elementos.get(0).click();
		//Comprobamos que la página no contiene al ultimo usuario. Email ejemplo5@mail.es
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "ejemplo5@mail.es", PO_View.getTimeout());
		//Comprobamos que ahora haya solo 4 usuarios
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 4);
		//Cerramos la sesión
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// PR15. Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se
	// actualiza y dichos usuarios desaparecen.
	@Test
	public void PR15() {
		//Inicio de sesión
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Comprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "Gestión Usuarios");
		//Vamos a la opción de gestionar usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		//Calcamos la opción
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		elementos.get(0).click();
		//Seleccionamos tres usuarios, voy a coger el primero,tercero y cuarto
		elementos = PO_View.checkElement(driver, "free", "//input");
		//Empezando en 0 serían 0,2 y 3
		elementos.get(0).click();
		elementos.get(2).click();
		elementos.get(3).click();
		//Calcamos el botón de borrar
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "deleteButton", PO_View.getTimeout());
		elementos.get(0).click();
		//Comprobamos que la página no contiene al 1,3 ni 4 usuario. Email ejemplo1@mail.es
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "ejemplo1@mail.es", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "ejemplo3@mail.es", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "ejemplo4@mail.es", PO_View.getTimeout());
		//Comprobamos que ahora haya solo 2 usuarios
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 2);
		//Cerramos la sesión
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// PR16. Ir al formulario de alta de oferta, rellenarla con datos válidos y
	// pulsar el botón Submit.
	// Comprobar que la oferta sale en el listado de ofertas de dicho usuario.
	@Test
	public void PR16() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		// Pinchamos en la opción de menu de gestión ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de añadir oferta.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/add')]");
		// Pinchamos en añadir oferta.
		elementos.get(0).click();
		//Esperamos a estar en la vista adecuada, deberia aparecer el botón publicar
		SeleniumUtils.EsperaCargaPagina(driver, "id", "publicar", PO_View.getTimeout());
		// Ahora vamos a rellenar la oferta.
		PO_PrivateView.fillFormAddOffer(driver, "Cascos", "Cascos Meizu 5.0", "90",false);
		// Esperamos a que se muestren los enlaces de paginación la lista de ofertas
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		// Nos vamos a la última página
		elementos.get(3).click();
		// Comprobamos que aparece la oferta en la pagina
		elementos = PO_View.checkElement(driver, "text", "Cascos");
		// nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// PR17.Ir al formulario de alta de oferta, rellenarla con datos inválidos
	// (campo título vacío) y pulsar el botón Submit. Comprobar que se muestra el
	// mensaje de campo obligatorio.
	@Test
	public void PR17() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		// Pinchamos en la opción de menu de gestión ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de añadir oferta.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/add')]");
		// Pinchamos en añadir oferta.
		elementos.get(0).click();
		//Esperamos a estar en la vista adecuada, deberia aparecer el botón publicar
		SeleniumUtils.EsperaCargaPagina(driver, "id", "publicar", PO_View.getTimeout());
		// Ahora vamos a rellenar la oferta sin titulo.
		PO_PrivateView.fillFormAddOffer(driver, " ", "Segunda mano Bosch", "90",false);
		// Si dejamos el título vacío, comprobamos que sigue en la misma página de
		// añadir oferta.
		PO_View.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		// nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// PR18. Mostrar el listado de ofertas para dicho usuario y comprobar que se
	// muestran todas los que existen para este usuario.
	@Test
	public void PR18() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo2@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo2@mail.es");
		// Pinchamos en la opción de menu de gestión ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de mis ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/myOffers')]");
		// Pinchamos en mis ofertas.
		elementos.get(0).click();
		// Cargamos el número de elementos de la tabla mis ofertas.
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		// Comprobamos que es igual al numero de productos del usuario actual (3)
		assertTrue(elementos.size() == 3);
		// nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// PR19. Ir a la lista de ofertas, borrar la primera oferta de la lista,
	// comprobar que la lista se actualiza y que la oferta desaparece.
	@Test
	public void PR19() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		// Pinchamos en la opción de menu de gestión ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de mis ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/myOffers')]");
		// Pinchamos en mis ofertas.
		elementos.get(0).click();
		// Buscamos la primera oferta y le damos a eliminar (Auriculares)
		elementos = PO_View.checkElement(driver, "free","//td[contains(text(), 'Auriculares')]/following-sibling::*/a[contains(@href, 'offer/delete')]");
		elementos.get(0).click();
		// Comprobamos que ya no figuran en la lista de mis ofertas.
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Auriculares", PO_View.getTimeout());
		// nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// PR20.Ir a la lista de ofertas, borrar la última oferta de la lista, comprobar
	// que la lista se actualiza y que la oferta desaparece.
	@Test
	public void PR20() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		// Pinchamos en la opción de menu de gestión ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de mis ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/myOffers')]");
		// Pinchamos en mis ofertas.
		elementos.get(0).click();
		// Buscamos la primera oferta y le damos a eliminar (Auriculares)
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'Camiseta running')]/following-sibling::*/a[contains(@href, 'offer/delete')]");
		elementos.get(0).click();
		// Comprobamos que ya no figuran en la lista de mis ofertas.
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Camiseta running", PO_View.getTimeout());
		// nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}
	// PR21.Hacer una búsqueda con el campo vacío y comprobar que se muestra la página que
	// corresponde con el listado de las ofertas existentes en el sistema
	@Test
	public void PR21() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		// Pinchamos en la opción de menu de gestión de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de buscar ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		// Pinchamos
		elementos.get(0).click();
		//Sin escribir nada calcamos el botón de buscar
		elementos  = SeleniumUtils.EsperaCargaPagina(driver, "id", "searchButton", PO_View.getTimeout());
		elementos.get(0).click();
		//Comprobamos que estámos en la página de búsqueda
		PO_View.checkElement(driver, "text", "Aquí puede buscar las ofertas que más le interesen");
		//Comprobamos que en la primera página aparecen todas las ofertas esperadas
		PO_View.checkElement(driver, "text", "Auriculares");
		PO_View.checkElement(driver, "text", "Teclado");
		PO_View.checkElement(driver, "text", "Camiseta running");
		PO_View.checkElement(driver, "text", "Ratón inalámbrico");
		PO_View.checkElement(driver, "text", "Ratón antiguo");
		//Pasamos a la siguiente página (2)
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "siguiente", PO_View.getTimeout());
		elementos.get(0).click();
		//Comprobamos que en esta página hay lo que debe haber
		PO_View.checkElement(driver, "text", "Auriculares Bluetooth");
		PO_View.checkElement(driver, "text", "Lavadora inteligente");
		PO_View.checkElement(driver, "text", "Samsung TV");
		PO_View.checkElement(driver, "text", "Mesa");
		PO_View.checkElement(driver, "text", "Silla");
		//Pasamos a la siguiente página (3)
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "siguiente", PO_View.getTimeout());
		elementos.get(0).click();
		//Comprobamos que en esta página hay lo que debe haber
		PO_View.checkElement(driver, "text", "Teclado");
		PO_View.checkElement(driver, "text", "Camiseta running");
		PO_View.checkElement(driver, "text", "Sartén");
		PO_View.checkElement(driver, "text", "Monitor");
		PO_View.checkElement(driver, "text", "Botas de montaña");
		//Pasamos a la última página (3) Como son 15 elementos y 5 por página la última es la tres
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "última", PO_View.getTimeout());
		elementos.get(0).click();
		//Comprobamos que en esta página hay lo que debe haber, lo que hay en la 3
		PO_View.checkElement(driver, "text", "Teclado");
		PO_View.checkElement(driver, "text", "Camiseta running");
		PO_View.checkElement(driver, "text", "Sartén");
		PO_View.checkElement(driver, "text", "Monitor");
		PO_View.checkElement(driver, "text", "Botas de montaña");
		// nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
		
	}
	@Test
	//PR 22 Hacer una búsqueda escribiendo en el campo un texto que no exista y comprobar que se
	//muestra la página que corresponde, con la lista de ofertas vacía.
	public void PR22() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		// Pinchamos en la opción de menu de gestión de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de buscar ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		// Pinchamos
		elementos.get(0).click();
		//Comprobamos que estamos en la página correcta y tomamos como elemento el botón buscar
		elementos  = SeleniumUtils.EsperaCargaPagina(driver, "id", "searchButton", PO_View.getTimeout());
		//Escribimos un texto que no concuerde con ninguna oferta
		PO_PrivateView.fillSearchText(driver, "Ninguna oferta");
		//Calcamos el botón de buscar
		elementos.get(0).click();
		//Comprobamos que estámos en la página de búsqueda
		PO_View.checkElement(driver, "text", "Aquí puede buscar las ofertas que más le interesen");
		//Comprobamos que no aparecen elementos en el cuerpo de la tabla
		//Solo habrá un tr en la página(corresponde con la cabecera)
		elementos = PO_View.checkElement(driver, "free", "//tr");
		Assert.assertTrue(elementos.size() == 1);//Un único tr, la cabecera
		// nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	//PR 23 Sobre una búsqueda determinada (a elección de desarrollador), comprar una oferta que deja
	//un saldo positivo en el contador del comprobador. Y comprobar que el contador se actualiza
	//correctamente en la vista del comprador.
	@Test
	public void PR23() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		// Pinchamos en la opción de menu de gestión de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de buscar ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		// Pinchamos
		elementos.get(0).click();
		//Comprobamos que estamos en la página correcta y tomamos como elemento el botón buscar
		elementos  = SeleniumUtils.EsperaCargaPagina(driver, "id", "searchButton", PO_View.getTimeout());
		//Escribimos un texto que concuerda con una oferta que deja saldo positivo
		PO_PrivateView.fillSearchText(driver, "Ratón antiguo");
		elementos.get(0).click();
		//Cogemos el botón directamente con btn btn-success porque solo hay uno
		elementos = PO_View.checkElement(driver, "class", "btn btn-success");
		//Calcamos el botón comprar
		elementos.get(0).click();
		//Comprobamos que volvemos a la página de búsqueda. Hay botón de búsqueda
		SeleniumUtils.EsperaCargaPagina(driver, "id", "searchButton", PO_View.getTimeout());
		//Comprobamos el dinero que tenemos disponible es 70
		PO_View.checkElement(driver, "text", "70.0 €");
		//Nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
				
	}
	//PR 24 Sobre una búsqueda determinada (a elección de desarrollador), comprar una oferta que deja
	//un saldo 0 en el contador del comprobador. Y comprobar que el contador se actualiza correctamente en
	//la vista del comprador. 
	@Test
	public void PR24() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		// Pinchamos en la opción de menu de gestión de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de buscar ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		// Pinchamos
		elementos.get(0).click();
		//Comprobamos que estamos en la página correcta y tomamos como elemento el botón buscar
		elementos  = SeleniumUtils.EsperaCargaPagina(driver, "id", "searchButton", PO_View.getTimeout());
		//Escribimos un texto que concuerda con una oferta que deja saldo 0
		PO_PrivateView.fillSearchText(driver, "Sartén");
		elementos.get(0).click();
		//Cogemos el botón directamente con btn btn-success porque solo hay uno
		elementos = PO_View.checkElement(driver, "class", "btn btn-success");
		//Calcamos el botón comprar
		elementos.get(0).click();
		//Comprobamos que volvemos a la página de búsqueda. Hay botón de búsqueda
		SeleniumUtils.EsperaCargaPagina(driver, "id", "searchButton", PO_View.getTimeout());
		//Comprobamos el dinero que tenemos disponible es 0
		PO_View.checkElement(driver, "text", "0.0 €");
		//Nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	//PR 25 Sobre una búsqueda determinada (a elección de desarrollador), intentar comprar una oferta
	//que esté por encima de saldo disponible del comprador. Y comprobar que se muestra el mensaje de
	//saldo no suficiente.
	@Test
	public void PR25() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		// Pinchamos en la opción de menu de gestión de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de buscar ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		// Pinchamos
		elementos.get(0).click();
		//Comprobamos que estamos en la página correcta y tomamos como elemento el botón buscar
		elementos  = SeleniumUtils.EsperaCargaPagina(driver, "id", "searchButton", PO_View.getTimeout());
		//Escribimos un texto que concuerda con una oferta que no se puede comprar
		PO_PrivateView.fillSearchText(driver, "Samsung");
		elementos.get(0).click();
		//Cogemos el botón directamente con btn btn-success porque solo hay uno
		elementos = PO_View.checkElement(driver, "class", "btn btn-success");
		//Calcamos el botón comprar
		elementos.get(0).click();
		//Comprobamos que volvemos a la página de búsqueda. Hay botón de búsqueda
		SeleniumUtils.EsperaCargaPagina(driver, "id", "searchButton", PO_View.getTimeout());
		//Comprobamos que no deja hacer la operación y que el dinero no varía
		PO_View.checkElement(driver, "text", "100.0 €");
		PO_View.checkElement(driver, "text", "La compra no ha podido ser realizada, revise su balance");
		//Nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
	}
	
	// PR26. Ir a la opción de ofertas compradas del usuario y mostrar la lista. Comprobar que aparecen
	//las ofertas que deben aparecer.
	@Test
	public void PR26() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		// Pinchamos en la opción de menu de gestión de compras.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'purchases-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de ver compras.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'purchase/list')]");
		// Pinchamos
		elementos.get(0).click();
		//Comprobamos que existen las ofertas que deberían existir
		
		PO_View.checkElement(driver, "text", "Ratón inalámbrico");
		PO_View.checkElement(driver, "text", "Lavadora inteligente");
		// nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
		
		
		}
	@Test
	// PR27. Visualizar al menos cuatro páginas en Español/Inglés/Español 
	// (comprobando que algunas de las   etiquetas   cambian   al   idioma   correspondiente).   
	// Página   principal/Opciones   Principales   de Usuario/Listado de Usuarios de Admin/Vistade alta de Oferta.
	public void PR27() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina princioal.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		//Estamos en español, comprobamos algunas etiquetas
		PO_View.checkElement(driver, "text", "Bienvenidos a MyWallapop");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
		PO_View.checkElement(driver, "text", "Ofertas destacadas");
		PO_View.checkElement(driver, "text", "Título");
		PO_View.checkElement(driver, "text", "Detalles");
		PO_View.checkElement(driver, "text", "Precio");
		PO_NavView.changeIdiom(driver, "btnEnglish");
		//Estamos en inglés, comprobamos algunas etiquetas
		PO_View.checkElement(driver, "text", "Welcome to MyWallapop");
		PO_View.checkElement(driver, "text", "This is a private area of the web");
		PO_View.checkElement(driver, "text", "Promoted Offers");
		PO_View.checkElement(driver, "text", "Title");
		PO_View.checkElement(driver, "text", "Details");
		PO_View.checkElement(driver, "text", "Price");
		//Volvemos al español y comprobamos que están otra vez en español
		PO_NavView.changeIdiom(driver, "btnSpanish");
		PO_View.checkElement(driver, "text", "Bienvenidos a MyWallapop");
		PO_View.checkElement(driver, "text", "Esta es una zona privada de la web");
		PO_View.checkElement(driver, "text", "Ofertas destacadas");
		PO_View.checkElement(driver, "text", "Título");
		PO_View.checkElement(driver, "text", "Detalles");
		PO_View.checkElement(driver, "text", "Precio");
//		// Vamos a ver las opciones principales de usuario, como están en la barra
//		//de navegación podemos comprobarlo desde esta vista. Estamos en español
		PO_View.checkElement(driver, "text", "Gestión");
		SeleniumUtils.textoPresentePagina(driver, "Mis ofertas");
		SeleniumUtils.textoPresentePagina(driver, "Buscar");
		SeleniumUtils.textoPresentePagina(driver, "Gestión de compras");
		SeleniumUtils.textoPresentePagina(driver, "Ver");
//		//Cambiamos a inglés y comprobamos
		PO_NavView.changeIdiom(driver, "btnEnglish");
		PO_View.checkElement(driver, "text", "Offers");
		SeleniumUtils.textoPresentePagina(driver, "Purchases");
		SeleniumUtils.textoPresentePagina(driver, "Add offer");
		SeleniumUtils.textoPresentePagina(driver, "My offers");
		SeleniumUtils.textoPresentePagina(driver, "View");
		
//		//Volvemos a español y comprobamos
		PO_NavView.changeIdiom(driver, "btnSpanish");
		PO_View.checkElement(driver, "text", "Gestión");
		SeleniumUtils.textoPresentePagina(driver, "Mis ofertas");
		SeleniumUtils.textoPresentePagina(driver, "Buscar");
		SeleniumUtils.textoPresentePagina(driver, "Gestión de compras");
		SeleniumUtils.textoPresentePagina(driver, "Ver");
//
		//Nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
		//Entramos como admin
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		//Vamos a la opción de gestionar usuarios
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
		elementos.get(0).click();
		//Calcamos la opción
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/user/list')]");
		elementos.get(0).click();
		//Estamos en español. Visualizamos algunos de los contenidos	
		PO_View.checkElement(driver, "text", "Usuarios");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar");
		SeleniumUtils.textoPresentePagina(driver, "Nombre");
		SeleniumUtils.textoPresentePagina(driver, "Apellidos");
		//Cambiamos a inglés y comprobamos 
		PO_NavView.changeIdiom(driver, "btnEnglish");
		PO_View.checkElement(driver, "text", "Users");
		SeleniumUtils.textoPresentePagina(driver, "Delete");
		SeleniumUtils.textoPresentePagina(driver, "Name");
		SeleniumUtils.textoPresentePagina(driver, "Last Name");
		//Volvemos a español
		PO_NavView.changeIdiom(driver, "btnSpanish");
		PO_View.checkElement(driver, "text", "Usuarios");
		SeleniumUtils.textoPresentePagina(driver, "Eliminar");
		SeleniumUtils.textoPresentePagina(driver, "Nombre");
		SeleniumUtils.textoPresentePagina(driver, "Apellidos");
		
		//Nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
		//Entramos como usuario
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		//Calcamos la opción
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/offer/add')]");
		elementos.get(0).click();
		//Estamos en español,Comprobamos estar en la página adecuada
		PO_View.checkElement(driver, "text", "Publicar");
		SeleniumUtils.textoPresentePagina(driver, "Añadir");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Detalles");
		SeleniumUtils.textoPresentePagina(driver, "Precio");
		SeleniumUtils.textoPresentePagina(driver, "Fecha");
		//Cambiamos a inglées y probamos
		PO_NavView.changeIdiom(driver, "btnEnglish");
		PO_View.checkElement(driver, "text", "Post");
		SeleniumUtils.textoPresentePagina(driver, "Add");
		SeleniumUtils.textoPresentePagina(driver, "Title");
		SeleniumUtils.textoPresentePagina(driver, "Details");
		SeleniumUtils.textoPresentePagina(driver, "Price");
		SeleniumUtils.textoPresentePagina(driver, "Date");
		//Volvemos a español y probamos
		PO_NavView.changeIdiom(driver, "btnSpanish");
		PO_View.checkElement(driver, "text", "Publicar");
		SeleniumUtils.textoPresentePagina(driver, "Añadir");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Detalles");
		SeleniumUtils.textoPresentePagina(driver, "Precio");
		SeleniumUtils.textoPresentePagina(driver, "Fecha");
		//Vamos a ver otra página,buscar ofertas
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		//Calcamos la opción
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '/offer/search')]");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "Ofertas");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Detalles");
		SeleniumUtils.textoPresentePagina(driver, "Comprado");
		SeleniumUtils.textoPresentePagina(driver, "Comprar");
		//Cambiamos a inglées y probamos
		PO_NavView.changeIdiom(driver, "btnEnglish");
		PO_View.checkElement(driver, "text", "Offers");
		SeleniumUtils.textoPresentePagina(driver, "Title");
		SeleniumUtils.textoPresentePagina(driver, "Details");
		SeleniumUtils.textoPresentePagina(driver, "Bought");
		SeleniumUtils.textoPresentePagina(driver, "Buy");
		//Volvemos a español y probamos
		PO_NavView.changeIdiom(driver, "btnSpanish");
		PO_View.checkElement(driver, "text", "Ofertas");
		SeleniumUtils.textoPresentePagina(driver, "Título");
		SeleniumUtils.textoPresentePagina(driver, "Detalles");
		SeleniumUtils.textoPresentePagina(driver, "Comprado");
		SeleniumUtils.textoPresentePagina(driver, "Comprar");
		//Nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
				
	}
	@Test
	// PR28.Intentar acceder sin estar autenticado a la opción de listado de usuarios del administrador.
	// Se deberá volver al formulario de login.
	public void PR28() {
		//Intentamos ir a la URL
		driver.navigate().to(URL+"/user/list");
		//Comprobamos que realmente no estamos en esa url si no en
		//el formulario de login
		PO_View.checkElement(driver, "text", "Identifícate");
		
	}
	@Test
	// PR29.Intentar acceder sin estar autenticado a la opción de listado de ofertas propias de un usuario estándar. 
	// Se deberá volver al formulario de login
	public void PR29() {
		//Intentamos ir a la URL
		driver.navigate().to(URL+"/offer/myOffers");
		//Comprobamos que realmente no estamos en esa url si no en
		//el formulario de login
		PO_View.checkElement(driver, "text", "Identifícate");
		
		
	}
	@Test
	// PR30.Estando  autenticado  como  usuario  estándar  intentar  acceder  a  la  opción  de  listado  de usuarios del administrador. 
	// Se deberá indicar un mensaje de acción prohibida.
	public void PR30() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		driver.navigate().to(URL+"/user/list");
		PO_View.checkElement(driver, "text", "Prohibido");
		//Nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
		
		
	}
	
	@Test
	// PR36.Al crear una oferta marcar dicha oferta como destacada y a continuación comprobar: i) que
	//aparece en el listado de ofertas destacadas para los usuarios y que el saldo del usuario se actualiza
	//adecuadamente en la vista del ofertante (-20).
	public void PR36() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		//Vamos a añadir oferta
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de añadir oferta.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/add')]");
		// Pinchamos en añadir oferta.
		elementos.get(0).click();
		//Esperamos a estar en la vista adecuada, deberia aparecer el botón publicar
		SeleniumUtils.EsperaCargaPagina(driver, "id", "publicar", PO_View.getTimeout());
		//Rellenamos el formulario y añadimos oferta
		PO_PrivateView.fillFormAddOffer(driver, "PlayStation 4", "500GB", "160",true);
		//Vamos a la página home, que es donde se encuentran todas las ofertas destacadas
		//de todos los usuarios
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'homeOption')]/a");
		elementos.get(0).click();
		//Comprobamos que aparece la oferta
		PO_View.checkElement(driver, "text", "PlayStation 4");
		//Vamos a comprobar ahora que sea visible para otros usuarios
		//Salimos de sesión		
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo2@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada y que aparece la oferta
		PO_View.checkElement(driver, "text", "ejemplo2@mail.es");
		PO_View.checkElement(driver, "text", "PlayStation 4");
		//Salimos de sesión
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
			
	}
	
	@Test
	// PR37.Sobre el listado de ofertas de un usuario con menos de 20 euros de saldo, pinchar en el
	// enlace Destacada y a continuación comprobar: i) que aparece en el listado de ofertas destacadas para los
	// usuarios y que el saldo del usuario se actualiza adecuadamente en la vista del ofertante (-20).
	
	//Nota, supongo que hay una errata en el enunciado de esta prueba y que se querría decir
	// con más de 20 euros, ya que la prueba que comprueba con menos de 20 es la 38
	// y el resto del enunciado parece indicar que el usuario tiene más de 20€
	public void PR37() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo2@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo2@mail.es");
		//Vamos a mis ofertas
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de mis ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/myOffers')]");
		// Pinchamos en mis ofertas.
		elementos.get(0).click();
		// Seleccionamos el botón de destacar oferta. Solo hay una destacable, ya que
		// las otras dos están compradas
		elementos = PO_View.checkElement(driver, "class", "destacar");
		elementos.get(0).click();
		//Comprobamos que se actualiza el saldo, baja de 100 a 80
		PO_View.checkElement(driver, "text", "80.0");
		//Vamos a home para comprobar que la oferta ha sido destacada
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'homeOption')]/a");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "Ratón antiguo");
		//Vamos a comprobar que está para todos los usuarios, entramos con otro
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada y que aparece la oferta
		//Las ofertas destacadas aparecen en home
		PO_View.checkElement(driver, "text", "ejemplo1@mail.es");
		PO_View.checkElement(driver, "text", "Ratón antiguo");
		//Salimos de sesión
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
			
	}
	@Test
	// PR38.Sobre el listado de ofertas de un usuario con menos de 20 euros de saldo, pinchar en el
	// enlace Destacada y a continuación comprobar que se muestra el mensaje de saldo no suficiente

	public void PR38() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo2@mail.es", "123456");
		// COmprobamos que entramos en la pagina privada.
		PO_View.checkElement(driver, "text", "ejemplo2@mail.es");
		// Vamos a comprar una oferta que deje el saldo por debajo de 20,
		// una que deja el saldo en 0
		// Pinchamos en la opción de menu de gestión de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de buscar ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/search')]");
		// Pinchamos
		elementos.get(0).click();
		//Comprobamos que estamos en la página correcta y tomamos como elemento el botón buscar
		elementos  = SeleniumUtils.EsperaCargaPagina(driver, "id", "searchButton", PO_View.getTimeout());
		//Escribimos un texto que concuerda con una oferta que deja saldo 0
		PO_PrivateView.fillSearchText(driver, "Sartén");
		elementos.get(0).click();
		//Cogemos el botón directamente con btn btn-success porque solo hay uno
		elementos = PO_View.checkElement(driver, "class", "btn btn-success");
		//Calcamos el botón comprar
		elementos.get(0).click();
		//Comprobamos que volvemos a la página de búsqueda. Hay botón de búsqueda
		SeleniumUtils.EsperaCargaPagina(driver, "id", "searchButton", PO_View.getTimeout());
		//Comprobamos el dinero que tenemos disponible es 0
		PO_View.checkElement(driver, "text", "0.0 €");
		//Vamos ahora a la vista de mis ofertas para intentar destacar la oferta
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'offers-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de mis ofertas.
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'offer/myOffers')]");
		// Pinchamos en mis ofertas.
		elementos.get(0).click();
		// Seleccionamos el botón de destacar oferta. Solo hay una destacable, ya que
		// las otras dos están compradas
		elementos = PO_View.checkElement(driver, "class", "destacar");
		elementos.get(0).click();
		// Comprobamos que aparece mensaje de error
		PO_View.checkElement(driver, "text", "No se ha podido destacar su oferta, revise su balance");
		//Vamos tambien a comprobar que no aparece la oferta entre las destacadas
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'homeOption')]/a");
		elementos.get(0).click();
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Ratón antiguo", PO_View.getTimeout());
		//Cerramos la sesión
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
			
	}
	
	
	
	

}