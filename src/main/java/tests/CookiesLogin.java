package tests;

import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.BaseTest;

public class CookiesLogin extends BaseTest{
	
	Set<Cookie> cookies;
	
	@Parameters({"user", "pass"})
	@Test
	public void validLogin(String user, String pass) {		
		app.click(app.menu.myAccountLink);
		app.myAccount.loginInApp(user, pass);		
		assertTrue(app.elementIsDisplayed(app.myAccount.myAccountContent));

		cookies = driver.manage().getCookies();
		
	}
	
	@Test(priority=2)
	public void cookiesLogin() throws InterruptedException {
		
		app.click(app.menu.myAccountLink);
		for(Cookie cook : cookies) {
			
			driver.manage().addCookie(cook);
		}
		Thread.sleep(3000);
		driver.navigate().refresh();
		
	}
	
}
