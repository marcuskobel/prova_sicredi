package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDriver {
	public WebDriver chrome;
	
	public SeleniumDriver() {
		// specify chrome driver location
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver/chromedriver.exe");
		// create chrome driver object
		System.out.println("Starting Chrome browser.");
		chrome = new ChromeDriver();
		chrome.manage().deleteAllCookies();
		chrome.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		chrome.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		chrome.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		// maximize browser
		chrome.manage().window().maximize();
	}
	
	public void openBrowser( String url ) {
		// navigate to URL
		System.out.println("Navigating to URL: " + url);
		chrome.get(url);
	}
	
	
	public void closeBrowser() {
		System.out.println("Closing Chrome.");
		chrome.quit();
	}
}
