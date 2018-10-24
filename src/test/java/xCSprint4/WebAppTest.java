package xCSprint4;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import util.WebDriverUtils;
import page.LoginPage;
import page.HomePage;
import page.CustomizeReportPage;
import page.ReportResultsPage;

public class WebAppTest extends AbstractTestDriver {

	WebDriverUtils util = new WebDriverUtils(driver);
	
	
	
	@Test
	public void TC001XC49LogOutOK() throws IOException {
		
		util.StartLoggin("TC001-XC49-LogOut OK");
	
		util.logInfo("Login - Entering the application", driver);
		
		// create browser instance
		LoginPage loginPage = new LoginPage(driver);
		loginPage.navigateToLogin();
		
		// login
		HomePage homePage = loginPage.LoginToHomePage(property.dataUserNameOK, property.dataPasswordOK);
		
		util.wait(2);

		util.logInfo("LogOut OK - Begin", driver);
		
		//logout
		loginPage = homePage.LogoutToLoginPage();
		
		util.logInfo("LogOut OK - End", driver);
		
		try {
			  WebElement e = driver.findElement(By.xpath(property.xpathLoginButton));
			  Assert.assertTrue(e.isDisplayed());
			  util.logInfo("Home - Back to login page", driver);
			  util.EndLoggin("TC001-XC49-LogOut OK - PASSED");
			} catch(NoSuchElementException nsee) {
			  util.logError("TC001-XC49-LogOut OK - FAILED - The login page was not located.");
			  Assert.fail("TC001-XC49-LogOut OK - FAILED - The login page was not located.");
			} catch(AssertionError ae) {
			  util.logWarn("TC001-XC49-LogOut OK - FAILED - The login page was located, but not displayed.");
			  Assert.fail("TC001-XC49-LogOut OK - FAILED - The login page was located, but not displayed.");
			}
		
	}
				 	
}
