package tests;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

import utils.BaseTest;

public class CookiesHandling extends BaseTest{

	@Test(priority=1)
	public void readAllCookies() {
		driver.get("http://keybooks.ro");
		
		//add cookie
		driver.manage().addCookie(new Cookie("My cookie", "My cookie value"));
		//get all cookies
		Set<Cookie> cookies = driver.manage().getCookies();
		System.out.println(cookies.size());
		System.out.println(cookies);
		//get cookie named
		Cookie cookie = driver.manage().getCookieNamed("PHPSESSID");
		System.out.println(cookie);
		//delete cookie
		driver.manage().deleteCookieNamed("PHPSESSID");
		driver.manage().deleteAllCookies();
		
		
	}
	
	
}
