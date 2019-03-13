package com.uniovi.tests;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MyWallapopTests {
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

	// @Test
	// PR01. Registro de Usuario con datos válidos
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
	// @Test
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
	// @Test
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
	// @Test
	public void PR04() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "santi@email.es", "Santiago", "Perez", "123456", "123456");
		PO_View.getP();
		// COmprobamos el error de mail repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.mail.duplicate", PO_Properties.getSPANISH());

	}

	// -----------------INICIO DE SESIÓN----------------------

	// PR05. Inicio de sesión con datos válidos (administrador).
	// @Test
	public void PR05() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		// COmprobamos que entramos en la pagina privada de Administrador
		PO_View.checkElement(driver, "text", "Gestión Usuarios");
	}

	// PR06. Inicio de sesión con datos válidos (usuario estándar).
	// @Test
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
	// @Test
	public void PR07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario vacío.
		PO_LoginView.fillForm(driver, "", "");
		// Comprobamos que seguimos en la vista de login
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// PR08. Inicio de sesión con datos válidos (usuario estándar, email existente,
	// pero contraseña
	// incorrecta).
	// @Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario, contraseña incorrecta.
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "admin");
		// Comprobamos que seguimos en la vista de login
		PO_View.checkElement(driver, "text", "Identifícate");

	}

	// PR09. Inicio de sesión con datos inválidos (usuario estándar, email no
	// existente en la aplicación).
	// @Test
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
	// a la página de inicio
	// de sesión (Login).
	@Test
	public void PR10() {
		//iniciamos sesión
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ejemplo1@mail.es", "123456");
		// Nos desconectamos.
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Comprobamos que volvemos a la vista de login
		PO_View.checkElement(driver, "text", "Identifícate");

	}

	// PR11. Comprobar que el botón cerrar sesión no está visible si el usuario no
	// está autenticado.
	// @Test
	public void PR11() {

	}

}
