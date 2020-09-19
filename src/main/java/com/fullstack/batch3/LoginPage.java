package com.fullstack.batch3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	By loginLink = By.xpath("//a[contains(text(),'Log In')]");
	By usernameTextField = By.id("username");
	By passwordTextField = By.id("password");
	By loginButton = By.xpath("//button[contains(text(),'Login')]");

	/**
	 * @param driverTest
	 */
	public LoginPage(ThreadLocal<WebDriver> driverTest) {
		super(driverTest);
		PageFactory.initElements(driverTest.get(), this);
	}

	public void login(String username, String password) {

		clickAndWait(loginLink);
		setText(usernameTextField, username);
		setText(passwordTextField, password);
		clickAndWait(loginButton);

	}

}
