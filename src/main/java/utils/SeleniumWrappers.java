package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

public class SeleniumWrappers extends BaseTest{

	public SeleniumWrappers(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Custom click method, that waits for an element to be visible before 
	 * triggering the click from Selenium
	 * @param element --> webelement to act on
	 */
	public void click(WebElement element) {	
		Log.info("Called method <click> on element " + element.getAttribute("outerHTML"));
		try {
			waitForElementToBeVisible(element);
			element.click();
		Log.info("click() performed sucessfully");	
		}catch (StaleElementReferenceException e) {	
			Log.error("StaleElement occured on element "+ element.getAttribute("outerHTML") );
			Log.info("attempting retry on click()");
			element.click();
			Log.info("click() performed sucessfully");	
		}catch(Exception e) {
			Log.error("Error in method <click()> on element " + element.getAttribute("outerHTML"));
			Log.error(e.getMessage());
			throw new TestException(e.getMessage());
		}
	}
	
	
	public void sendKeys(WebElement element, String text) {
		Log.info("called method <sendKeys()> on element " + element.getAttribute("outerHTML"));
		try {
			waitForElementToBeVisible(element);
		Log.info("called method <clear()> on element " + element.getAttribute("outerHTML"));
			element.clear();
			element.sendKeys(text);
		Log.info("<sendKeys> performed sucessfully");	
		}catch(Exception e) {
			Log.error("Error on method <sendKeys()> on element " + element.getAttribute("outerHTML"));
			Log.error(e.getMessage());
			throw new TestException(e.getMessage());
		}

	}
	
	
	public WebElement returnElement(By locator) {
		return driver.findElement(locator);
	}
	
	public void waitForElementToBeVisible(WebElement element) {
		Log.info("called <waitForElementToBeVisible() on >" + element.getAttribute("outerHTML"));
		Log.info("Wait duration 10 sec with condition 'visibilityOf'");
		try {
			WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(Exception e) {
		Log.error("Error on method <waitForElementToBeVisible()> on element " + element.getAttribute("outerHTML"));
		Log.error(e.getMessage());
			throw new TestException(e.getMessage());		}

	
	}
	
	public boolean elementIsDisplayed(WebElement element) {
		waitForElementToBeVisible(element);
		return element.isDisplayed();
	}
	
	
	public void hoverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	
	public void dragAndDrop(WebElement element, int x, int y) {
		Actions action = new Actions(driver);
		//action.dragAndDropBy(returnElement(locator), x, y).perform();
		action.moveToElement(element).clickAndHold().moveByOffset(x, y).release().perform();
		
	}
	
	public void scrollVerically(int pixels) {
		Actions action = new Actions(driver);
		action.scrollByAmount(0, pixels).perform();		
	}
	
	public void scrollHorizontally(int pixels) {
		Actions action = new Actions(driver);
		action.scrollByAmount(pixels, 0).perform();		
	}
	
	public void jsScrollVerically(int pixels) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0, "+pixels+")");	
	}

}
