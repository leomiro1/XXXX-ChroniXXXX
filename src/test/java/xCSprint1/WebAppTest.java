package xCSprint1;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import page.LoginPage;
import page.HomePage;
import util.WebDriverUtils;

public class WebAppTest extends AbstractTestDriver {

	WebDriverUtils util = new WebDriverUtils(driver);
	
	

	@Test
	public void TC001XC4LoginOK() throws IOException {
		
		util.StartLoggin("TC001-XC4-Login OK");
	
		util.logInfo("Login - Entering the application", driver);
		
		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login OK - Begin", driver);

		// login
		loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		//HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login OK - End", driver);
		
		try {
			  WebElement e = driver.findElement(By.xpath(property.xpathLogoChronicall));
			  Assert.assertTrue(e.isDisplayed());
			  util.logInfo("Home - Entering Home Page", driver);
			  util.EndLoggin("TC001-XC4-Login OK - PASSED");
			} catch(NoSuchElementException nsee) {
			  util.logError("TC001-XC4-Login OK - FAILED - The logo was not located.");
			  Assert.fail("TC001-XC4-Login OK - FAILED - The logo was not located.");
			} catch(AssertionError ae) {
			  util.logWarn("TC001-XC4-Login OK - FAILED - The logo was located, but not displayed.");
			  Assert.fail("TC001-XC4-Login OK - FAILED - The logo was located, but not displayed.");
			}
		
	}
	
	
	
	@Test
	public void TC002XC4LoginNOK() throws IOException {
		
		util.StartLoggin("TC002_XC-4_Login NOK");
		
		util.logInfo("Login - Entering the application", driver);

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();

		util.logInfo("Login NOK (empty fields) - Begin", driver);

		// login
		loginPage.LoginToHomePage("", "");
		//HomePage homePage = loginPage.LoginToHomePage("","");
		
		util.logInfo("Login NOK (empty fields) - End", driver);
		
		try {
			  WebElement e = driver.findElement(By.xpath(property.xpathLoginButton));
			  Assert.assertTrue(e.isDisplayed());
			  
			  util.logInfo("Empty fields dialog - Back to Login Page", driver);
			  util.EndLoggin("TC002_XC-4_Login NOK (empty fields) - PASSED");
			} catch(NoSuchElementException nsee) {
			  util.logError("TC002_XC-4_Login NOK (empty fields) - FAILED - The Empty field dialog was not located.");
			  Assert.fail("TC002_XC-4_Login NOK (empty fields) - FAILED - The Empty field dialog was not located.");
			} catch(AssertionError ae) {
			  util.logWarn("TC002_XC-4_Login NOK (empty fields) - FAILED - The Empty field dialog was located, but not displayed.");
			  Assert.fail("TC002_XC-4_Login NOK (empty fields) - FAILED - The Empty field dialog was located, but not displayed.");
			}
		
		util.logInfo("Login NOK - Begin", driver);

		// login
		loginPage.LoginToHomePage(property.dataUserNameNOK, property.dataPasswordNOK);
		//HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameNOK, property.dataPasswordNOK);
		
		util.logInfo("Login NOK - End", driver);
		
		try {
			  WebElement e = driver.findElement(By.xpath(property.xpathLoginButton));
			  Assert.assertTrue(e.isDisplayed());
			  
			  util.logInfo("Invalid credentials - Back to Login Page", driver);
			  util.EndLoggin("TC002_XC-4_Login NOK - PASSED");
			} catch(NoSuchElementException nsee) {
			  util.logError("TC002_XC-4_Login NOK - FAILED - The invalid credentials dialog was not located.");
			  Assert.fail("TC002_XC-4_Login NOK - FAILED - The invalid credentials dialog was not located.");
			} catch(AssertionError ae) {
			  util.logWarn("TC002_XC-4_Login NOK - FAILED - The invalid credentials dialog was located, but not displayed.");
			  Assert.fail("TC002_XC-4_Login NOK - FAILED - The invalid credentials dialog was located, but not displayed.");
			}
		
	}
	
	
	
	@Test
	public void TC001XC72CheckTopLevelNavigation() throws IOException {

        util.StartLoggin("TC001_XC-72 Check top level navigation");
		
		util.logInfo("Login - Entering the application", driver);

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);

		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// expand sidebar to explore the options and contrast with top level navigation
		homePage.CheckTopLevelNavigation();
	}
	
	
	
	@Test
	public void TC001XC38SearchExistentReport() throws IOException {

		int cont = 1;
		int counter = 0;
		int contReturn  = 0;
		String row = "";
		String rowNameReport = "";
		String nameReport = "";
				
		util.StartLoggin("TC001_XC-38_Search existent report");
		
		util.logInfo("Login - Entering the application", driver);

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);

		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		// search report
		contReturn = homePage.SearchReport(property.dataSearchExistentReport);
		
		util.logInfo("Home - Search executed - The search brought " + contReturn + " rows", driver);
		
		try {
			  // search brought more than one report. Go through them verify if their names match with the keyword
			  if (contReturn > 1) {
				  for (cont=1; cont<=contReturn;cont++) {
					  row = property.xpathSearchReturnAllReports + "[" + cont + "]";
					  rowNameReport = row + property.xpathRowNameReport;
					  nameReport=driver.findElement(By.xpath(rowNameReport)).getText();
					  util.logInfo("TC001_XC-38_Search existent report - row " + cont + " match with the keyword", driver);
					  Assert.assertTrue(nameReport.toLowerCase().contains(property.dataSearchExistentReport.toLowerCase()));
					  counter = counter + 1;
				  }
			  }
			  
			  // search brought one report verify if the name match with the keyword
			  if (contReturn == 1) {
				  rowNameReport = property.xpathSearchReturnAllReports + property.xpathRowNameReport;
				  nameReport=driver.findElement(By.xpath(rowNameReport)).getText();
				  util.logInfo("TC001_XC-38_Search existent report - row " + cont + " match with the keyword", driver);
				  Assert.assertTrue(nameReport.toLowerCase().contains(property.dataSearchExistentReport.toLowerCase()));
  			  	  counter = counter + 1;
			  }
			  
			} catch(NoSuchElementException nsee) {
			  util.logError("TC001_XC-38_Search existent report - FAILED - The reports brought by the search does not match the keyword.");
			  Assert.fail("TC001_XC-38_Search existent report - FAILED - The reports brought by the search does not match the keyword.");
			} catch(AssertionError ae) {
			  util.logWarn("TC001_XC-38_Search existent report - FAILED - The reports brought by the search does not match the keyword.");
			  Assert.fail("TC001_XC-38_Search existent report - FAILED - The reports brought by the search does not match the keyword.");
			}
		
		// if counter match contReturn them all the reports searched's names match with the keyword
	    if (counter == contReturn) {
        	util.EndLoggin("TC001_XC-38_Search existent report - PASSED");	
        }
        else {
		   	util.logError("TC001_XC-38_Search existent report - FAILED - The reports brought by the search does not match the keyword.");
        	Assert.fail("TC001_XC-38_Search existent report - FAILED - The reports brought by the search does not match the keyword.");
	    }
	}
	
	
	
	@Test
	public void TC002XC38SearchNonExistentReport() throws IOException {

		util.StartLoggin("TC002_XC-38_Search non existent report");
		
		util.logInfo("Login - Entering the application", driver);

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		// search report
		int contReturn = homePage.SearchReport(property.dataSearchNonExistentReport);
		
		try {
			  // if no reports brought by the search matches with the keyword then the test pass
			  Assert.assertTrue(contReturn == 0);
			  util.logInfo("Home - Search executed - The search brought " + contReturn + " rows", driver);
			  util.EndLoggin("TC002_XC-38_Search non existent report - PASSED");
			} catch(NoSuchElementException nsee) {
			  util.logError("TC002_XC-38_Search non existent report - FAILED - The search brought " + contReturn + " rows");
			  Assert.fail("TC002_XC-38_Search non existent report - FAILED - The search brought " + contReturn + " rows");
			} catch(AssertionError ae) {
			  util.logWarn("TC002_XC-38_Search non existent report - FAILED - The search brought " + contReturn + " rows");
			  Assert.fail("TC002_XC-38_Search non existent report - FAILED - The search brought " + contReturn + " rows");
			}
		
	}
	
	
	
	@Test
	public void TC003XC19Listinformation() throws IOException {

        util.StartLoggin("TC003_XC-19_List information");
		
		util.logInfo("Login - Entering the application", driver);

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		// check the header of the columns of the report list
		homePage.CheckListinformation();
		
	}

	
	
	@Test
	public void TC001XC54FilterExistentReportByTag() throws IOException {

		int contReturn  = 0;
		
        util.StartLoggin("TC001_XC-54_Filter existent report by tag");
		
		util.logInfo("Login - Entering the application", driver);

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		// filter by tag and return the quantity of reports found
		contReturn = homePage.FilterExistentReportByTag(property.dataTag,property.dataReportName);
		
		util.logInfo("Home - Search executed - The filter brought " + contReturn + " row-s of the " + Arrays.asList(property.dataReportName.split(",")).size() + " reports passed by parameter", driver);
		
		// if the taggin function returns a number of rows then the test is pass
		if (contReturn != 0) {
        	util.EndLoggin("TC001_XC-54_Filter existent report by tag - PASSED");	
        }
        else {
        	util.logError("TC001_XC-54_Filter existent report by tag - FAILED - The reports brought by the taggin does not match the required reports");
        	Assert.fail("TC001_XC-54_Filter existent report by tag - FAILED - The reports brought by the taggin does not match the required reports");
	    }
	}

	
	
	@Test
	public void TC002XC54FilterExistentReportByMultipleTags() throws IOException {

		int contReturn  = 0;
		
        util.StartLoggin("TC002_XC-54_Filter existent report by multiple tags");
		
		util.logInfo("Login - Entering the application", driver);

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		// filter by tag and return the quantity of reports found
		contReturn = homePage.FilterExistentReportByTag(property.dataTags,property.dataReportNames);
		
		util.logInfo("Home - Search executed - The filter brought " + contReturn + " row-s of the " + Arrays.asList(property.dataReportNames.split(",")).size() + " reports passed by parameter", driver);
		
		// if the taggin task returns a number of rows then the test is passed
		if (contReturn != 0) {
        	util.EndLoggin("TC002_XC-54_Filter existent report by multiple tags - PASSED");	
        }
        else {
        	util.logError("TC002_XC-54_Filter existent report by multiple tags - FAILED - The reports brought by the taggin does not match the required reports");
        	Assert.fail("TC002_XC-54_Filter existent report by multiple tags - FAILED - The reports brought by the taggin does not match the required reports");
	    }
	}
	
	

	@Test
	public void TC003XC54FilterNonExistentReportByMultipleTags() throws IOException {

		int contReturn  = 0;
		
        util.StartLoggin("TC003_XC-54_Filter non existent report by multiple tags");
		
		util.logInfo("Login - Entering the application", driver);

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		// filter by tag and return the quantity of reports found
		contReturn = homePage.FilterExistentReportByTag(property.dataTagsNonExistent,property.dataReportNamesNonExistent);
		
		util.logInfo("Home - Search executed - The filter brought " + contReturn + " row-s of the " + Arrays.asList(property.dataReportNamesNonExistent.split(",")).size() + " reports passed by parameter", driver);
		
		// if the taggin returns zero rows then the test is passed
		if (contReturn == 0) {
        	util.EndLoggin("TC003_XC-54_Filter non existent report by multiple tags - PASSED");	
        }
        else {
        	util.logError("TC003_XC-54_Filter non existent report by multiple tags - FAILED - The reports brought by the taggin does not match the required reports");
        	Assert.fail("TC003_XC-54_Filter non existent report by multiple tags - FAILED - The reports brought by the taggin does not match the required reports");
	    }
	}

	
/*
	@Test
	public void TC003XC21CheckReportPiningWithSameSection() throws IOException {

		int contReturn  = 0;
		
		util.StartLoggin("TC003_XC-21_Check report pining with same section");
		
		util.logInfo("Login - Entering the application", driver);

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		// search report to pin
		contReturn = homePage.SearchReport(property.dataReportToPin);
		
		util.logInfo("Home - Pin report - The search brought " + contReturn + " rows", driver);
		
		// check if the report to pin was found in the search
		try {
			  Assert.assertTrue(contReturn==1);
			} catch(NoSuchElementException nsee) {
			  util.logError("TC003_XC-21_Check report pining with same section - FAILED - The selected report to pin was not found.");
			  Assert.fail("TC003_XC-21_Check report pining with same section - FAILED - The selected report to pin was not found.");
			} catch(AssertionError ae) {
			  util.logWarn("TC003_XC-21_Check report pining with same section - FAILED - The selected report to pin was not found.");
			  Assert.fail("TC003_XC-21_Check report pining with same section - FAILED - The selected report to pin was not found.");
			}
		
		// if the report was found then I proceed with the pin task
		if (contReturn == 1) {
			//pin report
			homePage.PinUnpinFirstReport();
			
			// do a new search with empty so the homepage bring the complete report list and I'm able to capture the screen
			int contReturn2 = 0;
			contReturn2 = homePage.SearchReport(" ");
			util.logInfo("Home - Pin report - report pinned", driver);
			util.EndLoggin("TC003_XC-21_Check report pining with same section - PASSED");
		}
	}
	
	
	
	@Test
	public void TC004XC21CheckReportUnpiningWithSameSection() throws IOException {
		
		int contReturn  = 0;
		
		util.StartLoggin("TC004_XC-21_Check report unpining with same section");
		
		util.logInfo("Login - Entering the application", driver);

		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		util.logInfo("Login - Begin", driver);
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.logInfo("Login - End", driver);
		
		// go to the specific report section
		homePage.GoToSection("Reports","ALL REPORTS");
		
		// search report to pin
		contReturn = homePage.SearchReport(property.dataReportToPin);
		
		util.logInfo("Home - Pin report - The search brought " + contReturn + " rows", driver);
		
		// check if the report to pin was found in the search
		try {
			  Assert.assertTrue(contReturn==1);
			} catch(NoSuchElementException nsee) {
			  util.logError("TC004_XC-21_Check report unpining with same section - FAILED - The selected report to pin was not found.");
			  Assert.fail("TC004_XC-21_Check report unpining with same section - FAILED - The selected report to pin was not found.");
			} catch(AssertionError ae) {
			  util.logWarn("TC004_XC-21_Check report unpining with same section - FAILED - The selected report to pin was not found.");
			  Assert.fail("TC004_XC-21_Check report unpining with same section - FAILED - The selected report to pin was not found.");
			}
		
		// if the report was found then I proceed with the pin task
		if (contReturn == 1) {
			//pin report
			homePage.PinUnpinFirstReport();
			
			// do a new search with empty so the homepage bring the complete report list
			int contReturn2 = 0;
			contReturn2 = homePage.SearchReport(" ");
			util.logInfo("Home - Pin report - report pinned", driver);
		
			// search report to unpin
			contReturn2 = homePage.SearchReport(property.dataReportToPin);
			
			util.logInfo("Home - Unpin report - The search brought " + contReturn + " rows", driver);
			
			// check if the report to unpin was found in the search
			try {
				  Assert.assertTrue(contReturn2==1);
				} catch(NoSuchElementException nsee) {
				  util.logError("TC004_XC-21_Check report unpining with same section - FAILED - The selected report to unpin was not found.");
				  Assert.fail("TC004_XC-21_Check report unpining with same section - FAILED - The selected report to unpin was not found.");
				} catch(AssertionError ae) {
				  util.logWarn("TC004_XC-21_Check report unpining with same section - FAILED - The selected report to unpin was not found.");
				  Assert.fail("TC004_XC-21_Check report unpining with same section - FAILED - The selected report to unpin was not found.");
				}

			// if the report was found then I proceed with the unpin task
			if (contReturn2 == 1) {
				//pin report
				homePage.PinUnpinFirstReport();
				
				// do a new search with empty so the homepage bring the complete report list and I'm able to capture the screen
				int contReturn3 = 0;
				contReturn3 = homePage.SearchReport(" ");
				util.logInfo("Home - Unpin report - report unpinned", driver);
				util.EndLoggin("TC004_XC-21_Check report unpining with same section - PASSED");
			}
		}
	}
*/
}

