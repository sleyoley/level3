package com.fullstack.batch3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.fullstack.batch3.common.DataUtils;

public class ProfilePage extends BasePage {

	By profileUsername = By
			.xpath("//strong[contains(text(),'" + DataUtils.getDataFromExcel("WebData", "Username") + "')]");

	/**
	 * @param driverTest
	 */
	public ProfilePage(ThreadLocal<WebDriver> driverTest) {
		super(driverTest);
		PageFactory.initElements(driverTest.get(), this);

	}

	public String getUserProfileName() {
		return getText(profileUsername);
	}
}
