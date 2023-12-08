package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import pages.BasePage;
import pages.MyAccountPage;

public class BaseTest {

	public static WebDriver driver;
	public BasePage app;
	
	@Parameters({"url"})
	@BeforeClass(alwaysRun = true)
	public void setup(String url) {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();//maximizeaza fereastra de browser
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(url);
		app = new BasePage();
		
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		Thread.sleep(4000);
		//driver.close();//inchide tabul curent
		driver.quit(); //inchide instanta de browser
		
	}
	
	
	
}
