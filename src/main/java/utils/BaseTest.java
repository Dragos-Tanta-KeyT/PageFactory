package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;

import pages.BasePage;
import pages.MyAccountPage;

public class BaseTest {

	public static WebDriver driver;
	public BasePage app;
	
	@Parameters({"url"})
	@BeforeMethod(alwaysRun = true)
	public void setup(String url) {
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();//maximizeaza fereastra de browser
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(url);
		app = new BasePage();
		
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		Thread.sleep(4000);
		//driver.close();//inchide tabul curent
		driver.quit(); //inchide instanta de browser
		
	}
	
	@AfterMethod
	public void recordFailure(ITestResult result) {
		
		if(ITestResult.FAILURE == result.getStatus()) {
			
			TakesScreenshot poza = (TakesScreenshot)driver;
			File picture = poza.getScreenshotAs(OutputType.FILE);
			
			try {
				String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
				Files.copy(picture, new File("poze/" + result.getName() + "-" + timestamp + ".png"));
				System.out.println("Saved picture "+ result.getName() + "-" + timestamp + ".png");
				
			}catch(Exception e) {
				System.out.println("Could not save picture!");
				System.out.println(e.getMessage());
			}
			
		}
		
		
	}
	
	
	
}
