package com.fullstack.batch3;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fullstack.batch3.common.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseWebTest extends ObjectRepository implements DriverManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fullstack.batch3.DriverManager#createChromeDriver()
	 */
	@BeforeMethod(alwaysRun = true)
	public void setUpAndInitialize() {
		createChromeDriver();
		initializePageObjects();

	}

	/**
	 * 
	 */
	public void initializePageObjects() {
		loginPage = new LoginPage(driver);
		profilePage = new ProfilePage(driver);
		commonPage = new CommonPage(driver);

	}

	public void createChromeDriver() {
		TestUtils.hardWait(1);
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		driver.set(new ChromeDriver());
		driver.get().manage().window().maximize();

		/*
		 * flags sample Map<String, Object> prefs = new HashMap<>();
		 * prefs.put("profile.default_content_setting_values.notifications", 2);
		 * prefs.put("download.default_directory", System.getProperty("user.dir") +
		 * System.getProperty("file.separator") +
		 * TestUtils.getProperty("selenium.downloadFileFolder"));
		 * 
		 * ChromeOptions options = new ChromeOptions();
		 * options.addArguments("--use-fake-ui-for-media-stream");
		 * prefs.put("profile.default_content_setting_values.popups", 1);
		 * options.setExperimentalOption("prefs", prefs);
		 */
		/*
		 * headless mode ChromeOptions chromeOptions = new ChromeOptions();
		 * chromeOptions.addArguments("--headless"); driver.set(new
		 * ChromeDriver(chromeOptions)); driver.get().manage().window().maximize();
		 */

		/*
		 * mobile emulation code Map<String, String> mobileEmulation = new HashMap<>();
		 * 
		 * mobileEmulation.put("deviceName", "Nexus 5");
		 * 
		 * ChromeOptions chromeOptions = new ChromeOptions();
		 * chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
		 * 
		 * driver.set(new ChromeDriver(chromeOptions)); //
		 * driver.get().manage().window().maximize();
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fullstack.batch3.DriverManager#createFirefoxDriver()
	 */

	public void createFirefoxDriver() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fullstack.batch3.DriverManager#closeDriver()
	 */
	@AfterMethod(alwaysRun = true)
	public void cleanUpActivities(ITestResult itr) {

		if (itr.getStatus() == ITestResult.FAILURE) {
			captureScreenshotInCaseOfFailure(itr);
		}

		closeDriver();

	}

	public void closeDriver() {

		try {
			driver.get().quit();
			TestUtils.hardWait(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void captureScreenshotInCaseOfFailure(ITestResult itr) {

		try {
			File sourceFile = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);

			String fileName = itr.getName() + System.currentTimeMillis();

			File destinationFile = new File(System.getProperty("user.dir") + File.separator + "screenshots"
					+ File.separator + fileName + ".png");

			FileUtils.copyFile(sourceFile, destinationFile);
		} catch (WebDriverException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
