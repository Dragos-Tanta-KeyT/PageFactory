package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {
	
	
//	public WebDriver driver;
	public static ThreadLocal<WebDriver> driver =  new ThreadLocal<WebDriver>();
	
	public WebDriver initBrowser(String browser) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			try {
				Log.info("Browser parameter is Chrome");
				//driver = new ChromeDriver();
				driver.set(new ChromeDriver(getChromeOptions()));
				return driver.get();
			}catch(Exception e) {
				Log.error("Could not setup Chrome");
				Log.error(e.getMessage());
			}
		
		}else if(browser.equalsIgnoreCase("firefox")) {
			try {
				Log.info("Browser parameter is Firefox");
				//driver = new FirefoxDriver();
				driver.set(new FirefoxDriver(getFirefoxOptions()));
				return driver.get();
			}catch(Exception e) {
				Log.error("Could not setup Firefox");
				Log.error(e.getMessage());
			}

			
		}else if(browser.equalsIgnoreCase("edge")) {
			try {
				Log.info("Browser parameter is Edge");
				//driver = new EdgeDriver();
				driver.set(new EdgeDriver(getEdgeOptions()));
			}catch (Exception e) {
				Log.error("Could not setup Firefox");
				Log.error(e.getMessage());			}
		}
		return driver.get();
		
	}
	
	
	public static ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--window-size=1580,1280");
		return options;
	}

	public static FirefoxOptions getFirefoxOptions() {
		FirefoxBinary binary =  new FirefoxBinary();
		binary.addCommandLineOptions("--headless");
		FirefoxOptions options =  new FirefoxOptions();
		options.setBinary(binary);
		
		return options;
	}
	
	public static EdgeOptions getEdgeOptions() {
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--headless");
		return options;
	}
}
