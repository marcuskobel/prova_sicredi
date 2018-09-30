package page_objects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import selenium.SeleniumDriver;

public class MainPage extends PageObject {
	
	public MainPage( SeleniumDriver driver ) {
		super(driver);
		setupPageElements();
	}

	public void setupPageElements() {
		// define all WebElements that will be interacted
		defineWebElement( "dropdownSwitchVersion",  "//select[@id='switch-version-select']" ); //= new Select( driver.chrome.findElement( By.xpath("") ) );
		defineWebElement( "buttonAddCustomer", "//a[ contains(@href, '/demo/bootstrap_theme') and contains(@href, '/add') ]");
		defineWebElement( "selectPerPage", "//select[@name='per_page']" );
		defineWebElement( "linkMagnifier", "//a[contains(@class, 'search-button') and @href='javascript:;']");
		defineWebElement( "inputNameSearch", "//input[@type='text' and @name='search']" );
		defineWebElement( "inputSelectAllCheckbox", "//input[@type='checkbox' and @class='select-all-none']");
		defineWebElement( "buttonDeleteAll", "//div[@class='floatL']/a[@title='Delete']");
		defineWebElement( "textDeleteConfirmation", "//div[@class='delete-multiple-confirmation modal fade in show']/div/div/div[@class='modal-body']/p[@class='alert-delete-multiple-one']");
		defineWebElement( "buttonDeleteAllConfirmation", "//button[@type='button' and @data-target='/demo/bootstrap_theme_v4/delete_multiple']");
		defineWebElement( "textDeleteSuccessful", "//div[@data-growl='container']/span[@data-growl='message']");
	}
	
	public void validatePageLoaded() {
		System.out.println("Waiting for element to display in the screen.");
		
		waitForElement( getWebElement( "selectPerPage" ) );
	}
	
	public void selectBootstrapVersion( String bootstrapVersion ) {
		System.out.println("Switching bootstrap version to: " + bootstrapVersion);
		
		Select dropdownSwitchVersion = new Select( getWebElement( "dropdownSwitchVersion" ) );
		dropdownSwitchVersion.selectByVisibleText( bootstrapVersion );
	}
	
	public void clickButtonAddCustomer() {
		System.out.println("Clicking Add Customer button.");
		
		getWebElement( "buttonAddCustomer" ).click();
	}
	
	public void clickMagnifierIcon() {
		System.out.println("Clicking Magnifier for searching.");

		// link definition postponed
		getWebElement( "linkMagnifier" ).click();
		waitASecond( 1 );
	}
	
	public void setNameSearch( String nameSearch ) {
		System.out.println("Search for name = " + nameSearch);
		
		WebElement inputNameSearch = getWebElement("inputNameSearch");
		inputNameSearch.sendKeys( nameSearch );
		inputNameSearch.sendKeys( Keys.ENTER );
		waitASecond( 3 );
	}

	public void clickSelectAllCheckbox() {
		System.out.println("Clicking Select All checkbox.");
		
		getWebElement( "inputSelectAllCheckbox" ).click();
	}
	
	public void clickDeleteAllButton() {
		System.out.println("Clicking Delete All button.");
		
		getWebElement( "buttonDeleteAll").click();	
		waitASecond( 1 );
	}

	public void assertDeleteMessage( String confirmationMessage ) {
		System.out.println("Asserting delete message is correct.");
		
		assertEquals( getWebElement( "textDeleteConfirmation" ).getText(), confirmationMessage );
	}
	
	
	public void clickDeleteConfirmationButton() {
		System.out.println("Clicking Delete All Confirmation button.");
		
		getWebElement( "buttonDeleteAllConfirmation" ).click();
		
		waitASecond( 2 );
	}
	
	public void assertDeleteSuccessfulMessage( String successfulMessage ) {
		System.out.println("Asserting delete successful message is correct.");
		
		assertEquals( getWebElement( "textDeleteSuccessful" ).getText(), successfulMessage);
	}
}
