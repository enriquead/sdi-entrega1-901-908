package com.uniovi.tests;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.OffersRepository;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.OffersService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MyWallapopTests {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private OffersService offersService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private OffersRepository offersRepository;

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Users\\media service\\Desktop\\SDI\\p5\\FirefoxPortable\\App\\Firefox64\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\media service\\Desktop\\SDI\\p5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas):
	// static String PathFirefox65 =
	// "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	// static String Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		//initdb();
		driver.navigate().to(URL);
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
		// driver.quit();
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
	// @Test
	public void PR12() {

	}

	// PR13. Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza y dicho usuario desaparece.
	// @Test
	public void PR13() {

	}

	// PR14. Ir a la lista de usuarios, borrar el último usuario de la lista,
	// comprobar que la lista se actualiza y dicho usuario desaparece.
	// @Test
	public void PR14() {

	}

	// PR15. Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se
	// actualiza y dichos usuarios desaparecen.
	// @Test
	public void PR15() {

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
		// Ahora vamos a rellenar la oferta.
		PO_PrivateView.fillFormAddOffer(driver, "Cascos", "Cascos Meizu 5.0", "90");
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
		// Ahora vamos a rellenar la oferta sin titulo.
		PO_PrivateView.fillFormAddOffer(driver, " ", "Segunda mano Bosch", "90");
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
				"//td[contains(text(), 'Cascos')]/following-sibling::*/a[contains(@href, 'offer/delete')]");
		elementos.get(0).click();
		// Comprobamos que ya no figuran en la lista de mis ofertas.
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Cascos", PO_View.getTimeout());
		// nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

}
