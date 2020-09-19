package com.fullstack.batch3;

import org.openqa.selenium.WebDriver;

public interface DriverManager {
	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	void createChromeDriver();

	void createFirefoxDriver();

	void closeDriver();
}
