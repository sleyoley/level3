package com.fullstack.batch3;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	ThreadLocal<WebDriver> driverPage = new ThreadLocal<WebDriver>();
	protected static Logger log = LogManager.getLogger(BasePage.class.getName());

	WebDriverWait explicitwait;

	public BasePage(ThreadLocal<WebDriver> driverTest) {
		this.driverPage = driverTest;
		explicitwait = new WebDriverWait(driverPage.get(), Duration.ofSeconds(60));

	}

	protected final void clickAndWait(By elementLocator) {
		log.info("Trying to click on " + elementLocator.toString());
		explicitwait.until(ExpectedConditions.elementToBeClickable(elementLocator));
		driverPage.get().findElement(elementLocator).click();
		log.info("click on " + elementLocator.toString() + " is successfull");

	}

	protected final void setText(By elementLocator, String textToWrite) {
		log.info("Trying to type on " + elementLocator.toString());

		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		driverPage.get().findElement(elementLocator).sendKeys(textToWrite);
		log.info("typing on " + elementLocator.toString() + " is successfull");

	}

	protected final String getText(By elementLocator) {
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		return driverPage.get().findElement(elementLocator).getText();
	}

	protected final void openUrl(String url) {
		log.info("Trying to open url " + url);

		driverPage.get().get(url);
		log.info("successfull in opening the url");

	}

	protected final String getWebPageTitle() {
		log.info("Trying to get title of page ");

		return driverPage.get().getTitle();

	}

	protected final void goForward() {
		log.info("Trying to go forward in webpage ");

		driverPage.get().navigate().forward();

	}

	protected final void goBack() {
		log.info("Trying to go back in webpage ");

		driverPage.get().navigate().back();

	}

	protected final void refresh() {
		log.info("Trying to refresh in webpage ");

		driverPage.get().navigate().refresh();

	}

	protected final void createTab() {
		driverPage.get().switchTo().newWindow(WindowType.TAB);
	}

	protected final void createWindow() {
		log.info("Trying to create new window ");

		driverPage.get().switchTo().newWindow(WindowType.WINDOW);

	}

	protected final void switchDriver(int index) {
		log.info("Trying to switch..");

		Set<String> handles = driverPage.get().getWindowHandles();

		for (String s : handles) {
			System.out.println("windows details are:" + s);
		}

		ArrayList<String> handleList = new ArrayList<String>(handles);

		driverPage.get().switchTo().window(handleList.get(index));

	}

	protected final void executeJS(String script) {
		log.info("Trying to execute js.." + script);

		JavascriptExecutor jse = (JavascriptExecutor) driverPage.get();
		jse.executeScript(script);

	}

	protected final void acceptAlert() {
		log.info("Trying to switch to alert and aceept it..");

		driverPage.get().switchTo().alert().accept();
	}
}
