package xCSprint2;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import util.WebDriverUtils;
import page.LoginPage;
import page.HomePage;

public class WebAppTest extends AbstractTestDriver {

	WebDriverUtils util = new WebDriverUtils(driver);
	
	
	@Test
	public void TC001XC40CheckRowsPerPageInReportList() throws IOException {

		util.StartLoggin("TC001_XC-40_Check rows per page in Report list");
		
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
		
		WebElement idRangePagination = driver.findElement(By.xpath(property.xpathRangePagination));
		String rangePagination = idRangePagination.getText();
		
		// change the rows per page
		homePage.ChangeRowsPerPage(property.dataRowsPerPage);

		// returns true if the amount of rows that the page of report list shows matches with the rows per page
		boolean rowsPerPage = homePage.CheckRowsPerPage();
		
  	   	// assert if the amount of rows that the page of report list shows matches with the rows per page
		try {
				  Assert.assertTrue(rowsPerPage);
			  	  util.logInfo("TC001_XC-40_Check rows per page in Report list - The amount of rows of page '" + rangePagination + "' in the report list matches the rows per page", driver);
				  util.EndLoggin("TC001_XC-40_Check rows per page in Report list - PASSED");
			    } catch(NoSuchElementException nsee) {
			      util.logError("TC001_XC-40_Check rows per page in Report list - FAILED -  The amount of rows of page '" + rangePagination + "' in the report list does not match the rows per page");
			      Assert.fail("TC001_XC-40_Check rows per page in Report list - FAILED -  The amount of rows of page '" + rangePagination + "' in the report list does not match the rows per page");
			    } catch(AssertionError ae) {
			      util.logWarn("TC001_XC-40_Check rows per page in Report list - FAILED -  The amount of rows of page '" + rangePagination + "' in the report list does not match the rows per page");
			      Assert.fail("TC001_XC-40_Check rows per page in Report list - FAILED -  The amount of rows of page '" + rangePagination + "' in the report list does not match the rows per page");
			    }	
		
	}
	
	
	
	@Test
	public void TC002XC40CheckPagingInReportList() throws IOException {

		util.StartLoggin("TC002_XC-40_Check paging in Report list");
		
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
		
		// change the rows per page
		homePage.ChangeRowsPerPage(property.dataRowsPerPage);
		
		// returns the amount of pages of report list
		int cantpages = homePage.GoThroughPages();

		
  	   	// assert if the amount of rows that the page of report list shows matches with the rows per page
		try {
				  Assert.assertTrue(cantpages != 0);
			  	  util.logInfo("TC002_XC-40_Check paging in Report list - The amount of pages of the report list is '" + cantpages + "'", driver);
				  util.EndLoggin("TC002_XC-40_Check paging in Report list - PASSED");
			    } catch(NoSuchElementException nsee) {
			      util.logError("TC002_XC-40_Check paging in Report list - FAILED -  The amount of pages of the report list '" + cantpages + "' does not match");
			      Assert.fail("TC002_XC-40_Check paging in Report list - FAILED -  The amount of pages of the report list '" + cantpages + "' does not match");
			    } catch(AssertionError ae) {
			      util.logWarn("TC002_XC-40_Check paging in Report list - FAILED -  The amount of pages of the report list '" + cantpages + "' does not match");
			      Assert.fail("TC002_XC-40_Check paging in Report list - FAILED -  The amount of pages of the report list '" + cantpages + "' does not match");
			    }	
		
	}
		
}
