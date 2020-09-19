package com.fullstack.batch3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CommonPage extends BasePage {

	/**
	 * @param driverTest
	 */
	public CommonPage(ThreadLocal<WebDriver> driverTest) {
		super(driverTest);
		PageFactory.initElements(driverTest.get(), this);

	}

}
