package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWrappers extends BaseTest{

	
	
	public void click(WebElement element) {
		
		try {
			//WebElement element = driver.findElement(locator);
			waitForElementToBeVisible(element);
			element.click();
		}catch (StaleElementReferenceException e) {
			
			element.click();
		}

		
	}
	
	
	public void sendKeys(WebElement element, String text) {
		//WebElement element = driver.findElement(locator);
	//	waitForElementToBeVisible(locator);
		element.clear();
		element.sendKeys(text);
	}
	
	
	public WebElement returnElement(By locator) {
		return driver.findElement(locator);
	}
	
	public void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	
	}
	
	public boolean elementIsDisplayed(By locator) {
	//	waitForElementToBeVisible(locator);
		return driver.findElement(locator).isDisplayed();
	}
	
	
	public void hoverElement(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(returnElement(locator)).perform();
	}
	
	
	public void dragAndDrop(By locator, int x, int y) {
		Actions action = new Actions(driver);
		//action.dragAndDropBy(returnElement(locator), x, y).perform();
		action.moveToElement(returnElement(locator)).clickAndHold().moveByOffset(x, y).release().perform();
		
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
