package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.SeleniumWrappers;

public class MyAccountPage extends SeleniumWrappers{
	
	public MyAccountPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="username")
	public WebElement usernameField;
	
	@FindBy(id="password")
	public WebElement passwordField;
	
	@FindBy(css="button[name='login']")
	public WebElement loginButton;
	
	@FindBy(xpath = "//div[@class='woocommerce-MyAccount-content']")
	public WebElement myAccountContent;
	
	@FindBy(linkText = "Log out")
	public WebElement logoutButton;
	
	
	public void loginInApp(String user, String pass) {
		sendKeys(usernameField, user);
		sendKeys(passwordField, pass);
		click(loginButton);
	}
	
	
}
