package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_PrivateView extends PO_NavView {

	static public void fillFormAddOffer(WebDriver driver, String titlep, String detallesp, String preciop,
			boolean promote) {
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
		// Rellenamos el checkbox;
		if(promote) {
			WebElement check = driver.findElement(By.name("promoted"));
			check.click();
		}
		// Pulsamos submit
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	static public void fillSearchText(WebDriver driver, String textp) {
		WebElement text = driver.findElement(By.name("searchText"));
		text.clear();
		text.sendKeys(textp);
	}
}