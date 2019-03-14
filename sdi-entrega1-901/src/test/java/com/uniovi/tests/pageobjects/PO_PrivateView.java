package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {
	
	static public void fillFormAddOffer(WebDriver driver, String titlep, String detallesp, String preciop) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Rellenemos el campo de titulo
		WebElement title = driver.findElement(By.name("title"));
		title.clear();
		title.sendKeys(titlep);
		// Rellenemos el campo de descripcion
		WebElement detalles = driver.findElement(By.name("details"));
		detalles.click();
		detalles.clear();
		detalles.sendKeys(detallesp);
		// Rellenemos el campo de precio
		WebElement precio = driver.findElement(By.name("price"));
		precio.click();
		precio.clear();
		precio.sendKeys(preciop);
		//Pulsamos submit
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}