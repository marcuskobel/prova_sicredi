package page_objects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;

import selenium.SeleniumDriver;

public class AddCustomerPage extends PageObject {
	
	public AddCustomerPage( SeleniumDriver driver ) {
		super(driver);
		setupPageElements();
	}
	
	public void setupPageElements() {
		// define all WebElements that will be interacted
		defineWebElement( "inputName", "//input[@id='field-customerName']" );
		defineWebElement( "inputLastName", "//input[@id='field-contactLastName']" );
		defineWebElement( "inputContactFirstName", "//input[@id='field-contactFirstName']" );
		defineWebElement( "inputPhone", "//input[@id='field-phone']" );
		defineWebElement( "inputAddressLine1", "//input[@id='field-addressLine1']" );
		defineWebElement( "inputAddressLine2", "//input[@id='field-addressLine2']" );
		defineWebElement( "inputCity", "//input[@id='field-city']" );
		defineWebElement( "inputState", "//input[@id='field-state']" );
		defineWebElement( "inputPostalCode", "//input[@id='field-postalCode']" );
		defineWebElement( "inputCountry", "//input[@id='field-country']" );
		defineWebElement( "linkChosenEmployer", "//a[@class='chosen-single chosen-default']");
		defineWebElement( "inputSearchEmployer", "//div[@class='chosen-drop']/div/input[@type='text' and @autocomplete='off']" );
		defineWebElement( "inputCreditLimit", "//input[@id='field-creditLimit']" );
		defineWebElement( "buttonSave", "//button[@id='form-button-save']" );
		defineWebElement( "divReportSuccess", "//div[@id='report-success' and @style='']");
		defineWebElement( "linkGoBackToList", "//a[@href='/demo/bootstrap_theme_v4' and text()='Go back to list']");
	}

	public void validatePageLoaded() {
		System.out.println("Validating page Add Customer has openned.");
		waitForElement( getWebElement( "buttonSave" ) );
	}

	public void setCustomerInformation(String name, String contactLastName, String contactFirstName, String phone,
			String addressLine1, String addressLine2, String city, String state, String postalCode, String country,
			String fromEmployer, String creditLimit) {
		
		System.out.println("Setting customer information.");

		getWebElement( "inputName" ).sendKeys( name ); 
		getWebElement( "inputLastName" ).sendKeys( contactLastName );
		getWebElement( "inputContactFirstName" ).sendKeys( contactFirstName );
		getWebElement( "inputPhone" ).sendKeys( phone );
		getWebElement( "inputAddressLine1" ).sendKeys( addressLine1 );
		getWebElement( "inputAddressLine2" ).sendKeys( addressLine2 );
		getWebElement( "inputCity" ).sendKeys( city );
		getWebElement( "inputState" ).sendKeys( state );
		getWebElement( "inputPostalCode" ).sendKeys( postalCode );
		getWebElement( "inputCountry" ).sendKeys( country );
		// little trick to deal with this dropdown
		getWebElement( "linkChosenEmployer" ).click();
		getWebElement( "inputSearchEmployer" ).sendKeys( fromEmployer );
		getWebElement( "inputSearchEmployer" ).sendKeys( Keys.ENTER );
		getWebElement( "inputCreditLimit" ).sendKeys( creditLimit );
	}

	public void clickSaveButton() {
		System.out.println("Clicking button Save.");
		
		getWebElement( "buttonSave" ).click();
		waitASecond(3);
	}

	public void assertSuccessMessage( String expectedMessage ) {
		System.out.println("Asserting success message displays in application.");
		
		// assert text is displayed in the screen
		assertEquals( getWebElement( "divReportSuccess" ).getText().trim(), expectedMessage);
	}
	
	public void clickLinkGoBackToList() {
		System.out.println("Clicking link Go Back to List.");
		
		getWebElement( "linkGoBackToList" ).click();
	}
}
