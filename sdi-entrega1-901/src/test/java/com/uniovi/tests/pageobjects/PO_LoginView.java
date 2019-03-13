package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_LoginView extends PO_NavView{ 
	
	static public void fillForm(WebDriver driver, String mailp, String passwordp) {
		WebElement mail = driver.findElement(By.name("username"));
		mail.click();
		mail.clear();
		mail.sendKeys(mailp);
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(passwordp);
		// Pulsar el boton de Login.
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

}