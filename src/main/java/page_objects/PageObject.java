package page_objects;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import selenium.SeleniumDriver;

public class PageObject {

	public SeleniumDriver driver;
	private Map<String, String> pageElements;
	static private int MAX_RETRIES = 30;

	public PageObject( SeleniumDriver sd ) {
		driver = sd;
		pageElements = new HashMap<String, String>();
	}
	
	public void defineWebElement( String elementName, String elementXPath ) {
		// insert page elements in a Map
		pageElements.put(elementName, elementXPath);
	}

	public WebElement getWebElement( String elementName ) {
		// grab page element from Map and return its WebElement
		String elementXPath = "";
		WebElement pageElement = null;
		
		// make sure that element is correctly retrieved from Map 
		try {
			elementXPath = pageElements.get(elementName);
		} catch ( NullPointerException e ) {
			System.out.println("WebElement '" + elementName + "' was not defined for this page. Make sure you have called defineWebElement() for this object.");
			//e.printStackTrace();
			System.exit(1);
		}
		
		// make selenium search for the webelement and finds it before returning it
		try {
			pageElement = driver.chrome.findElement( By.xpath( elementXPath ) );
			
		} catch ( NoSuchElementException e ) {
			System.out.println("WebElement '" + elementName + "' was not found in the application. Element XPath = " + elementXPath);
			//e.printStackTrace();
			System.exit(1);
		}
		 
		return pageElement;
	}

	public void waitForElement( WebElement element ) {
		int retries = 0;
		
		while( retries < MAX_RETRIES ) {
			try { if( element.isDisplayed() ) { break; } }
			catch( StaleElementReferenceException e ) {}
			
			waitASecond(1);			
			retries++;
		}
		
		if( retries == MAX_RETRIES ) {
			throw new RuntimeException("Element not found in the screen.");
		}
	}
	
	public void waitASecond( int sleepInSeconds ) {
		// sleep for seconds
		try { Thread.sleep( sleepInSeconds * 1000 );} 
		catch (InterruptedException e) { e.printStackTrace();	}
	}
}
