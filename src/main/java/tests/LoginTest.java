package tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.BaseTest;

public class LoginTest extends BaseTest{
	
	@Parameters({"user", "pass"})
	@Test
	public void validLogin(String user, String pass) {
		
		app.click(app.menu.myAccountLink);
		app.myAccount.loginInApp(user, pass);
	}
	

}
