package tests;

import org.testng.annotations.*;

import page_objects.AddCustomerPage;
import page_objects.MainPage;
import selenium.SeleniumDriver;

public class ProvaSicredi {
	
	private SeleniumDriver driver;
	
	@BeforeTest(alwaysRun=true)
	public void initializeTest() {
		driver = new SeleniumDriver();
	}
	
	
	@DataProvider // parameters for desafio1 test	 
	public Object[][] dataProviderDesafio1() {
		return new Object[][] {
			new Object[] { "https://www.grocerycrud.com/demo/bootstrap_theme", "Bootstrap V4 Theme", "Teste Sicredi", "Teste", "Marcus", 
					"51 9999-9999",	"Av Assis Brasil, 3970", "Torre D", "Porto Alegre", "RS", "91000-000", "Brasil", "Fixter", "200",
					"Your data has been successfully stored into the database. Edit Customer or Go back to list"}
		};
	}
	
	@Test (dataProvider="dataProviderDesafio1")
	public void desafio1( String url, String bootstrapVersion, String name, String contactLastName, String contactFirstName, String phone,
			String addressLine1, String addressLine2, String city, String state, String postalCode, String country,
			String fromEmployer, String creditLimit, String expectedSuccessMessage ) {
		
		System.out.println("====================================================================");
		System.out.println("Executing the DESAFIO 1 test - Add a new Customer in application");
		System.out.println("====================================================================");
		
		// open chrome browser and navigate to URL
		driver.openBrowser(url);
		
		// Main page
		MainPage pMain = new MainPage(driver);
		pMain.validatePageLoaded();
		pMain.selectBootstrapVersion( bootstrapVersion );
		pMain.clickButtonAddCustomer();
		
		// page Add Customer
		AddCustomerPage pAddCustomer = new AddCustomerPage(driver);
		pAddCustomer.validatePageLoaded();		
		pAddCustomer.setCustomerInformation(name, contactLastName, contactFirstName, phone, addressLine1, addressLine2, 
				city, state, postalCode, country, fromEmployer, creditLimit);
		
		pAddCustomer.clickSaveButton();
		pAddCustomer.assertSuccessMessage( expectedSuccessMessage );
	}

	
	@DataProvider // parameters for desafio1 test	 
	public Object[][] dataProviderDesafio2() {
		return new Object[][] {
			new Object[] { "Teste Sicredi", "Are you sure that you want to delete this 1 item?",
					"Your data has been successfully deleted from the database." }
		};
	}
	
	@Test (dataProvider = "dataProviderDesafio2", 
			dependsOnMethods = { "desafio1" } )
	public void desafio2( String nameSearch, String deleteConfirmationMessage, String deleteSuccessfulMessage ) {
		System.out.println("====================================================================");
		System.out.println("Executing the DESAFIO 2 test - Search and delete customer");
		System.out.println("====================================================================");

		// page Add Customer
		AddCustomerPage pAddCustomer = new AddCustomerPage(driver);
		pAddCustomer.clickLinkGoBackToList();
		
		// Main page
		MainPage pMain = new MainPage(driver);
		pMain.validatePageLoaded();
		pMain.clickMagnifierIcon();
		pMain.setNameSearch(nameSearch);
		pMain.clickSelectAllCheckbox();
		pMain.clickDeleteAllButton();
		pMain.assertDeleteMessage( deleteConfirmationMessage );
		pMain.clickDeleteConfirmationButton();
		pMain.assertDeleteSuccessfulMessage( deleteSuccessfulMessage );
	}

	@AfterTest
	public void finalizeTest() {
		driver.closeBrowser();
	}
}
