package com.fullstack.batch3;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.fullstack.batch3.common.DataUtils;
import com.fullstack.batch3.common.TestUtils;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseWebTest {

	@Test(priority = 1, groups = {
			"RegressionTest" }, description = "Verify that login is working correctly with correct username and password")

	public void loginWithCorrectCredentials() {

		commonPage.openUrl(DataUtils.getDataFromExcel("WebData", "BaseUrl"));
		loginPage.login(DataUtils.getDataFromExcel("WebData", "Username"),
				DataUtils.getDataFromExcel("WebData", "Password"));
		String actualUsername = profilePage.getUserProfileName();

		assertEquals(actualUsername, DataUtils.getDataFromExcel("WebData", "Username"));

	}

	@Test(groups = { "SanityTest" }, description = "Verify that the title of website is correct")

	public void verifyTitle() {

		commonPage.openUrl(DataUtils.getDataFromExcel("WebData", "BaseUrl"));
		String title = profilePage.getWebPageTitle();
		assertEquals(title, "Home");

	}

	@Test
	public void testNavigation() {
		commonPage.openUrl(DataUtils.getDataFromExcel("WebData", "BaseUrl"));
		TestUtils.hardWait(5);
		commonPage.openUrl("https://facebook.com");
		TestUtils.hardWait(5);
		commonPage.goBack();
		TestUtils.hardWait(5);
		commonPage.goForward();
		TestUtils.hardWait(5);
		commonPage.refresh();
		TestUtils.hardWait(5);
	}

	@Test
	public void testSwitches() {
		commonPage.openUrl(DataUtils.getDataFromExcel("WebData", "BaseUrl"));
		commonPage.createTab();
		commonPage.createWindow();
		commonPage.switchDriver(2);
		commonPage.switchDriver(1);
		commonPage.switchDriver(0);

	}

	@Test
	public void testJSScript() {
		String script = "alert('This is a level 2')";
		commonPage.executeJS(script);
		TestUtils.hardWait(5);
		commonPage.acceptAlert();

	}

}
